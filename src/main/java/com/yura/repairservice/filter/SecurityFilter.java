package com.yura.repairservice.filter;

import com.yura.repairservice.domain.user.Role;
import com.yura.repairservice.domain.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}, urlPatterns = "/profile.jsp")
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("404.jsp").forward(request, response);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
