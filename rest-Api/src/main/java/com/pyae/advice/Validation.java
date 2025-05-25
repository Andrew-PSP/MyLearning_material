package com.pyae.advice;

import java.time.LocalDate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.pyae.entity.Customer;
import com.pyae.exception.ValidationUserException;

@Aspect
@Configuration
public class Validation {

	@Before("execution(* com.pyae.service.UserService.create(..)) && args(customer) )")
	public void ValidationUser(Customer customer) {
		if(!StringUtils.hasLength(customer.getName())) {
			throw new ValidationUserException("Please Enter your Name");
		}
		if(customer.getDob().isAfter(LocalDate.now())) {
			throw new ValidationUserException("Please Enter Valid Date of Birth");
		}
	}	
	
	
	
//	@Before("@annotation(com.pyae.annotation.ValidateUser)")
//	public void ValidationUser(JoinPoint joinPoint) {
//		for(Object arg : joinPoint.getArgs()) {
//			if(arg instanceof Customer customer) {
//				if(!StringUtils.hasLength(customer.getName())) {
//					throw new ValidationUserException("Please Enter your Name");
//				}
//				if(customer.getDob().isAfter(LocalDate.now())) {
//					throw new ValidationUserException("Please Enter Valid Date of Birth");
//				}
//			}
//		}
//		
//		
//	}
}
