package org.apache.payment.gateway.config.activeMq.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.payment.gateway.config.activeMq.MessageAcknowledgement;
import org.apache.payment.gateway.config.activeMq.models.ActionType;
import org.apache.payment.gateway.config.activeMq.models.PublishBody;
import org.apache.payment.gateway.config.activeMq.models.QueueMessageModel;
import org.apache.payment.gateway.utils.Utility;

/**
 * @author Rahul Goel created on 10/6/18
 */


public abstract class AbstractMessageListener implements ActiveMqMessageListener{

    private static Logger logger = LogManager.getLogger(AbstractMessageListener.class);

    public void onMessage(PublishBody payload, String destination, MessageAcknowledgement messageAcknowledgement) throws Exception {
        QueueMessageModel model = Utility.convertToQueueMessageModel(payload);
        if (model.getAction().compareTo(ActionType.CREATE) == 0) {
            this.actionCreate(model.getPayload().toString(), destination);
        } else if (model.getAction().compareTo(ActionType.UPDATE) == 0) {
            this.actionUpdate(model.getPayload().toString(), destination);
        } else if (model.getAction().compareTo(ActionType.DELETE) == 0) {
            this.actionDelete(model.getPayload().toString(), destination);
        } else {
            this.actionDefault(model.getPayload().toString(), destination);
        }

        messageAcknowledgement.acknowledge();
    }

    public abstract void actionCreate(String var1, String var2);

    public abstract void actionUpdate(String var1, String var2);

    public abstract void actionDelete(String var1, String var2);

    public abstract void actionDefault(String var1, String var2);

}
