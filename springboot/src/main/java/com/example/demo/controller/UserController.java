package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserMapper userMapper;


    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User>  wrapper = Wrappers.<User>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(User::getNickName, search);
        }
        Page<User> userpage = userMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        //Page<User> userPage = userMapper.findPage(new Page<>(pageNum, pageSize), search);
        return Result.success(userpage);
    }

}
