package com.yzchn.platform.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 *    
 *  *   
 *  * @Description:  jackson工具类   
 *  * @Author:       柏龙进  
 *  * @CreateDate:   2019/8/6 15:25  
 *  *    
 *  
 */
public class JacksonUtils {

    public static String serialization(Object t) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.writeValueAsString(t);
    }

    public static <T> T deserialize(String str, Class<T> valueType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(str, valueType);
    }

    public static <T> T deserialize(String content, TypeReference valueTypeRef) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T) mapper.readValue(content, valueTypeRef);
    }
}
