package com.yura.repairservice.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/login.jsp", "/register.jsp"})
public class LogoutFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getSession().getAttribute("user") != null) {
            req.getSession().removeAttribute("user");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
