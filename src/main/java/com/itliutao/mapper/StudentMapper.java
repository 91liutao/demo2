package com.itliutao.mapper;

import com.itliutao.pojo.student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<student> list();
    @Delete("delete from student where id=#{id}")
    void deleteById(Integer id);
    @Insert("insert into student(name, no, gender, phone, idCard, isCollege, address, graduationDate, clazzId) values(#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{graduationDate}, #{clazzId})")
    void insert(student student);

    @Select("select * from student where id=#{id}")
    student getById(Integer id);
    @Update("update student set name=#{name}, no=#{no}, gender=#{gender}, phone=#{phone}, idCard=#{idCard}, isCollege=#{isCollege}, address=#{address}, graduationDate=#{graduationDate}, clazzId=#{clazzId} where id=#{id}")
    void update(student student);
    @Update("UPDATE student SET violationCount = violationCount + 1, violationScore = violationScore + #{score} WHERE id = #{id}")
    void violationScore(@Param("id") Integer id, @Param("score") Integer score);

    List<Map<String, Object>> countStudentDegree();

    List<Map<String, Object>> studentCountData();
}



