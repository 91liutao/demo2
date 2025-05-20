package com.itliutao.mapper;

import com.itliutao.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ClazzMapper {

    public List<Clazz> list();
    @Delete("delete from clazz where id=#{id}")
    void deleteById(Integer id);
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) values(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);
    @Select("select c.*, e.name as master_name from clazz c left join emp e on c.master_id = e.id where c.id=#{id}")
    Clazz getById(Integer id);
    @Insert("update clazz set name=#{name}, room=#{room}, begin_date=#{beginDate}, end_date=#{endDate}, master_id=#{masterId}, subject=#{subject}, update_time=#{updateTime} where id=#{id}")
    void update(Clazz clazz);
}
