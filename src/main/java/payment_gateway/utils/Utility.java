package payment_gateway.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

}
