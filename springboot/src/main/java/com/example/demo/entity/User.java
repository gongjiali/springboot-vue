package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@TableName("user")
@Data
public class User {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer user_id;
    private String password;
    private String name;
    private String email;
    private String nick_name;
    private Integer id_card;
    private Integer age;
    private Integer sex;
    private Integer phone_number;

}
