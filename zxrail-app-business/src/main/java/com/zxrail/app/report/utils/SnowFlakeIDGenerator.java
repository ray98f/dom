package com.zxrail.app.report.utils;

import cn.hutool.core.util.IdUtil;

public class SnowFlakeIDGenerator {
    public static long _nextId(){
        return IdUtil.getSnowflakeNextId();
    }

}
