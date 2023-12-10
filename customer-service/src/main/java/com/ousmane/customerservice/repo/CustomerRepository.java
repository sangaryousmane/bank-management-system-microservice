package com.ousmane.customerservice.repo;

import com.ousmane.customerservice.entities.Customer;
import com.ousmane.customerservice.external.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {



}
