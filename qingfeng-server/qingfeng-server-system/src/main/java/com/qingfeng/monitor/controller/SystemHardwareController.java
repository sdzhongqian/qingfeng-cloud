package com.qingfeng.monitor.controller;

import com.qingfeng.base.controller.BaseController;
import com.qingfeng.entity.MyResponse;
import com.qingfeng.monitor.server.SystemHardwareServer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName SystemHardwareController
 * @author qingfeng
 * @version 1.0.0
 * @Description 监控服务
 * @createTime 2021/4/3 0003 21:00
 */
@Slf4j
@Validated
@RestController
@RequestMapping("monitorServer")
public class SystemHardwareController extends BaseController {

    /**
     * @title systemHardware
     * @description systemHardware
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:00
     */
    @GetMapping("/systemHardware")
    public MyResponse systemHardware() throws Exception {
        SystemHardwareServer server = new SystemHardwareServer();
        server.copyTo();
        return new MyResponse().data(server);
    }


}
