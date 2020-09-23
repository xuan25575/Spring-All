package com.springboot.controller;

import com.springboot.exception.UserExitException;
import com.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    /**
     *  @GetMapping 支持正则表达式。
     *
     *
     * @param id
     */
    @GetMapping("/{id:\\d+}")
    public void get(@PathVariable String id) {
        throw new UserNotExistException(id);
    }



    @GetMapping("/exit")
    public void exit() {
        throw new UserExitException("用户退出异常！");
    }
}

