package com.pyae.jpa.repo;

import java.util.UUID;

import com.pyae.jpa.base.BaseRepository;
import com.pyae.jpa.entity.Customer;

public interface CustomerRepo extends BaseRepository<Customer, UUID> {

}
