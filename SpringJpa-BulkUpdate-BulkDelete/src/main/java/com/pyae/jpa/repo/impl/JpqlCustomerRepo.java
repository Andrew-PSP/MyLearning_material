package com.pyae.jpa.repo.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.pyae.jpa.repo.CustomerRepoBase;

@Repository
@Profile("jpql")
public class JpqlCustomerRepo extends CustomerRepoBase{

	@Override
	public int update(int id, String name, String phone) {
		var query = em.createNamedQuery("Customer.update");
		query.setParameter("id", id);
		query.setParameter("name", name);
		query.setParameter("phone", phone);
		return query.executeUpdate();
	}

	@Override
	public int delete(int id) {
		var query = em.createNamedQuery("Customer.delete");
		query.setParameter("id", id);
		return query.executeUpdate();
	}

}
