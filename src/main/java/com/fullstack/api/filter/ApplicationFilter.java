package com.fullstack.api.filter;

import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class ApplicationFilter implements Filter {

    private Logger LOGGER = LoggerFactory.getLogger(ApplicationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Open Resource ....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        long before = System.currentTimeMillis();

        try {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/springactivejdbc?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            Base.openTransaction();
            chain.doFilter(request, response);
            Base.commitTransaction();
        } catch (Exception e) {
            Base.rollbackTransaction();
            throw e;
        } finally {
            Base.close();
        }
        LOGGER.info("Processing took: {}  milliseconds", System.currentTimeMillis() - before);
    }

    @Override
    public void destroy() {
        System.out.println("Destroy Resource ....");
    }
}
