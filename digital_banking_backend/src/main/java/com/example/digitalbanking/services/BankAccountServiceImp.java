package com.example.digitalbanking.services;

import com.example.digitalbanking.entities.*;
import com.example.digitalbanking.enums.OperationType;
import com.example.digitalbanking.exceptions.AccountNotFoundException;
import com.example.digitalbanking.exceptions.BalanceInsuffisanteException;
import com.example.digitalbanking.exceptions.CardNotFound;
import com.example.digitalbanking.exceptions.CustomerNotFoundException;
import com.example.digitalbanking.mappers.BankAccountMapperImpl;
import com.example.digitalbanking.repositories.BankAccountRepository;
import com.example.digitalbanking.repositories.CardRepository;
import com.example.digitalbanking.repositories.CustomerRepository;
import com.example.digitalbanking.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BankAccountServiceImp implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private final OperationRepository operationRepository;
    private final CardRepository cardRepository;
    private final BankAccountMapperImpl bankAccountMapper;


    @Override
    public BankAccount saveCurrentBankAccout(double balance, double overDraft, String customerId) throws CustomerNotFoundException {
        log.info("create account for customer :  {}", customerId);
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException("Customer Not found")
        );
        CurrentAccount bankAccount = new CurrentAccount();
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setBalance(balance);
        bankAccount.setCustomer(customer);
        bankAccount.setCreatedAt(new Date());
        bankAccount.setOverDraft(overDraft);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        return savedBankAccount;
    }

    @Override
    public BankAccount saveSavingBankAccout(double balance, double interestRate, String customerId) throws CustomerNotFoundException {
        log.info("create account for customer :  {}", customerId);
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException("Customer Not found")
        );
        SavingAccount bankAccount = new SavingAccount();
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setBalance(balance);
        bankAccount.setCustomer(customer);
        bankAccount.setCreatedAt(new Date());
        bankAccount.setInterestRate(interestRate);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        return savedBankAccount;
    }

    @Override
    public BankAccount getBankAccount(String accountId) throws AccountNotFoundException {
        log.info("Get account : {}",accountId);
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(
                ()-> new AccountNotFoundException("Account Not Found")
        );
        return bankAccount;
    }

    @Override
    public void debit(String accountId,double amount,  String description) throws BalanceInsuffisanteException, AccountNotFoundException {
        log.info("Debit account : {}",accountId);
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(
                ()-> new AccountNotFoundException("Account Not Found")
        );
        double balance = bankAccount.getBalance();
        if(balance<amount) throw new BalanceInsuffisanteException("Balance insuffisante");
        Operation operation = new Operation();
        operation.setBankAccount(bankAccount);
        operation.setAmount(amount);
        operation.setDate(new Date());
        operation.setOpType(OperationType.DEBIT);
        operationRepository.save(operation);
        bankAccount.setBalance(balance - amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credite(String accountId,double amount, String description) throws AccountNotFoundException {
        log.info("Credite account : {}",accountId);
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(
                ()-> new AccountNotFoundException("Account Not Found")
        );
        double balance = bankAccount.getBalance();
        Operation operation = new Operation();
        operation.setBankAccount(bankAccount);
        operation.setAmount(amount);
        operation.setDate(new Date());
        operation.setOpType(OperationType.CREDIT);
        //operationRepository.save(operation);
        bankAccount.getOperations().add(operation);
        bankAccount.setBalance(balance + amount);
        //bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String sourceAccountId, String destinationAccountId, double amount) throws BalanceInsuffisanteException, AccountNotFoundException {
            debit(sourceAccountId,amount,"Transfer to" + sourceAccountId);
            credite(destinationAccountId,amount,"Transfer from" + destinationAccountId);
    }

    @Override
    public void addCarrdTobankAccount(String idAccount, Long idCard) throws CardNotFound, AccountNotFoundException {
        Card c = cardRepository.findById(idCard).orElseThrow(()->new CardNotFound("card not found"));
        BankAccount bankAccount = bankAccountRepository.findById(idAccount).orElseThrow(()-> new AccountNotFoundException("account Not found"));
        bankAccount.getCars().add(c);
    }
}