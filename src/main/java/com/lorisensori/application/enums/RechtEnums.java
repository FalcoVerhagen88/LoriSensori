package com.lorisensori.application.enums;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.function.ToDoubleBiFunction;

public enum  RechtEnums implements Serializable {

    BEKIJKEN_SENSOR_GEGEVENS("Bekijken SensorGegevens"),
    ONTVANGEN_NOTIFICATIES("Ontvangen Notificaties"),
    TOEVOEGEN_TANK("Toevoegen Tank"),
    UPDATEN_TANK("Updaten Tank"),
    AFMELDEN_TANK("Afmelden Tank"),
    BEHEREN_ACTUATOREN("Beheren Actuatoren"),
    ADMINISTRATOR("Administrator");

    static final RechtEnums DEFAULT = BEKIJKEN_SENSOR_GEGEVENS;

    private final String displayName;

    RechtEnums(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}