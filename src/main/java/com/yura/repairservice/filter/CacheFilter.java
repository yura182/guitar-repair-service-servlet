package com.yura.repairservice.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class CacheFilter implements Filter {
    private static final String PARAMETER = "Cache-Control";
    private static final String VALUE = "no-cache, no-store, must-revalidate";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.setHeader(PARAMETER, VALUE);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
