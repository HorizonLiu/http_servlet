package com.practice.http_servlet.controller;

import com.practice.http_servlet.bean.CommonResponseBody;
import com.practice.http_servlet.bean.ResponseBody;
import com.practice.http_servlet.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author horizonliu
 * @date 2021/5/19 10:29 上午
 */
@RestController
@Slf4j
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/insert")
    public ResponseBody insert() {
        return userService.insert();
    }

    @PostMapping(value = "/page")
    public CommonResponseBody page(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
        return new CommonResponseBody(userService.list(pageIndex, pageSize));
    }


}
