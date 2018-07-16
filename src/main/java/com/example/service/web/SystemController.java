package com.example.service.web;

import com.alibaba.fastjson.JSONObject;
import com.example.service.application.ISystemApplicationService;
import com.example.service.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys")
public class SystemController {

    @Autowired
    private ISystemApplicationService systemApplicationService;

    @PostMapping("/login")
    public JSONObject login(@RequestParam("userName")String userName,
                            @RequestParam("password")String password) throws InvalidOperationException {
        return systemApplicationService.login(userName,password);
    }
}
