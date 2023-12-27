package com.example.accountservice;

import com.example.accountservice.clients.CustomerResTClient;
import com.example.accountservice.entities.Account;
import com.example.accountservice.enums.AccountType;
import com.example.accountservice.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication  {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AccountRepository accountRepository , CustomerResTClient customerResTClient){
		return args ->{
			customerResTClient.AllCustomers().forEach(c->{
				Account account = Account.builder()
						.accountId(UUID.randomUUID().toString())
						.balance(Math.random()*1000)
						.currency("MAD")
						.type(AccountType.CURRENT_ACCOUNT)
						.createAt(LocalDate.now())
						.customerId(c.getId())
						.build();

				Account account2 = Account.builder()
						.accountId(UUID.randomUUID().toString())
						.balance(Math.random()*67434)
						.currency("MAD")
						.type(AccountType.SAVING_ACCOUNT)
						.createAt(LocalDate.now())
						.customerId(c.getId())
						.build();

				accountRepository.save(account);
				accountRepository.save(account2);
			});

		};
	}

}
