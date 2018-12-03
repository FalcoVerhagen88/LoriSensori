package com.lorisensori.application.interfaces;

import com.lorisensori.application.enums.BerichtEnums;
import com.lorisensori.application.domain.Medewerker;

import java.util.ArrayList;

public interface INotificatieHandler {

    void stuurBericht(ArrayList<String> dataTankBeheerders, BerichtEnums berichtType);

    boolean medewerkerGeupdate(Medewerker medewerker);
}
