package com.qingfeng.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;

/**
 * @Title: TestJob
 * @ProjectName wdata
 * @Description: 实现序列化接口、防止重启应用出现quartz Couldn't retrieve job because a required class was not found 的问题
 * @author qingfeng
 * @date 2019-6-5 14:44
 */
public class TestJob implements  Job,Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//可注入Service 执行相关业务操作
		System.out.println("任务执行成功");
	}
}
