package com.vmeknowledge.mapper;

import com.vmeknowledge.pojo.UserAccount;
import com.vmeknowledge.pojo.UserInformation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserAccountMapper {
    @Select("select * from user_account where username = #{username} and password = #{password}")
    UserAccount getByUsernameAndPassword(UserAccount account);

    @Select("select * from user_account where username = #{username}")
    UserAccount selectByUsername(String username);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user_account (username, password) values (#{username}, #{password})")
    void addAccount(UserAccount account);


    void addInformation(UserInformation userInfo);
}
