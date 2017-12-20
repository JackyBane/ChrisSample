package com.example.mylibrary.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class JsonUtils {

    private static Gson gson = new GsonBuilder()
            //.excludeFieldsWithoutExposeAnnotation() // 不导出实体中没有用@Expose注解的属性
            .enableComplexMapKeySerialization() // 支持Map的key为复杂对象的形式
            .serializeNulls()
                    //.setDateFormat("yyyy-MM-dd HH:mm:ss")// 时间转化为特定格式
            .setPrettyPrinting() // 对json结果格式化.
            .create();
    private static com.google.gson.JsonParser JsonParser = new JsonParser();

    /**
     * string 是 jsonobject, 解析成jsonObject
     */
    public static <T> T parse(String strJson, Class<T> clazz) {
        if (strJson == null || strJson.length() == 0) {
            Log.e("JsonUtils", "strJson == null || strJson.length() == 0");
            return null;
        }
        try {
            JsonObject jsonObject = (JsonObject) JsonParser.parse(strJson);
            return gson.fromJson(jsonObject, clazz);
        } catch (Exception ignored) {
            Log.e("JsonUtils", ignored.toString());
        }
        return null;
    }

    /**
     * jsonobject-stirng 解析为实体
     */
    public static <T> T parseObjFromType(String strJson, java.lang.reflect.Type type) {
        if (strJson == null || strJson.length() == 0) {
            Log.e("JsonUtils", "strJson == null || strJson.length() == 0");
            return null;
        }
        try {
            JsonObject jsonObject = (JsonObject) JsonParser.parse(strJson.trim());
            return gson.fromJson(jsonObject, type);
        } catch (Exception ignored) {
            Log.e("JsonUtils", ignored.toString());
        }
        return null;
    }

    /**
     * string 是 jsonArray，解析成ArrayList
     */
    public static <E> ArrayList<E> parse(String strJson, java.lang.reflect.Type type) {
        if (strJson == null || strJson.length() == 0) {
            Log.e("JsonUtils", "strJson == null || strJson.length() == 0");
            return null;
        }
        ArrayList<E> result = null;
        try {
            result = gson.fromJson(strJson, type);
        } catch (Exception ignored) {
            Log.e("JsonUtils", ignored.toString());
        }
        return result;
    }
}
