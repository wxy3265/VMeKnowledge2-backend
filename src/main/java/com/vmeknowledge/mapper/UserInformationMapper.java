package com.vmeknowledge.mapper;

import com.vmeknowledge.pojo.UserInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInformationMapper {

    void init();

    void addInformation(UserInformation userInfo);

}
