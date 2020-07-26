package com.project.email.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Reisen
 */
@Mapper
@DS(value = "db1")
public interface BaseMapper {

    @Select("select username from user")
    public List<String> queryAll();
}
