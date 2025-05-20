package com.itliutao.controller;

import com.itliutao.pojo.Result;
import com.itliutao.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    //    本地磁盘存储方案
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws Exception {
//        log.info("参数: name:{},age:{},file:{}",name,age,file);
//        //获取原始文件名
//        String originalFilename = file.getOriginalFilename();
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFileName= UUID.randomUUID().toString()+extension;
//        //保存文件
//        file.transferTo(new File("D:/download/ideafile/"+newFileName));
//        return Result.success();
//
//
//    }
//
//}
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws Exception {
        log.info("参数: name:{},age:{},file:{}", name, age, file);
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);
    }
}
