package com.qingfeng.job;

import org.quartz.*;
import java.io.Serializable;


/**
 * @author qingfeng
 * @Title: MessageJob
 * @ProjectName property
 * @Description: TODO
 * @date 2019-10-3113:47
 */
@DisallowConcurrentExecution
public class MessageJob implements Job,Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        JobDetail jobDetail = arg0.getJobDetail();
        System.out.println("=================开始执行===================");
        System.out.println(jobDetail.getDescription());
        System.out.println(jobDetail.getKey().getName());
        System.out.println(jobDetail.getKey().getGroup());
        System.out.println("====================消息====================");
    }

}
