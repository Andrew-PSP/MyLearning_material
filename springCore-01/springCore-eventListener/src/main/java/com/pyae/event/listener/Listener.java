package com.pyae.event.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.pyae.event.MyEvent;

@Component
public class Listener {
	//When The event is happened,listener does the instruction you give.
	@EventListener
	void listen(ApplicationContextEvent event) {
		System.out.println(event.getClass().getSimpleName());
	}
	
	@EventListener
	void listenEvent(MyEvent event) {
		System.out.println(event);
	}

}
