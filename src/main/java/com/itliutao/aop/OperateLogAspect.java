package com.itliutao.aop;

import com.itliutao.mapper.OperateLogMapper;
import com.itliutao.pojo.OperateLog;
import com.itliutao.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class OperateLogAspect {
    @Autowired
    private  OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itliutao.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();


        Object result = joinPoint.proceed();


        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
//构建日志实体
        OperateLog olog = new OperateLog();
        olog.setOperateEmpId(getCurrentUserId());
        olog.setOperateTime(LocalDateTime.now());
        olog.setClassName(joinPoint.getTarget().getClass().getName());
        olog.setMethodName(joinPoint.getSignature().getName());
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        olog.setReturnValue(result==null?result.toString() :"void");
        olog.setCostTime(costTime);

        operateLogMapper.insert(olog);
        return result;
    }

    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}
