package com.yura.repair.filter.security;

import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {
    protected Role role;

    public SecurityFilter(Role role) {
        this.role = role;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        UserDto userDto = (UserDto) session.getAttribute("user");

        if (userDto == null || userDto.getRole() != role) {
            request.getRequestDispatcher("404").forward(request, response);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
