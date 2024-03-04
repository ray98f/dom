package com.zxrail.app.report.strategy.impl;

import com.zxrail.app.report.strategy.IStrategy;

import java.util.Map;
import java.util.function.Function;

/**
 * @Author: Li.Wang
 * Date: 2023/12/26 10:56
 */
public class StrategyContext {
    private final IStrategy strategy;
    private Map<String, Function<Integer,String>> functionList;
    public StrategyContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 根据消息场景选择对应的策略
     *
     * @param scene
     */
    public void SceneMessageSend(String scene) {
        System.out.println(scene + "选择的场景为：");
        strategy.show();
    }
}
