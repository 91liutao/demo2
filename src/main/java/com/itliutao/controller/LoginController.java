package com.itliutao.controller;

import com.itliutao.pojo.Emp;
import com.itliutao.pojo.LoginInfo;
import com.itliutao.pojo.Result;
import com.itliutao.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping
    public Result login(@RequestBody Emp emp) {
       log.info("登录信息："+emp);
        LoginInfo login = loginService.login(emp);
        if (login!=null){
            return Result.success(login);
        }else
            return Result.error("登录失败");
    }
}
