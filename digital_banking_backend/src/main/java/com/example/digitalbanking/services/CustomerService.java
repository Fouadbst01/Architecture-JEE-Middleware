package com.example.digitalbanking.services;

import com.example.digitalbanking.dtos.CustomerDTO;
import com.example.digitalbanking.exceptions.CustomerNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    Map<String,Object> getCustomers(int page, int size, String keyword);
    CustomerDTO gestCustomer(String idCustomer) throws CustomerNotFoundException;
    void deleteCustomer(String idCustomer);
}
