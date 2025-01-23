package com.vmeknowledge.service;

import com.vmeknowledge.pojo.UserAccount;

public interface UserAccountService {

    UserAccount login(UserAccount account);

    UserAccount selectByUsername(String username);

    void register(UserAccount account);
}
