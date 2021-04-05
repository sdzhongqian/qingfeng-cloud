package com.qingfeng.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.jdbcjobstore.JobStoreTX;
import org.quartz.simpl.SimpleThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @ProjectName SchedulerConfig
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 20:29
 */
@Configuration
@RequiredArgsConstructor
public class SchedulerConfig {

	@Autowired
	private SpringJobFactory springJobFactory;

    private final DynamicRoutingDataSource dynamicRoutingDataSource;

    @Bean(name="SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // 设置数据源
        DataSource job = dynamicRoutingDataSource.getDataSource("base");
        System.out.println("----------------------");
        System.out.println(job);
        factory.setDataSource(job);
        Properties prop = new Properties();
        // 任务调度实例名称，集群时多个实例名称保持一致
        prop.put(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, "QingfengCloudScheduler");
        // 任务调度实例ID，指定为AUTO时，将自动生成ID
        prop.put(StdSchedulerFactory.PROP_SCHED_INSTANCE_ID, StdSchedulerFactory.AUTO_GENERATE_INSTANCE_ID);
        // quartz提供的简单线程池，适用于绝大部分场景
        prop.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, SimpleThreadPool.class);
        // 并发执行任务的线程数，取决于服务器系统资源
        prop.put("org.quartz.threadPool.threadCount", "20");
        // 可以是Thread.MIN_PRIORITY（1）和Thread.MAX_PRIORITY（10）之间的任何int值 。
        // 默认值为Thread.NORM_PRIORITY（5）
        prop.put("org.quartz.threadPool.threadPriority", "5");
        // 指定任务存储策略，这里使用关系型数据库
        prop.put(StdSchedulerFactory.PROP_JOB_STORE_CLASS, JobStoreTX.class);
        // 是否开启集群
        prop.put("org.quartz.jobStore.isClustered", "true");
        // 集群中任务调度实例失效的检查时间间隔，单位为毫秒
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        // 数据表前缀
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        factory.setQuartzProperties(prop);
        factory.setSchedulerName("Qingfeng_Cloud_Scheduler");
        // 延时启动
        factory.setStartupDelay(1);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        // 启动时更新己存在的 Job
        factory.setOverwriteExistingJobs(true);
        // 设置自动启动，默认为 true
        factory.setAutoStartup(true);
        factory.setJobFactory(springJobFactory);
        return factory;
    }

//    @Bean(name="SchedulerFactory")
//    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setAutoStartup(true);
//        factory.setStartupDelay(5);//延时5秒启动
//        factory.setQuartzProperties(quartzProperties());
//        factory.setJobFactory(springJobFactory);
//        return factory;
//    }


    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }


    /*
     * quartz初始化监听器
     */
    @Bean
    public QuartzInitializerListener executorListener() {
       return new QuartzInitializerListener();
    }

    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Primary
    @Bean(name="Scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

}