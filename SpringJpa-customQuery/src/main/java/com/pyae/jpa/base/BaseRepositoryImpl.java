package com.pyae.jpa.base;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
	
	@PersistenceContext
	private EntityManager em;

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	@Override
	public List<T> searchEntity(Function<CriteriaBuilder, CriteriaQuery<T>> queryFunc) {
		var cq = queryFunc.apply(em.getCriteriaBuilder());
		var query = em.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public Long count(Function<CriteriaBuilder, CriteriaQuery<Long>> queryFunc) {
		var cq = queryFunc.apply(em.getCriteriaBuilder());
		var query = em.createQuery(cq);
		return query.getSingleResult();
	}

	@Override
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		var cq = queryFunc.apply(em.getCriteriaBuilder());
		var query = em.createQuery(cq);
		return query.getResultList();
	}
	
	

}
