package com.wang.teachermsrv.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnyUtil {

    public static String jsonmapToJsonstr(Map<String, Object> jsonmap) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonstr = mapper.writeValueAsString(jsonmap);
        return jsonstr;
    }

    /**
     * usage:
     * Object obj = new Object();
     * List<String> list = objToList(obj, String.class);
     * list.forEach(System.out::println);
     */
    public static <T> List<T> objToList(Object obj, Class<T> anyClass) {
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>) {
            for(Object o : (List<?>) obj) {
                result.add(anyClass.cast(o));
            }
            return result;
        }
        return null;
    }

    public static Map<String, Object> objToJsonmap(Object obj) {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> Jsonmap = oMapper.convertValue(obj, Map.class);
        return Jsonmap;
    }
}
