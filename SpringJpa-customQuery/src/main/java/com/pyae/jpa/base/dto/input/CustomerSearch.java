package com.pyae.jpa.base.dto.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.pyae.jpa.entity.Customer;
import com.pyae.jpa.entity.Customer.Gender;
import com.pyae.jpa.entity.Customer_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record CustomerSearch(String name, String phone, String email, Gender gender) {
	
	public Predicate[] where(CriteriaBuilder cb, Root<Customer> root) {
		
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(root.get(Customer_.name), name));
		}
		
		if(StringUtils.hasLength(phone)) {
			list.add(cb.like(root.get(Customer_.phone), phone));
		}
		
		if(StringUtils.hasLength(email)) {
			list.add(cb.like(root.get(Customer_.email), email));
		}
		
		if(null != gender) {
			list.add(cb.equal(root.get(Customer_.gender), gender));
		}
	
		return list.toArray(Predicate[] :: new);
	}

}
