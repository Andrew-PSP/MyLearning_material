package com.pyae.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class BeanFactoryPostProcessor implements org.springframework.beans.factory.config.BeanFactoryPostProcessor{

	//BeanFactoryPostProcessor is used to modify metadata configuration
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("bean factory is ready to use");
		
		var names = beanFactory.getBeanDefinitionNames();
		
		for(var name : names) {
			System.out.println(name);
		}
		
	}

}
