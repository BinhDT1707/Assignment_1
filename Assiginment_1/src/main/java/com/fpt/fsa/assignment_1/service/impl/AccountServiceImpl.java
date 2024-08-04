package com.fpt.fsa.assignment_1.service.impl;

import com.fpt.fsa.assignment_1.config.PasswordEncode;
import com.fpt.fsa.assignment_1.controller.exception.UnAuthenticationExeption;
import com.fpt.fsa.assignment_1.entity.Account;
import com.fpt.fsa.assignment_1.model.request.LoginRequest;
import com.fpt.fsa.assignment_1.model.response.LoginResponse;
import com.fpt.fsa.assignment_1.repositories.AccountRepository;
import com.fpt.fsa.assignment_1.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncode passwordEncode;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Optional<Account> accountEntity = accountRepository.findAccountByAccountName(accountName);
        if (accountEntity.isPresent()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE USER");
            return new User(accountEntity.get().getAccountName(), accountEntity.get().getPassword(),
                    true, true, true, true,
                    Collections.singleton(grantedAuthority));
        }
        return null;
    }

    @Override
    public LoginResponse login(LoginRequest payload) {
        Optional<Account> accountEntity = accountRepository.findAccountByAccountName(payload.getAccountName());
        if (accountEntity.isEmpty()){
            throw new UnAuthenticationExeption();
        }

        if ((!passwordEncode.bCryptPasswordEncoder().matches(payload.getPassword(), accountEntity.get().getPassword()))){
            throw new UnAuthenticationExeption();
        }

        LoginResponse response = new LoginResponse();
        response.setAccount(accountEntity.get());
        return response;
    }

    @Override
    public LoginResponse register(LoginRequest payload) {
        // 1. User co hay chua
        Optional<Account> accountEntity= accountRepository.findAccountByAccountName(payload.getAccountName());
        if (accountEntity.isPresent()) {
            return null;
        }
        Account account = new Account();
        account.setAccountName(payload.getAccountName());
        account.setPassword(passwordEncode.bCryptPasswordEncoder().encode(payload.getPassword()));
        account = accountRepository.save(account);

        LoginResponse response = new LoginResponse();
        response.setAccount(account);
        return response;
    }

    @Override
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncode.bCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

}

