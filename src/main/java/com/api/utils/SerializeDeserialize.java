package com.api.utils;

import com.google.gson.Gson;

public class SerializeDeserialize {
    public static String getJson (Object obj){
        Gson gson= new Gson();
        String json = gson.toJson(obj);
        return json;
    }
}
