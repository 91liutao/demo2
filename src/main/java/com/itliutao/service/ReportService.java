package com.itliutao.service;

import com.itliutao.pojo.JobOption;
import com.itliutao.pojo.studentCountOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReportService {
    JobOption getEmpJobData();
    List<Map<String,Object>> getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    studentCountOption getStudentCountData();
}
