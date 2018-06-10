package org.apache.payment.gateway.config.activeMq.listener;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.payment.gateway.config.activeMq.ActiveMqUtil;
import org.apache.payment.gateway.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class ApplicationContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	ActiveMqUtil activeMqUtil;

	private List<Pair<Set<String>, AbstractListener>> listenerList;
	private static Logger logger = LogManager.getLogger(ApplicationContextRefreshListener.class);
	
	public ApplicationContextRefreshListener() {
		this.listenerList = new LinkedList<Pair<Set<String>, AbstractListener>>();
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("Application Event processing starts.");
		List<Pair<Set<String>, AbstractListener>> listeners = this.getListenerList();
		if(Utility.isNonEmpty(listeners)) {
			logger.info("ListenersList is non-empty -> Size - " + listeners.size());
			for(Pair<Set<String>, AbstractListener> pair : listeners) {
				logger.info("Subscribing " + pair.getKey() + " topics to listenersClass - " + pair.getValue().getClass().getSimpleName());
				activeMqUtil.subscribe(pair.getKey(), pair.getValue());
			}
			logger.info("Subscribing Completed on " + listeners.size() + " listeners.");
		}
		logger.info("Application Event processing ends.");
	}

	public synchronized List<Pair<Set<String>, AbstractListener>> getListenerList() {
		return new LinkedList<Pair<Set<String>,AbstractListener>>(this.listenerList);
	}

	public synchronized void setListenerList(List<Pair<Set<String>, AbstractListener>> listenerList) {
		this.listenerList = listenerList;
	}
	
	public synchronized boolean addListener(Set<String> topics, AbstractListener listener) {
		return this.listenerList.add(Pair.of(topics, listener));
	}
	
}
