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
import java.util.Map;


@RestController //返回josn数据的控制器
@RequestMapping("/user")
public class userecontroller {

    @Resource
    UserMapper userMapper;

    //gong

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User>  wrapper = Wrappers.<User>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(User::getNickName, search);
        }
        wrapper.eq(User::getRole,1);
        Page<User> userpage = userMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        //Page<User> userPage = userMapper.findPage(new Page<>(pageNum, pageSize), search);
        return Result.success(userpage);
    }


    @PostMapping
    public Result<?> save(@RequestBody User user){//把前台传来的josn对象，映射成User实体
        userMapper.insert(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getName,user.getName()).eq(User::getPassword,user.getPassword()).eq(User::getRole,user.getRole()));
        if(res ==null){
            return Result.error("-1,","用户名或密码错误");
        }
        return Result.success(res);
    }
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getName, user.getName()));
        if(res !=null){
            return Result.error("-1,","用户名重复");
        }
        userMapper.insert(user);
        return Result.success();
    }
    @PutMapping
    public Result<?> update(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();
    }
    @PutMapping("/pass")
    public Result<?> pass(@RequestBody Map<String, Object> map) {
        User user = userMapper.selectById((Integer) map.get("userId"));
        if (user== null) {
            return Result.error("-1", "未找到用户");
        }
        if (!user.getPassword().equals(map.get("password").toString())) {
            return Result.error("-1", "密码错误");
        }
        map.put("newPass", (map.get("newPass").toString()));
        userMapper.updatePass(map);
        return Result.success();
    }

    @PostMapping("/deleteacc")
    public Result<?> deleteacc(@RequestBody User user){//把前台传来的josn对象，映射成User实体
        userMapper.deleteById(user.getUserId());
        return Result.success();
    }
}
