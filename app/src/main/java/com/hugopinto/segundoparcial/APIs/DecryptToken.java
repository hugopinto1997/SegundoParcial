package com.hugopinto.segundoparcial.APIs;


import com.google.gson.*;

import java.lang.reflect.Type;

public class DecryptToken implements JsonDeserializer<String>{
    private String tok="";

    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if(json.getAsJsonObject()!=null){
            tok=json.getAsJsonObject().get("token").getAsString();
        }
        return tok;
    }
}
