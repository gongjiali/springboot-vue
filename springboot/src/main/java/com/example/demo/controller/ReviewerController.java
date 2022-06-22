package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Reviewer;
import com.example.demo.entity.User;
import com.example.demo.mapper.ReviewerMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/specialist")
public class ReviewerController {
    @Resource
    ReviewerMapper reviewerMapper;

    @PostMapping
    public Result<?> save(@RequestBody Reviewer reviewer){
        reviewerMapper.insert(reviewer);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Reviewer>  wrapper = Wrappers.<Reviewer>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(Reviewer::getNickName, search);
        }
        Page<Reviewer> reviewerpage = reviewerMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        //Page<User> userPage = userMapper.findPage(new Page<>(pageNum, pageSize), search);
        return Result.success(reviewerpage);
    }

    @GetMapping("/bycat")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") Integer cat){
        LambdaQueryWrapper<Reviewer>  wrapper = Wrappers.<Reviewer>lambdaQuery();
        wrapper.eq(Reviewer::getCategory,cat);
        Page<Reviewer> reviewerpage = reviewerMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(reviewerpage);
    }
}
