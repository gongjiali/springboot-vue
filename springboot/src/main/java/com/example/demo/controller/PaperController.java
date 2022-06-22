package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Paper;
import com.example.demo.entity.User;
import com.example.demo.mapper.PaperMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/paper")
public class PaperController {
    @Resource
    PaperMapper paperMapper;

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Paper>  wrapper = Wrappers.<Paper>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(Paper::getContent, search);
        }
        wrapper.ne(Paper::getStatus,0);
        Page<Paper> paperpage = paperMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);

        return Result.success(paperpage);
    }

    @PostMapping("/assign")
    public Result<?> assign(@RequestBody Paper paper) {
        paperMapper.updateById(paper);
        return Result.success();
    }

}



