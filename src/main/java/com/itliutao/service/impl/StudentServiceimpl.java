package com.itliutao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itliutao.mapper.StudentMapper;
import com.itliutao.pojo.PageResult;
import com.itliutao.pojo.student;
import com.itliutao.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceimpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<student> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<student> list = studentMapper.list();
        Page<student> studentPage = (Page<student>) list;
        return new PageResult<student>(studentPage.getTotal(),studentPage.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    @Override
    public void insert(student student) {
        studentMapper.insert(student);
    }

    @Override
    public student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(student student) {
        studentMapper.update(student);
    }

    @Override
    public void violationScore(Integer id, Integer score) {
        studentMapper.violationScore(id, score);
    }
}
