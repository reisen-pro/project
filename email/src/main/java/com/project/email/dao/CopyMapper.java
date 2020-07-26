package com.project.email.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@DS(value = "db2")
@Mapper
public interface CopyMapper {
    @Select("select username from user")
    public List<String> queryAll();
}
