package com.yura.repair.filter.security;

import com.yura.repair.entity.Role;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        urlPatterns = "/master/*")
public class MasterSecurityFilter extends SecurityFilter {

    public MasterSecurityFilter() {
        super(Role.MASTER);
    }
}
