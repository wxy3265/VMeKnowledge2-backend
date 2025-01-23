package com.vmeknowledge.service.impl;

import com.vmeknowledge.mapper.UserAccountMapper;
import com.vmeknowledge.pojo.UserAccount;
import com.vmeknowledge.pojo.UserInformation;
import com.vmeknowledge.service.UserAccountService;
import com.vmeknowledge.utils.BCryptPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Override
    public UserAccount login(UserAccount account) {
        UserAccount newUserAccount = userAccountMapper.selectByUsername(account.getUsername());
        if (BCryptPasswordUtil.verifyPassword(account.getPassword(),newUserAccount.getPassword())) {
            return newUserAccount;
        }
        else return null;

    }

    @Override
    public UserAccount selectByUsername(String username) {
        return userAccountMapper.selectByUsername(username);
    }

    //事务管理，所有异常都回退
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserAccount account) {
        //对密码进行加密
        String password = BCryptPasswordUtil.encryptPassword(account.getPassword());
        account.setPassword(password);
        userAccountMapper.addAccount(account);
        UserInformation userInfo = new UserInformation();
        userInfo.setUserId(account.getId());
        userAccountMapper.addInformation(userInfo);
    }
}
