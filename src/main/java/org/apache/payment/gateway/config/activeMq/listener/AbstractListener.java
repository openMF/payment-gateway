package org.apache.payment.gateway.config.activeMq.listener;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractListener extends AbstractMessageListener {
	
	private Set<String> topics;

    @Autowired
    private ApplicationContextRefreshListener applicationContextRefreshListener;
    
    ApplicationContextRefreshListener getApplicationContextRefreshListener() {
		return applicationContextRefreshListener;
	}

	void setApplicationContextRefreshListener(ApplicationContextRefreshListener applicationContextRefreshListener) {
		this.applicationContextRefreshListener = applicationContextRefreshListener;
	}
	
	public AbstractListener(String topicName) {
		Set<String> topics = new HashSet<String>();
		topics.add(topicName);
		this.topics = topics;
	}

	public AbstractListener(Set<String> topics) {
        this.topics = topics;
    }
	
	@PostConstruct
	public void postConstruct() {
		this.applicationContextRefreshListener.addListener(topics, this);
	}
	
}
