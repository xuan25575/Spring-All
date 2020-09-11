package com.springboot.schedule.controller;


import com.springboot.schedule.common.GeneralResult;
import com.springboot.schedule.common.SysJobStatus;
import com.springboot.schedule.mapper.SysJobMapper;
import com.springboot.schedule.model.SysJob;
import com.springboot.schedule.task.CronTaskRegistrar;
import com.springboot.schedule.task.SchedulingRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/job")
//@Api(tags = "定时任务")
public class SysJobController {

    @Autowired
    private SysJobMapper sysJobMapper;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    //@ApiOperation(value = "新增任务",httpMethod = "POST")
    public GeneralResult add(@RequestBody SysJob sysJob){
        int i = sysJobMapper.insertSelective(sysJob);
        if (i==0)
            return GeneralResult.failure("新增失败");
        else {
            if (sysJob.getJobStatus().equals(SysJobStatus.NORMAL.ordinal())) {
                SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
                cronTaskRegistrar.addCronTask(task, sysJob.getCronExpression());
            }
        }
        return GeneralResult.success();
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST )
    //@ApiOperation(value = "编辑任务",httpMethod = "POST")
    public GeneralResult update(@RequestBody  SysJob sysJob){
        SysJob existedSysJob = sysJobMapper.selectByPrimaryKey(sysJob.getJobId());
        int i = sysJobMapper.updateByPrimaryKeySelective(sysJob);
        if (i==0)
            return GeneralResult.failure("编辑失败");
        else {

            if (existedSysJob.getJobStatus().equals(SysJobStatus.NORMAL.ordinal())) {
                SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(),
                        existedSysJob.getMethodName(), existedSysJob.getMethodParams());
                cronTaskRegistrar.removeCronTask(task);
            }

            if (sysJob.getJobStatus().equals(SysJobStatus.NORMAL.ordinal())) {
                SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
                cronTaskRegistrar.addCronTask(task, sysJob.getCronExpression());
            }
        }
        return GeneralResult.success();
    }


    @RequestMapping("/delete")
   // @ApiOperation(value = "删除任务",httpMethod = "GET")
    public GeneralResult delete(Integer id){
        SysJob existedSysJob = sysJobMapper.selectByPrimaryKey(id);

        int i = sysJobMapper.deleteByPrimaryKey(id);
        if (i==0)
            return GeneralResult.failure("删除失败");
        else {
            if (existedSysJob.getJobStatus().equals(SysJobStatus.NORMAL.ordinal())) {
                SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
                cronTaskRegistrar.removeCronTask(task);
            }
        }
        return GeneralResult.success();
    }


    @RequestMapping("/switchStatus")
    //@ApiOperation(value = "切换状态",httpMethod = "POST")
    public GeneralResult switchStatus(@RequestBody SysJob existedSysJob){
        if (existedSysJob.getJobStatus().equals(SysJobStatus.NORMAL.ordinal())) {
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            cronTaskRegistrar.addCronTask(task, existedSysJob.getCronExpression());
        } else {
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            cronTaskRegistrar.removeCronTask(task);
        }
        return GeneralResult.success();
    }

    @RequestMapping("/get")
   // @ApiOperation(value = "切换状态",httpMethod = "GET")
    public GeneralResult get(){
        List<SysJob> sysJobs = sysJobMapper.selectByExample(null);
        return new GeneralResult("200",sysJobs);
    }

}
