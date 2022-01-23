package utils;

import java.util.HashMap;
import java.util.Map;

public class FieldUtils {
    public static Map<String, Object> getFields(String[] keys, Object[] values){
        Map<String, Object> valuesMap = null;
        if(keys.length == values.length){
            valuesMap = new HashMap<>();
            for (int i=0; i< keys.length;i++){
                valuesMap.put(keys[i], values[i]);
            }
        }
        return valuesMap;
    }
}