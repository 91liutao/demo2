package com.itliutao.service;

import com.itliutao.pojo.PageResult;
import com.itliutao.pojo.student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    PageResult<student> findAll(Integer page, Integer pageSize);

    void deleteById(Integer id);

    void insert(student student);

    student getById(Integer id);

    void update(student student);

    void violationScore(Integer id, Integer score);
}
