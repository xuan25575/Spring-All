/**************************************************************************
 * Copyright (c) 2006-2025 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 *
 * 项目名称：世晨2.0
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。
 ***************************************************************************/
package com.springboot.config;

import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @description:    数据源配置类
 * @author:         <a href="mailto:zhangbao@zjport.gov.cn">zhangbao</a>
 * @version:         $Id: MybatisConfig.java  2020/6/16 17:04  zhangbao $
 * @since:           1.0
 */
@Configuration
public class MybatisConfig  {


    /**
     *
     * 乐观锁增加版本 配合@version注解使用.
     * @return OptimisticLockerInnerInterceptor
     */
    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }




}
