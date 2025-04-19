package com.pyae.processor;

import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import com.pyae.bean.HelloBean;

@Component
public class BeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor {
	
	//BeanPostProcessor is used to set specific bean properties using beanName or etc. after the bean is created.
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		
		if(bean instanceof HelloBean helloBean) {
			helloBean.setName("hellobean Post Processor");
		}
		return bean;
	}
}
