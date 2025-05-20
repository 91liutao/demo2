package com.itliutao.mapper;

import com.itliutao.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp login(Emp emp);
}
