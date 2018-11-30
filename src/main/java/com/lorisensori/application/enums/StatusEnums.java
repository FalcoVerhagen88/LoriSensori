package com.lorisensori.application.enums;

import java.io.Serializable;

public enum StatusEnums implements Serializable {
    ACTIEF("Actief"),
    NONACTIEF("Non actief"),
    INGEBRUIK("In gebruik"),
    INREPARATIE("In reparatie");

    static final StatusEnums DEFAULT = ACTIEF;

    private final String displayName;

    StatusEnums(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
