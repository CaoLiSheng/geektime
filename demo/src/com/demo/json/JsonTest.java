package com.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonTest {

    public static void main(String[] args) {
        Object parsed = JSON.parse("{\"arr\":[10000000000,2,3],\"foo\":\"bar\"}");
        travel(parsed, " ");
    }

    private static void travel(Object obj, String indent) {
        if (obj instanceof JSONObject) {
            System.out.println(indent + "JSONObject");
            for (String key : ((JSONObject) obj).keySet()) {
                travel(((JSONObject) obj).get(key), indent + " ");
            }
        } else if (obj instanceof JSONArray) {
            System.out.println(indent + "JSONArray");
            for (int i=0; i<((JSONArray) obj).size(); i++) {
                travel(((JSONArray) obj).get(i), indent + " ");
            }
        } else {
            System.out.println(indent + obj.getClass().getSimpleName());
        }
    }

}
