package com.itliutao.controller;

import com.itliutao.pojo.Emp;
import com.itliutao.pojo.EmpQueryParam;
import com.itliutao.pojo.PageResult;
import com.itliutao.pojo.Result;
import com.itliutao.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
   // 分页查询
    @GetMapping
    public Result Page(EmpQueryParam param){
        log.info("分页查询{},{},{},{},{},{}",param);
        PageResult<Emp> pageResult = empService.Page(param);
        return Result.success(pageResult);
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增或修改");
        empService.save(emp);
        return Result.success();
    }
    @DeleteMapping
    public Result delete(@RequestParam("id") List<Integer> ids){
        empService.deleteByIds(ids);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        empService.deleteByIds(List.of(id));
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息："+emp);
        empService.update(emp);
        return Result.success();
    }

}
