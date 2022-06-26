package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")//与数据库表的名字一一对应
@Data//lombok 简化代码，通过注解写get set
public class User {
//    @TableId(type = IdType.AUTO)//自增
//    private Integer id;
//    private String username;


//

//    private String sex;
//    private String address;

//    private Integer role;
//
@TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    private String password;
    private String name;
    private String email;
    private String nickName;

    private Integer age;
    private Integer sex; // 崔
    private String phoneNumber;
    private String address;//
    private Integer role;
    private String category;
}
