package com.qingfeng.monitor.entity;

import com.qingfeng.utils.CalculationUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName Cpu
 * @author qingfeng
 * @version 1.0.0
 * @Description Cpu
 * @createTime 2021/4/3 0003 21:00
 */
@Data
public class Cpu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 核心数
     */
    private int cpuNum;

    /**
     * CPU总的使用率
     */
    private double total;

    /**
     * CPU系统使用率
     */
    private double sys;

    /**
     * CPU用户使用率
     */
    private double used;

    /**
     * CPU当前等待率
     */
    private double wait;

    /**
     * CPU当前空闲率
     */
    private double free;


    public double getTotal() {
        return CalculationUtil.round(CalculationUtil.multiply(total+"", "100"), 2);
    }

    public double getSys() {
        return CalculationUtil.round(CalculationUtil.multiply((sys/total)+"", "100"), 2);
    }

    public double getUsed() {
        return CalculationUtil.round(CalculationUtil.multiply((used / total)+"", "100"), 2);
    }

    public double getWait() {
        return CalculationUtil.round(CalculationUtil.multiply((wait / total)+"", "100"), 2);
    }

    public double getFree() {
        return CalculationUtil.round(CalculationUtil.multiply((free / total)+"", "100"), 2);
    }
}