package com.example.digitalbanking;

import com.example.digitalbanking.dtos.CardDTO;
import com.example.digitalbanking.dtos.CustomerDTO;
import com.example.digitalbanking.entities.Customer;
import com.example.digitalbanking.enums.CardDesigne;
import com.example.digitalbanking.enums.CardType;
import com.example.digitalbanking.repositories.CardRepository;
import com.example.digitalbanking.repositories.CustomerRepository;
import com.example.digitalbanking.secutiy.entities.AppUser;
import com.example.digitalbanking.secutiy.entities.AppRole;
import com.example.digitalbanking.secutiy.service.SecurityService;
import com.example.digitalbanking.services.BankAccountService;
import com.example.digitalbanking.services.CardService;
import com.example.digitalbanking.services.CustomerService;
import com.github.javafaker.App;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Locale;
import java.util.UUID;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@Slf4j
public class DigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(CustomerService customerService, BankAccountService bankAccountService, CardService cardService){
        Faker faker=new Faker(Locale.CANADA_FRENCH);
        Name name=  faker.name();
        return (args)->{
            for(int i=0;i<20;i++){
                CustomerDTO customer1 = new CustomerDTO();
                customer1.setId(UUID.randomUUID().toString());
                customer1.setEmail(faker.internet().emailAddress());
                customer1.setName(name.firstName());
                assert customerService != null;
                customerService.saveCustomer(customer1);
            }





            CustomerDTO customerDTO=new CustomerDTO();
            customerDTO.setName("fouad");
            customerDTO.setEmail("fouadelbssita@gmail.com");

            CustomerDTO savedCustomer = customerService.saveCustomer(customerDTO);

            bankAccountService.saveCurrentBankAccout(520894142d,0,savedCustomer.getId());

            CardDTO cardDTO= new CardDTO();
            cardDTO.setCardNum("1458 7845 9568 7854");
            cardDTO.setCardDesigne(CardDesigne.BLUE);
            cardDTO.setCardType(CardType.MASTERCARD);
            cardService.save(cardDTO);


            CardDTO cardDTO2= new CardDTO();
            cardDTO2.setCardNum("1458 7845 9568 7854");
            cardDTO2.setCardDesigne(CardDesigne.BLUE);
            cardDTO2.setCardType(CardType.MASTERCARD);

            //customerDTO.setCards();
            /*for(CustomerDTO customer : customerService.getCustomers()){

            }*/
            /*Customer savedCustom = bankAccountService.saveCustomer(customer1);
            BankAccount bankAccount = bankAccountService.saveCurrentBankAccout(1000000,500000,savedCustom.getId());
            bankAccountService.saveSavingBankAccout(100000000,5.3,savedCustom.getId());*/

            /*try {
                bankAccountService.debit(bankAccount.getId(),10000,"test");
            } catch (BalanceInsuffisanteException e) {
                throw new RuntimeException(e);
            }*/
        };
    }
    @Bean
    CommandLineRunner commandLineRunner1(SecurityService securityService, CustomerRepository customerRepo){
        return (args)->{
            AppUser savedUser = securityService.saveUser("fouad@gmail.com","1234","1234");
            AppRole savedRole = securityService.saveRole("USER","test");
            securityService.addRoleToUser(savedUser.getUserName(),savedRole.getName());
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
