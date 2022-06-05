package com.example.digitalbanking.mappers;

import com.example.digitalbanking.dtos.CardDTO;
import com.example.digitalbanking.dtos.CurrentAccountDTO;
import com.example.digitalbanking.dtos.CustomerDTO;
import com.example.digitalbanking.entities.Card;
import com.example.digitalbanking.entities.CurrentAccount;
import com.example.digitalbanking.entities.Customer;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        //pour tester dans l'interface mobile
        customer.getBankAccounts().get(0);
        return customerDTO;
    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        /*if(customerDTO.getCards()!=null){
            List<Card> cars = customerDTO.getCards().stream().map(card->fromCardDTO(card)).collect(Collectors.toList());
            customer.setCards(cars);
        }*/
        BeanUtils.copyProperties(customerDTO,customer);
        return  customer;
    }

    public CardDTO fromCard(Card card){
        CardDTO cardDTO=new CardDTO();
        BeanUtils.copyProperties(card,cardDTO);
        return  cardDTO;
    }
    public Card fromCardDTO(CardDTO cardDTO){
        Card card=new Card();
        BeanUtils.copyProperties(cardDTO,card);
        return card;
    }

    public CurrentAccountDTO fromCurrentAccount(CurrentAccount currentAccount){
        CurrentAccountDTO currentAccountDTO =new CurrentAccountDTO();
        currentAccountDTO.setCards(currentAccount.getCards().stream().map(card -> fromCard(card)).collect(Collectors.toList()));
        currentAccountDTO.setBalance(currentAccount.getBalance());
        return currentAccountDTO;
    }
}
