package com.vmeknowledge.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation {
    private Integer id;
    private Integer userId;
    private String nickName;
    private String image;
    private String email;
    private Integer gender;
    private String personalSignature;
    private String phone;
}
