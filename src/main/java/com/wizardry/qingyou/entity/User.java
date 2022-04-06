package com.wizardry.qingyou.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("users")
public class User implements Serializable {

    // 主键，采用自增策略
    @TableId(type = IdType.AUTO)
    private Long id;
    private String psw;
    private String uname;
    private String phone;
    private String email;
    // 时间自动填充
    @TableField(fill = FieldFill.INSERT)
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
    //乐观锁

}
