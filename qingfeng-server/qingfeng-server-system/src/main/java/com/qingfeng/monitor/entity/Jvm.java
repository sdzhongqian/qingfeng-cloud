package com.qingfeng.monitor.entity;

import com.qingfeng.utils.CalculationUtil;
import com.qingfeng.utils.DateTimeUtil;
import lombok.Data;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.Date;

/**
 * @title Jvm
 * @description Jvm
 * @author qingfeng
 * @updateTime 2021/4/3 0003 21:00
 */
@Data
public class Jvm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前JVM占用的内存总数(M)
     */
    private double total;

    /**
     * JVM最大可用内存总数(M)
     */
    private double max;

    /**
     * JVM空闲内存(M)
     */
    private double free;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    public double getTotal() {
        String value = "";
        try {
            value = CalculationUtil.divide(total+"", (1024 * 1024)+"", 2);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Double.parseDouble(value);
    }

    public double getMax() {
        String value = "";
        try {
            value = CalculationUtil.divide(max+"", (1024 * 1024)+"", 2);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Double.parseDouble(value);
    }

    public double getFree() {
        String value = "";
        try {
            value = CalculationUtil.divide(free+"", (1024 * 1024)+"", 2);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Double.parseDouble(value);
    }

    public double getUsed() {
        String value = "";
        try {
            value = CalculationUtil.divide((total - free)+"", (1024 * 1024)+"", 2);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Double.parseDouble(value);
    }

    public String getVersion() {
        return version;
    }

    public String getHome() {
        return home;
    }

    public double getUsage() {
        String value = "";
        try {
            value = CalculationUtil.multiply(CalculationUtil.divide((total - free)+"", total+"", 4), "100",2);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Double.parseDouble(value);
    }
    /**
     * 获取JDK名称
     */
    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    /**
     * JDK启动时间
     */
    public String getStartTime() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        Date date = new Date(time);
        String value = "";
        try {
            value = DateTimeUtil.getDateTimeStr(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

    /**
     * JDK运行时间
     */
    public String getRunTime() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        String value = "";
        try {
            value = DateTimeUtil.getDatePoor(new Date(time), DateTimeUtil.getDateTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
}
