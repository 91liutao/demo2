package com.itliutao.service;

import com.itliutao.pojo.Clazz;
import com.itliutao.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ClazzService {

    PageResult<Clazz> Page(Integer page, Integer pageSize);

    void deleteById(Integer id);

    void insert(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);
}
