package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Paper;
import com.example.demo.mapper.PaperMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/paper")
public class PaperController {
    @Resource
    PaperMapper paperMapper;

    @PostMapping
    public Result<?> save(@RequestBody Paper paper){
        if(paper.getStatus() ==null){
            paper.setStatus(0);
        }
        paperMapper.insert(paper);
        return Result.success();
    }
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Paper>  wrapper = Wrappers.<Paper>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(Paper::getAuthor, search);
        }
        Page<Paper> Paperpage = paperMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);

        return Result.success(Paperpage);
    }
    @PutMapping
    public Result<?> update(@RequestBody Paper paper){
        paperMapper.updateById(paper);
        return Result.success();
    }
    @DeleteMapping("/{paperId}")
    public Result<?> delete(@PathVariable Long paperId){
        paperMapper.deleteById(paperId);
        return Result.success();

    }


    @GetMapping("/Assigned")
    public Result<?> findassigned(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Paper>  wrapper = Wrappers.<Paper>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(Paper::getContent, search);
        }
        wrapper.ne(Paper::getStatus,0);
        Page<Paper> Paperpage = paperMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);

        return Result.success(Paperpage);
    }



    @PostMapping("/assign")
    public Result<?> assign(@RequestBody Paper paper) {
        paperMapper.updateById(paper);
        return Result.success();
    }

    @GetMapping("/userPaper")
    public Result<?> UserPaper(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "") Integer search){

        Page<Paper> PaperPage =paperMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Paper>lambdaQuery().like(Paper::getUserId,search));
        return Result.success(PaperPage);
    }

    @GetMapping("/reviewPaper")
    public Result<?> ReviewPaper(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "") Integer search){
        Page<Paper> PaperPage =paperMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Paper>lambdaQuery().like(Paper::getSpecialistId,search));
        return Result.success(PaperPage);
    }

}



