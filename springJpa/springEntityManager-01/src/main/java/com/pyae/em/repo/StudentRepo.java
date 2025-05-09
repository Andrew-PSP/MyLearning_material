 package com.pyae.em.repo;

import org.springframework.stereotype.Repository;

import com.pyae.em.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class StudentRepo {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public Student create(Student student) {
		em.persist(student);
		return student;
	}
	
	@Transactional
	public Student modify(Student student) {
		return em.merge(student);
	}
}
