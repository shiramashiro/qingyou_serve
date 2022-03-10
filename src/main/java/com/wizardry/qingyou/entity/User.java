package com.wizardry.qingyou.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User implements Serializable {

    private int id;
    private String psw;
    private String uname;
    private String phone;
    private String email;
    private Date createdDate;
    private Date birthday;
    private String signature;
    private String sex;
    private String location;
    private String constellation;
    private int age;
    private String avatar;
    private String occupation;
    private String salt;

}
