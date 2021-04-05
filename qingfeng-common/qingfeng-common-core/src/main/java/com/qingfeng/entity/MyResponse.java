package com.qingfeng.entity;

import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName MyResponse
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:27
 */
public class MyResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public MyResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public MyResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public MyResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
