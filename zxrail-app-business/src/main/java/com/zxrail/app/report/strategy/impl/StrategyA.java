package com.zxrail.app.report.strategy.impl;

import com.zxrail.app.report.strategy.IStrategy;

/**
 * A的具体策略
 * @Author: Li.Wang
 * Date: 2023/12/26 10:49
 */
public class StrategyA implements IStrategy {
    @Override
    public void show() {
        System.out.println("策略A");
    }
}
