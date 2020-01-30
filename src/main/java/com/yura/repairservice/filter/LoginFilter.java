package com.yura.repairservice.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/login.jsp")
public class LoginFilter implements Filter {
    private final static String ATTRIBUTE = "user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getSession().getAttribute(ATTRIBUTE) != null) {
            req.getSession().removeAttribute(ATTRIBUTE);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
