package com.example.service.application.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.service.application.ISystemApplicationService;
import com.example.service.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class SystemApplicationServiceImpl implements ISystemApplicationService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public JSONObject login(String userName, String password) throws InvalidOperationException {
        String sql = "SELECT * FROM YUNYI_ServiceOfficeAccount WHERE LoginName = ? AND Password = ?";
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql,userName,password);
        if(CollectionUtils.isEmpty(result)){
            throw new InvalidOperationException("用户名或密码错误");
        }
        JSONObject admin = new JSONObject();
        admin.put("userName",result.get(0).get("LoginName"));
        admin.put("serviceId",result.get(0).get("ServiceId"));
        return admin;
    }
}
