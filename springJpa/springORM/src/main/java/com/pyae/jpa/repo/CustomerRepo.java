package com.pyae.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pyae.jpa.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
