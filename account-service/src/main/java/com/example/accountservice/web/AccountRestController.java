package com.example.accountservice.web;

import com.example.accountservice.clients.CustomerResTClient;
import com.example.accountservice.entities.Account;
import com.example.accountservice.models.Customer;
import com.example.accountservice.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountRestController {
    AccountRepository accountRepository;
    CustomerResTClient customerResTClient;

    @GetMapping("/accounts")
    public List<Account> accountList(){

        List<Account> listAccounts = accountRepository.findAll();
        listAccounts.forEach(c->{
            c.setCustomer(customerResTClient.findCustomerById(c.getCustomerId()));
        });

        return listAccounts;
    }

    @GetMapping("/accounts/{id}")
    public Account getAccountById(@PathVariable  String id ){

        Account account = accountRepository.findById(id).get();
        Customer customer = customerResTClient.findCustomerById(account.getCustomerId());
        System.out.println(customer);
        account.setCustomer(customer);
        return  account;
    }
}

