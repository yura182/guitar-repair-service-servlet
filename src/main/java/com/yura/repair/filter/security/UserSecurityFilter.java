package com.yura.repair.filter.security;

import com.yura.repair.entity.Role;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        urlPatterns = "/client/*")
public class UserSecurityFilter extends SecurityFilter {

    public UserSecurityFilter() {
        super(Role.CLIENT);
    }
}
