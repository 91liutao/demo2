package com.itliutao.controller;

import com.itliutao.pojo.Clazz;
import com.itliutao.pojo.PageResult;
import com.itliutao.pojo.Result;
import com.itliutao.service.ClazzService;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    @GetMapping

    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize){
        log.info("分页查询，参数：page={},pageSize={}",page,pageSize);
        PageResult<Clazz> pageResult = clazzService.Page(page,pageSize);
        return Result.success(pageResult);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        clazzService.deleteById(id);
        return Result.success();
    }
    @PostMapping
    public Result insert(@RequestBody Clazz clazz){
        clazzService.insert(clazz);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        clazzService.update(clazz);
        return Result.success();
    }
}
