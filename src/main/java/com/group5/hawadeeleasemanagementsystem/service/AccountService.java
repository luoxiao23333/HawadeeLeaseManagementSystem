package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.AccountDao;
import com.group5.hawadeeleasemanagementsystem.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    public void addNewAccount(String username, String password, String phone){
        Account account = new Account();
        account.setPhone(phone);
        account.setUsername(username);
        account.setPassword(password);
        accountDao.addAccount(account);
    }
}
