package com.example.digitalbanking.web;

import com.example.digitalbanking.dtos.CustomerDTO;
import com.example.digitalbanking.entities.Customer;
import com.example.digitalbanking.exceptions.CustomerNotFoundException;
import com.example.digitalbanking.mappers.BankAccountMapperImpl;
import com.example.digitalbanking.services.BankAccountService;
import com.example.digitalbanking.services.CustomerService;
import com.example.digitalbanking.services.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/customers",produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
@CrossOrigin(value = "*",allowedHeaders = "*")
public class CustomerRestController {
    private CustomerServiceImpl customerService;
    @GetMapping
    public Map<String,Object> getAllCusstmer(@RequestParam(name = "keyword",defaultValue = "")  String keyword,
                                             @RequestParam(name="page", defaultValue = "0") int page,
                                             @RequestParam(name="size",defaultValue = "6") int size){
        return customerService.getCustomers(page,size,keyword);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO){
        CustomerDTO savedCustomer = customerService.saveCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }


    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String customerId){
        try {
            return ResponseEntity.ok().body(customerService.gestCustomer(customerId));
        } catch (CustomerNotFoundException e) {
            log.error(e.getMessage());
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping
    public ResponseEntity putCustomer(@RequestBody CustomerDTO customerDTO){
        CustomerDTO savedCustomerDTO = customerService.updateCustomer(customerDTO);
        return ResponseEntity.ok().body(savedCustomerDTO);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable String customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
