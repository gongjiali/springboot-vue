package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@TableName("specialist")
@Data
@Builder
public class Reviewer {

    @TableId(value = "specialist_id", type = IdType.AUTO)
    private Integer specialistId;
    private String password;
    private String name;
    private String email;
    private Integer age;
    private Integer sex;
    private String nickName;
    private String phoneNumber;
    private Integer status;
    private Integer category;
}
