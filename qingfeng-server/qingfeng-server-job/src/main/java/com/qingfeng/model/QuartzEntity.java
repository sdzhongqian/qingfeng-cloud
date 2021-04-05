package com.qingfeng.model;

import lombok.Data;

/**
 * @ProjectName QuartzEntity
 * @author qingfeng
 * @version 1.0.0
 * @Description 任务类
 * @createTime 2021/4/3 0003 20:24
 */
@Data
public class QuartzEntity {

	private String jobName;//任务名称
	private String jobGroup;//任务分组
	private String description;//任务描述
	private String jobClassName;//执行类
	private String cronExpression;//执行时间
	private String triggerTime;//执行时间
	private String triggerState;//任务状态

	private String oldJobName;//任务名称 用于修改
	private String oldJobGroup;//任务分组 用于修改

	public QuartzEntity() {
		super();
	}
	public QuartzEntity(String jobName, String jobGroup, String description, String jobClassName, String cronExpression, String triggerTime) {
		super();
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.description = description;
		this.jobClassName = jobClassName;
		this.cronExpression = cronExpression;
		this.triggerTime = triggerTime;
	}
}
