package com.qingfeng.job;

import org.quartz.*;

import java.io.Serializable;

/**
 * Job 的实例要到该执行它们的时候才会实例化出来。每次 Job 被执行，一个新的 Job 实例会被创建。
 * 其中暗含的意思就是你的 Job 不必担心线程安全性，因为同一时刻仅有一个线程去执行给定 Job 类的实例，甚至是并发执行同一 Job 也是如此。
 * @DisallowConcurrentExecution 保证上一个任务执行完后，再去执行下一个任务，这里的任务是同一个任务
 */
@DisallowConcurrentExecution
public class ChickenJob implements  Job,Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		JobDetail jobDetail = arg0.getJobDetail();
		System.out.println(jobDetail.getDescription());
		System.out.println(jobDetail.getKey().getName());
		System.out.println(jobDetail.getKey().getGroup());
		System.out.println("大吉大利、今晚吃鸡");
	}
}
