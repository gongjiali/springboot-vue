package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("paper")
@Data
public class Paper {

    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId;
    private String title;
    private String content;
    private String author;
    private String category;
    private String tag;
    private Integer userId;
    private Integer specialistId;
    private Integer status;
    private String submitDate;

    private String filePath;
    private String checkDate;
    private String review;
    private Integer status1;

}
