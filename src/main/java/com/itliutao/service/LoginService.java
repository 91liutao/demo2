package com.itliutao.service;

import com.itliutao.pojo.Emp;
import com.itliutao.pojo.LoginInfo;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    LoginInfo login(Emp emp);
}
