package com.example.accountservice.clients;

import com.example.accountservice.models.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerResTClient {

    @CircuitBreaker(name = "customerService",fallbackMethod = "getDefaultCustomer")
    @GetMapping("/customers/{id}")
    Customer findCustomerById(@PathVariable Long id);


    @CircuitBreaker(name = "customerService",fallbackMethod = "getAllCustomers")
    @GetMapping("/customers")
    List<Customer> AllCustomers();

    default Customer getDefaultCustomer(Long id,Exception exception){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFistName("not available");
        customer.setLastName("not available");
        customer.setEmail("not available");
        return customer;
    }
    default List<Customer> getAllCustomers(){
        return List.of();
    }



}
