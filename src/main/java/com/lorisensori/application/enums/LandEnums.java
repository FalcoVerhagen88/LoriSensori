package com.lorisensori.application.enums;

import java.io.Serializable;


public enum LandEnums implements Serializable {
    NEDERLAND("Nederland"),
    BELGIE("België"),
    LUXEMBURG("Luxemburg");

    static final LandEnums DEFAULT = NEDERLAND;

    private final String displayName;

    LandEnums(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}
