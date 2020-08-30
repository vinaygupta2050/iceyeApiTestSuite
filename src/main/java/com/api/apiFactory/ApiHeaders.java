package com.api.apiFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author in-vinaykumar.gupta on 29/08/20
 * @project IntelliJ IDEA
 */
public class ApiHeaders {

    public  static Map<String,String> defaultHeader(){
        Map<String,String> headerMap = new HashMap<String, String>();
        headerMap.put("accept","*/*");
        //headerMap.put("Content-Type","application/json");
        return headerMap;

    }
    public  static Map<String,String> headerWithContentType(){
        Map<String,String> headerMap = new HashMap<String, String>();
        String token="";
        headerMap.put("accept","*/*");
        headerMap.put("Content-Type","application/json");
        return headerMap;
    }
}

