package com.itliutao.service;

import com.itliutao.pojo.Emp;
import com.itliutao.pojo.EmpQueryParam;
import com.itliutao.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

//    PageResult<Emp> Page(Integer page, Integer pageSize,
//                         String name, Integer gender,
//                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                         @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end);
    PageResult<Emp> Page(EmpQueryParam queryParam);

    void save(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void update(Emp emp);
}
