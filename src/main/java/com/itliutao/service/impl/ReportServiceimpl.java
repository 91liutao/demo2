package com.itliutao.service.impl;

import com.itliutao.mapper.EmpMapper;
import com.itliutao.mapper.StudentMapper;
import com.itliutao.pojo.JobOption;
import com.itliutao.pojo.studentCountOption;
import com.itliutao.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceimpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public JobOption getEmpJobData() {
        //调用mapper接口，获取统计数据
       List<Map<String,Object>> list = empMapper.countEmpJobDats();

       //组装数据，并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        List<Map<String, Object>> maps = empMapper.countEmpGender();
        return maps;
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String, Object>>maps = studentMapper.countStudentDegree();
        return maps;
    }

    @Override
    public studentCountOption getStudentCountData() {
    List<Map<String, Object>> list=  studentMapper.studentCountData();
    List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazzList")).toList();
    List<Object> DataList=list.stream().map(dataMap->dataMap.get("dataList")).toList();
    return new studentCountOption(clazzList,DataList);
    }
}
