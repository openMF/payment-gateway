package org.apache.payment.gateway.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.payment.gateway.config.activeMq.models.PublishBody;
import org.apache.payment.gateway.config.activeMq.models.QueueMessageModel;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Rahul Goel created on 2/6/18
 */

public class Utility {

    private static Logger logger = LogManager.getLogger(Utility.class);

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static TypeFactory typeFactory = objectMapper.getTypeFactory();

    private static RestTemplate restTemplate = new RestTemplate();

    public static boolean isNullOrBlank(String key){
        int strLen;
        if (key != null && (strLen = key.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(key.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static String objectToJson(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonToString = mapper.writeValueAsString(object);
            return jsonToString;
        } catch (Exception var3) {
            logger.error("exception", var3);
            return null;
        }
    }

    public static <T1> List<T1> convertModelList(List<? extends Object> sourceClass, Class<T1> destinationClass) {
        try {
            CollectionType collectionType = typeFactory.constructCollectionType(List.class, destinationClass);
            return objectMapper.convertValue(sourceClass, collectionType);
        } catch (Exception exp) {
            logger.error("[convertModelList] Exception occurred while converting model list", exp);
            return null;
        }
    }

    public static <T> T convertModel(Object sourceClass, Class<T> destinationClass) {
        try {
//            JavaType javaType = typeFactory.constructType(destinationClass);
            return objectMapper.convertValue(sourceClass, destinationClass);
        } catch (Exception exp) {
            logger.error("Exception", exp);
            return null;
        }
    }

    public static Long getLongValue(String str){
        try{
            return new Long(str);
        }catch (Exception e) {
            logger.error("Error Occurred while converting String to Long value - " + str, e);
        }
        return 0L;
    }

    public static boolean getBooleanValue(String str){
        try{
            return Boolean.valueOf(str);
        }catch(Exception e){
            logger.error("Error occurred while converting String to boolean - " + str, e);
        }
        return false;
    }

    public static boolean isNonEmpty(Collection<?> collection) {
        return (collection!=null && !collection.isEmpty());
    }

    public static QueueMessageModel convertToQueueMessageModel(PublishBody publishBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        QueueMessageModel model = (QueueMessageModel)objectMapper.readValue(publishBody.getPayload(), QueueMessageModel.class);
        model.setPayload(objectToJson(model.getPayload()));
        return model;
    }
}
