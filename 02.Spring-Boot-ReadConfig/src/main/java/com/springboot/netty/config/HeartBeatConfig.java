package com.springboot.netty.config;

import com.springboot.netty.protocol.CustomProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 心跳机制bean 配置
 * 由于整合了 SpringBoot ，所以发送的心跳信息是一个单例的 Bean。
 */
@Configuration
public class HeartBeatConfig {


    @Value("${channel.id}")
    private long id;


    @Bean(value = "heartBeat")
    public CustomProtocol customProtocol(){
        return new CustomProtocol(id,"ping");
    }
}

