package com.example.repository;

import com.example.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tinoll on 2017. 1. 4..
 */
public interface CustomerRepositoryJpa extends JpaRepository<Customer, Integer>{

    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    Page<Customer> findAllOrderByName(Pageable pageable);
}
