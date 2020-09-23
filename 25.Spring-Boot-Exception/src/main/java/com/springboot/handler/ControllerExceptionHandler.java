package com.springboot.handler;

import com.springboot.exception.UserExitException;
import com.springboot.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 这是全局异常的处理
 * 1.通过拦截指定的异常，让所有抛出了指定的异常的方法，会被统一拦截
 * @ControllerAdvice 和@ExceptionHandler 配合使用。
 *
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(UserNotExistException.class)
    // 不使用这个注解的话，返回自定义的错误页面  resources/resources/error/500.html
    //@ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistsException(UserNotExistException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("message", e.getMessage());
        return map;
    }


    /**
     * spring.resources.static-locations =
     * classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
     * @param e
     * @return
     */
}
