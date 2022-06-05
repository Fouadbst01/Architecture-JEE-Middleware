package com.example.digitalbanking.repositories;

import com.example.digitalbanking.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    //Customer findById(String customerId);
    //List<Customer> findCustomerByName(String Name,Pageable pageable);
    @Query("select c from Customer as c where c.name like :kw")
    Page<Customer> findCustomers(@Param("kw") String keyword, Pageable pageable);
}
