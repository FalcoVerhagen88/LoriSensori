package com.lorisensori.application.enums;

import java.io.Serializable;

public enum BerichtEnums implements Serializable {
    ONGEWENSTE_NIVEAU_DALING("Ongewenste Niveaudaling"),
    TANKEN("Bijtanken"),
    LAAG_SPANNINGS_NIVEAU_ACCU("Laag Spanningsniveau Accu"),
    SLOT_OPEN("Slot open buiten openingstijden");

    static final BerichtEnums DEFAULT = TANKEN;

    private final String displayName;

    BerichtEnums(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
