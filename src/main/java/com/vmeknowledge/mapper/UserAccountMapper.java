package com.vmeknowledge.mapper;

import com.vmeknowledge.pojo.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserAccountMapper {
    @Select("select * from user_account where username = #{username} and password = #{password}")
    UserAccount getByUsernameAndPassword(UserAccount account);
}
