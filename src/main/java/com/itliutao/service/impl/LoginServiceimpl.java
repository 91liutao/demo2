package com.itliutao.service.impl;

import com.itliutao.mapper.LoginMapper;
import com.itliutao.pojo.Emp;
import com.itliutao.pojo.LoginInfo;
import com.itliutao.service.LoginService;
import com.itliutao.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceimpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginInfo login(Emp emp) {
        Emp e = loginMapper.login(emp);
        if (e!=null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateToken(claims);
            return new LoginInfo(e.getId(),e.getUsername(),e.getPassword(),jwt);
        }
        return null;
    }
}
