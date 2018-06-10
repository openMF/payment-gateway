package org.apache.payment.gateway.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.payment.gateway.config.activeMq.models.PublishBody;
import org.apache.payment.gateway.config.activeMq.models.QueueMessageModel;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Rahul Goel created on 2/6/18
 */


public class Utility {

    private static Logger logger = LogManager.getLogger(Utility.class);

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
