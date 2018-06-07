package com.hugopinto.segundoparcial.APIs;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DecryptNews implements JsonDeserializer<News> {

    public News JDnews;

    @Override
    public News deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JDnews = new News();

        JsonObject jsonObject = json.getAsJsonObject();


        JDnews.setId(jsonObject.get("_id").getAsString());
        JDnews.setTitle(jsonObject.get("title").getAsString());
        JDnews.setBody(jsonObject.get("body").getAsString());
        JDnews.setGame(jsonObject.get("game").getAsString());
        JDnews.setCreate_date(jsonObject.get("created_date").getAsString());
        JDnews.setCoverImage(jsonObject.get("coverImage").getAsString());
        JDnews.setDescription(jsonObject.get("description").getAsString());

        return JDnews;
    }


}
