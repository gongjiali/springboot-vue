package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    // 查询用户名
    @Select("select password from user where name=#{name}")
    User selectByName(String name);

    @Update("update user set password = #{newPass} where user_id = #{user_id}")
    int updatePass(Map<String, Object> map);
}
