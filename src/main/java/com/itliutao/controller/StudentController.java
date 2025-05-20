package com.itliutao.controller;

import com.itliutao.pojo.PageResult;
import com.itliutao.pojo.Result;
import com.itliutao.pojo.student;
import com.itliutao.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "1")Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("查询所有学生，分页："+page+","+pageSize);
        PageResult<student> pageResult = studentService.findAll(page,pageSize);
        return Result.success(pageResult);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        studentService.deleteById(id);
        return Result.success();
    }
    @PostMapping
    public Result insert(@RequestBody student student){
        studentService.insert(student);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        student student = studentService.getById(id);
        return Result.success(student);
    }
    @PutMapping
    public Result update(@RequestBody student student){
        studentService.update(student);
        return Result.success();
    }
    @PutMapping("/violation/{id}/{score}")
    public Result violationScore(@PathVariable Integer id, @PathVariable Integer score) {
        studentService.violationScore(id, score);
        return Result.success();
    }
}
