package com.pyae.jpa.base.dto.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.pyae.jpa.entity.Account_;
import com.pyae.jpa.entity.Customer;
import com.pyae.jpa.entity.Customer.Gender;
import com.pyae.jpa.entity.Customer_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record CustomerDto (
		UUID id,
		String name,
		String loginId,
		String phone,
		String email,
		LocalDate dob,
		LocalDateTime activatedAt,
		LocalDateTime expriedAt,
		Gender gender) {
	
	public static void select(CriteriaQuery<CustomerDto> cq, Root<Customer> root) {
		
		cq.multiselect(
				root.get(Customer_.id),
				root.get(Customer_.name),
				root.get(Customer_.account).get(Account_.loginId),
				root.get(Customer_.phone),
				root.get(Customer_.email),
				root.get(Customer_.dob),
				root.get(Customer_.account).get(Account_.activatedAt),
				root.get(Customer_.account).get(Account_.expiredAt)
				);
	
	}

}
