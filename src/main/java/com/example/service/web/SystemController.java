package com.example.service.web;

import com.example.service.application.ISystemApplicationService;
import com.example.service.common.RestResponse;
import com.example.service.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys")
public class SystemController {

    private final ISystemApplicationService systemApplicationService;

    @Autowired
    public SystemController(ISystemApplicationService systemApplicationService) {
        this.systemApplicationService = systemApplicationService;
    }

    /**
     * 管理员登陆
     * @param userName 用户名
     * @param password 密码
     * @throws InvalidOperationException 用户名或密码错误
     */
    @PostMapping("/login")
    public RestResponse login(@RequestParam("userName")String userName,
                              @RequestParam("password")String password) throws InvalidOperationException {
        return RestResponse.buildResponse(systemApplicationService.login(userName,password));
    }
}
