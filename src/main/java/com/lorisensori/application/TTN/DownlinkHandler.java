package com.lorisensori.application.TTN;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.thethingsnetwork.data.common.messages.DownlinkMessage;

import java.util.Map;

public class DownlinkHandler {

    public DownlinkMessage getDownlinkMessage(Map<String, Object> payload) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);

        JSONObject jsonObject = new JSONObject(jsonString);

        JSONArray jsonArray = jsonObject.getJSONObject("downlink").getJSONArray("bytes");

        byte [] downlinkPayload = new byte[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++){
            downlinkPayload[i] = (byte) jsonArray.getInt(i);
        }
        return new DownlinkMessage(1, downlinkPayload );
    }

    public String getDevIdTank(Map<String, Object> payload)throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);

        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getJSONObject("downlink").getString("dev_id");
    }
}
