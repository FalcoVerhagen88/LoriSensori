package com.lorisensori.application.TTN;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thethingsnetwork.data.common.messages.DownlinkMessage;

import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;
import java.util.Map;

@Service
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

    public void stuurDownlinkNaarTank() {

    }

    public DownlinkMessage getDownlinkMessage(Map<String, Object> payload) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);

        JSONObject jsonObject = new JSONObject(jsonString);

        JSONArray jsonArray = jsonObject.getJSONObject("downlink").getJSONArray("bytes");
       // System.out.println(jsonObject.toString());

        byte [] downlinkPayload = new byte[jsonArray.length()];
        byte [] encodedBytes = new byte[jsonArray.length()];
//
        for (int i = 0; i < jsonArray.length(); i++){
            byte bytes = (byte)jsonArray.getInt(i);
            Arrays.fill(downlinkPayload, bytes);
            encodedBytes = Base64.encodeBase64(downlinkPayload);
            System.out.println(downlinkPayload[i]);
        }
        return new DownlinkMessage(1, encodedBytes );
    }

    public String getDevIdTank(Map<String, Object> payload)throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);

        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getJSONObject("downlink").getString("dev_id");
    }
}
