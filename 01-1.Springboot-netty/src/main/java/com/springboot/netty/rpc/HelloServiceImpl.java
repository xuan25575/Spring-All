package com.springboot.netty.rpc;

public class HelloServiceImpl implements HelloService {

    private int count;

    @Override
    public String hello(String mes) {
        System.out.println("收到客户端消息=" + mes); //根据 mes 返回不同的结果
        if(mes != null) {
            return "你好客户端, 我已经收到你的消息 [" + mes + "] 第" + (++count) + " 次";
        } else {
            return "你好客户端, 我已经收到你的消息 ";
        }
    }
}
