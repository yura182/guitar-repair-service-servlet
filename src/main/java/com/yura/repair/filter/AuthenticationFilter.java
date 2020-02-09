package com.yura.repair.filter;

import com.yura.repair.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        urlPatterns = {"/profile.jsp", "/user-add-order.jsp"})
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        UserDto userDto = (UserDto) session.getAttribute("user");

        if (userDto == null) {
            ((HttpServletResponse) response).sendRedirect("login.jsp");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
