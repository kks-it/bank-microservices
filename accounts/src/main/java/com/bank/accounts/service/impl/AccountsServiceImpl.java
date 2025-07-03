package com.bank.accounts.service.impl;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.constants.CustomerConstants;
import com.bank.accounts.dto.AccountDto;
import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.entity.Account;
import com.bank.accounts.entity.Customer;
import com.bank.accounts.exception.CustomerAlreadyExistsException;
import com.bank.accounts.exception.ResourceNotFoundException;
import com.bank.accounts.mapper.AccountMapper;
import com.bank.accounts.mapper.CustomerMapper;
import com.bank.accounts.repository.AccountsRepository;
import com.bank.accounts.repository.CustomersRepository;
import com.bank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomersRepository customersRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> exitingCustomer = customersRepository.findByMobileNumber(customerDto.getMobileNumber());
        exitingCustomer.ifPresent((customer) -> {
                    throw new CustomerAlreadyExistsException(CustomerConstants.ALREADY_EXISTS + customerDto.getMobileNumber());
                }
        );

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer = customersRepository.save(customer);


        Account account = new Account();
        long accountNumber = 1000000000L + new Random().nextInt(900000000);

        account.setAccountNumber(accountNumber);
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);
        account.setCustomerId(savedCustomer.getCustomerId());
        Account savedAccount = accountsRepository.save(account);

    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customersRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Account account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() ->
                new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
        AccountDto accountDto = AccountMapper.mapToAccountDto(account, new AccountDto());
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(accountDto);
        return customerDto;


    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountsDto = customerDto.getAccountDto();
        if(accountsDto !=null ){
            Account accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountMapper.mapToAccount(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customersRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customersRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }


    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customersRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customersRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
