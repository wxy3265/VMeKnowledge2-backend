package com.vmeknowledge.service.impl;

import com.vmeknowledge.mapper.UserAccountMapper;
import com.vmeknowledge.pojo.UserAccount;
import com.vmeknowledge.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Override
    public UserAccount login(UserAccount account) {
        return userAccountMapper.getByUsernameAndPassword(account);
    }
}
