package com.springboot.schedule.task;


import com.springboot.schedule.common.SysJobStatus;
import com.springboot.schedule.mapper.SysJobMapper;
import com.springboot.schedule.model.SysJob;
import com.springboot.schedule.model.SysJobExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * CommandLineRunner 这个类
 * 1.平常开发中有可能需要实现在项目启动后执行的功能，这个类的执行时机
 * 2.如果有多个CommandLineRunner的话，使用@Order注解（或者实现Order接口）来表明顺序
 *  - @Order 注解的执行优先级是按value值从小到大顺序。
 *
 */
@Service
public class SysJobRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SysJobRunner.class);

    @Autowired
    private SysJobMapper sysJobMapper;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    /**
     *
     * 逻辑，当程序启动的时候，将数据库中存在的定时任务加入cronTaskRegistrar
     * @param args args
     */
    @Override
    public void run(String... args) {

        SysJobExample example = new SysJobExample();
        example.createCriteria().andJobStatusEqualTo(SysJobStatus.NORMAL.ordinal());

        List<SysJob> jobList = sysJobMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(jobList)) {
            for (SysJob job : jobList) {
                SchedulingRunnable task = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getMethodParams());
                cronTaskRegistrar.addCronTask(task, job.getCronExpression());
            }

            logger.info("定时任务已加载完毕...");
        }
    }
}
