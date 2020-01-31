package com.yura.repairservice.domain.order;

public enum Status {
    NEW("status.new"),
    ACCEPTED("status.accepted"),
    PROCESSING("status.processing"),
    COMPLETED("status.completed"),
    REJECTED("status.rejected"),
    CANCELED("status.canceled");

    String localeDescription;

    Status(String localeDescription) {
        this.localeDescription = localeDescription;
    }

    public String getLocaleDescription() {
        return localeDescription;
    }
}
