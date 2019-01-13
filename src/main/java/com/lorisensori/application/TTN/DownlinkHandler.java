package com.lorisensori.application.TTN;

public class DownlinkHandler {

    private final byte[] DOWNLINK_SLOT_OPENEN = {0x00, 0x01};
    private final byte[] DOWNLINK_SLOT_SLUITEN = {0x00, 0x00};

    public byte[] setDieselNiveau(int alarmNiveau){
        return new byte[]{0x01, (byte) alarmNiveau};
    }

    public byte[] setOpeningsTijd(int uren, int minuten){
        return new byte[]{0x02, (byte) uren, (byte) minuten};
    }

    public byte[] setSluitingsTijd(int uren, int minuten){
        return new byte[]{0x02, (byte) uren, (byte) minuten};
    }

    public byte[] instellenWeekendDagen(int weekendPlanning){
        byte[] weekendDagen = new byte[2];
        switch (weekendPlanning){
            case 0:
                weekendDagen = new byte[]{0x04, (byte)weekendPlanning};
                break;
            case 1:
                weekendDagen = new byte[]{0x04, (byte)weekendPlanning};
                break;
            case 2:
                weekendDagen = new byte[]{0x04, (byte) weekendPlanning};
                break;
            case 3:
                weekendDagen = new byte[]{0x04, (byte)weekendPlanning};

                default:
                    //default niet nodig
                    break;
        }
        return weekendDagen;
    }

    public byte[] getDOWNLINK_SLOT_OPENEN() {
        return DOWNLINK_SLOT_OPENEN;
    }

    public byte[] getDOWNLINK_SLOT_SLUITEN() {
        return DOWNLINK_SLOT_SLUITEN;
    }

    public void stuurDownlinkNaarTank(){

    }


}
