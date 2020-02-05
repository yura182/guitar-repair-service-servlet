package com.yura.repairservice.filter.security;

import com.yura.repairservice.domain.user.Role;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        urlPatterns = {"/admin"})
public class AdminSecurityFilter extends SecurityFilter {

    public AdminSecurityFilter() {
        super(Role.ADMIN);
    }
}
