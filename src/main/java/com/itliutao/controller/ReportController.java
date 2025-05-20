package com.itliutao.controller;

import com.itliutao.pojo.JobOption;
import com.itliutao.pojo.Result;
import com.itliutao.pojo.studentCountOption;
import com.itliutao.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/empJobData")
    public Result empJobData(){
        log.info("查询员工职位数据");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }
    @GetMapping("/empGenderData")
    public Result empGenderData(){
        log.info("查询员工性别数据");
        List<Map<String,Object>>genderList =reportService.getEmpGenderData();
        return Result.success(genderList);
    }
    @GetMapping("/studentDegreeData")
    public Result studentDegreeData(){
        log.info("查询学生学历数据");
        List<Map<String,Object>>degreeList =reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }
    @GetMapping("/studentCountData")
    public Result studentCountData(){
        log.info("查询学生学历数据");
        studentCountOption studebntCountOption =reportService.getStudentCountData();
        return Result.success(studebntCountOption);
    }
}
