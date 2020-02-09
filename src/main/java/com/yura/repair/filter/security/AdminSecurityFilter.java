package com.yura.repair.filter.security;

import com.yura.repair.entity.Role;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        urlPatterns = {"/admin"})
public class AdminSecurityFilter extends SecurityFilter {

    public AdminSecurityFilter() {
        super(Role.ADMIN);
    }
}
