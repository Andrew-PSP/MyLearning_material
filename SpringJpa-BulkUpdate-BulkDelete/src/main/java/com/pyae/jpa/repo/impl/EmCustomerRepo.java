package com.pyae.jpa.repo.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.pyae.jpa.entity.Customer;
import com.pyae.jpa.repo.CustomerRepoBase;

@Repository
@Profile("em")
public class EmCustomerRepo extends CustomerRepoBase{

	@Override
	public int update(int id, String name, String phone) {
		var entity = em.find(Customer.class, id);
		if(null !=entity) {
			entity.setName(name);
			entity.setPhone(phone);
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(int id) {
		var entity = em.find(Customer.class, id);
		if(null != entity) {
			em.remove(entity);
			return 1;
		}
		return 0;
	}

}
