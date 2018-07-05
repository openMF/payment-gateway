package org.apache.payment.gateway.config.activeMq.listener;

/**
 * @author Rahul Goel created on 10/6/18
 */


public class MessageListener extends AbstractListener {

    public MessageListener(String topicName) {
        super(topicName);
    }

    @Override
    public void actionCreate(String var1, String var2) {

    }

    @Override
    public void actionUpdate(String var1, String var2) {

    }

    @Override
    public void actionDelete(String var1, String var2) {

    }

    @Override
    public void actionDefault(String var1, String var2) {

    }
}
