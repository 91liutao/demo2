package com.itliutao.mapper;

import com.itliutao.pojo.Emp;
import com.itliutao.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    //原始方法
//    @Select("select count(*) from emp e left join dept d on e.dept_id=d.id")
//    public Long count();
//    @Select("select  e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc limit #{start},#{end}")
//    public List<Emp> list(Integer start, Integer end);
    //@Select("select  e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc")
    //public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    List<Emp> list(EmpQueryParam queryParam);
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) values(#{username}" +
            ",#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate}," +
            "#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);


    void update(Emp emp);
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobDats();

    List<Map<String,Object>> countEmpGender();


}
