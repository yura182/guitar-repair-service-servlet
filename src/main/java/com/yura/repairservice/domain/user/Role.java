package com.yura.repairservice.domain.user;

public enum Role {
    ADMIN("role.admin"),
    MASTER("role.master"),
    CLIENT("role.client");

    String localeDescription;

    Role(String localeDescription) {
        this.localeDescription = localeDescription;
    }

    public String getLocaleDescription() {
        return localeDescription;
    }
}
