package com.itliutao.controller;

import com.itliutao.anno.Log;
import com.itliutao.pojo.Dept;
import com.itliutao.pojo.Result;
import com.itliutao.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;
    @Log
    @GetMapping
    public Result findAll(){
        List<Dept> depts = deptService.findAll();
        log.info("查询所有部门：{}", depts);
        return Result.success(depts);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        deptService.deleteById(id);
        log.info("删除部门：{}", id);
        return Result.success(id);
    }
    @Log
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        log.info("添加部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }
    @Log
    @GetMapping("/{id}")
        public Result findById(@PathVariable Integer id){
        Dept dept =  deptService.findById(id);
        log.info("查询部门：{}", dept);
        return Result.success(dept);
        }
        @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
            log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
