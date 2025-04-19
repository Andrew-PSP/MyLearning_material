package com.pyae.event.publisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.pyae.event.MyEvent;

@Component
public class MyEventPublisher implements ApplicationEventPublisherAware {
	//Using aware is not common because mostly, this action can be done with dependency injection
	private ApplicationEventPublisher publisher;
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher= applicationEventPublisher;
	}
	
	public void publish(MyEvent event) {
		publisher.publishEvent(event);
	}

}
