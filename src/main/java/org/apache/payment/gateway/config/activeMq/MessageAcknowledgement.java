package org.apache.payment.gateway.config.activeMq;

/**
 * @author Rahul Goel created on 10/6/18
 */

@FunctionalInterface
public interface MessageAcknowledgement {
    void acknowledge();
}
