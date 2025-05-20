package com.itliutao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itliutao.mapper.EmpExprMapper;
import com.itliutao.mapper.EmpMapper;
import com.itliutao.pojo.Emp;
import com.itliutao.pojo.EmpExpr;
import com.itliutao.pojo.EmpQueryParam;
import com.itliutao.pojo.PageResult;
import com.itliutao.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@Service
@Transactional
public class EmpServiceimpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
//原始方法
//    @Override
//    public PageResult<Emp> Page(Integer page, Integer pageSize) {
//        //调用mapper接口查询总记录数
//        Long total = empMapper.count();
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//        return new PageResult<Emp>(total,rows);
//    }
//    @Override
//    public PageResult<Emp> Page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //调用mapper接口查询总记录数
//        PageHelper.startPage(page, pageSize);
//        List<Emp> list = empMapper.list(name,gender,begin,end);
//        Page<Emp> empPage = (Page<Emp>) list;
//        return new PageResult<Emp>(empPage.getTotal(),empPage.getResult());
//    }
    @Override
    public PageResult<Emp> Page(EmpQueryParam queryParam) {
        //调用mapper接口查询总记录数
        PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
        List<Emp> list = empMapper.list(queryParam);
        Page<Emp> empPage = (Page<Emp>) list;
        return new PageResult<Emp>(empPage.getTotal(),empPage.getResult());
    }

    @Override
    public void save(Emp emp) {

        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(expr -> {emp.getId();});
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteByIds(List<Integer> ids) {
        //删除员工的基本信息
        empMapper.deleteByIds(ids);

        //批量删除员工的工作经历里信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getById(Integer id) {
       return  empMapper.getById(id);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(expr -> {expr.setEmpId(emp.getId());});
            empExprMapper.insertBatch(exprList);
        }
    }

}
