package com.pyae;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.pyae.event.listener","com.pyae.event.publisher"})
public class ApplicationConfig {

}
