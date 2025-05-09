package com.pyae.jpa.repo.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.pyae.jpa.entity.Customer;
import com.pyae.jpa.entity.Customer_;
import com.pyae.jpa.repo.CustomerRepoBase;

@Repository
@Profile("criteria")
public class CriteraiCustomerRepo extends CustomerRepoBase {

	@Override
	public int update(int id, String name, String phone) {
		
		var cb = em.getCriteriaBuilder();
		var cq = cb.createCriteriaUpdate(Customer.class);
		
		var root = cq.from(Customer.class);
		
		cq.set(root.get(Customer_.name), name);
		cq.set(root.get(Customer_.phone), name);
		
		cq.where(cb.equal( root.get(Customer_.id), id));
		
		return em.createQuery(cq).executeUpdate();
	}

	@Override
	public int delete(int id) {
		var cb = em.getCriteriaBuilder();
		var cq = cb.createCriteriaDelete(Customer.class);
		
		var root = cq.from(Customer.class);
	
		cq.where(cb.equal( root.get(Customer_.id), id));
		
		return em.createQuery(cq).executeUpdate();
	}

}
