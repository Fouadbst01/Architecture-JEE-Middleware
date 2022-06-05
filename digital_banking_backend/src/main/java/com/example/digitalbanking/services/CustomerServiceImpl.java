package com.example.digitalbanking.services;

import com.example.digitalbanking.dtos.CustomerDTO;
import com.example.digitalbanking.entities.Customer;
import com.example.digitalbanking.exceptions.CustomerNotFoundException;
import com.example.digitalbanking.mappers.BankAccountMapperImpl;
import com.example.digitalbanking.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private BankAccountMapperImpl mapper;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = mapper.fromCustomerDTO(customerDTO);
        customer.setId(UUID.randomUUID().toString());
        Customer savedCustomer = customerRepository.save(customer);
        return mapper.fromCustomer(savedCustomer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer=mapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return mapper.fromCustomer(savedCustomer);
    }

    @Override
    public Map<String,Object> getCustomers(int page, int size,String keyword){
        Page<Customer> pagecu = customerRepository.findCustomers(keyword+"%",PageRequest.of(page,size));
        int totalePages = pagecu.getTotalPages();
        List<Customer> list = pagecu.stream().collect(Collectors.toList());
        List<CustomerDTO> listCDTO=list.stream().map(mapper::fromCustomer).collect(Collectors.toList());
        Map<String,Object> map = new HashMap<>();
        map.put("totalePage", totalePages);
        map.put("data",listCDTO);
        return map;
    }

    @Override
    public CustomerDTO gestCustomer(String idCustomer) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(idCustomer).orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        return mapper.fromCustomer(customer);
    }

    @Override
    public void deleteCustomer(String idCustomer) {
        customerRepository.deleteById(idCustomer);
    }
}
