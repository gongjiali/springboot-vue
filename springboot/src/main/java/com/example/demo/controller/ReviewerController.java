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
@RequestMapping("/specialist")
public class ReviewerController {
    @Resource
    UserMapper reviewerMapper;

    @PostMapping("/register")
    public Result<?> register(@RequestBody User reviewer) {
        User res = reviewerMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getName, reviewer.getName()));
        if (res != null) {
            return Result.error("-1", "用户名重复");
        }
        reviewer.setRole(2);
        reviewerMapper.insert(reviewer);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User>  wrapper = Wrappers.<User>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(User::getNickName, search);
        }
        wrapper.eq(User::getRole,2);
        Page<User> reviewerpage = reviewerMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        //Page<User> userPage = userMapper.findPage(new Page<>(pageNum, pageSize), search);
        return Result.success(reviewerpage);
    }

    @GetMapping("/bycat")
    public Result<?> findbycat(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String cat){
        LambdaQueryWrapper<User>  wrapper = Wrappers.<User>lambdaQuery();
        wrapper.eq(User::getCategory,cat);
        wrapper.eq(User::getRole,2);
        Page<User> reviewerpage = reviewerMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(reviewerpage);
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        reviewerMapper.deleteById(id);
        return Result.success();
    }
}
