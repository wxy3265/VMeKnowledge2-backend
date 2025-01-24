package com.vmeknowledge.service;

import com.vmeknowledge.pojo.UserAccount;
import com.vmeknowledge.pojo.UserInformation;

public interface UserAccountService {

    UserAccount login(UserAccount account);

    UserAccount selectByUsername(String username);

    void register(UserAccount account);

    void update(UserInformation info);
}
