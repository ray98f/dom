package com.wzmtr.dom.enums;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author lize
 * @Date 2023/6/9
 */
public enum PlanStatusEnum {
    //
    draft("draft", "草稿"),
    approved("approved", "已批准"),
    published("published", "已发布"),
    changed("changed", "已变更"),
    canceled("canceled", "已取消"),
    started("started", "已请点"),
    finished("finished", "已完成"),
    expired("expired", "过期作废"),
    approval("approval", "审批中");
    private String value;
    private String label;

    PlanStatusEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static PlanStatusEnum find(String flowId) {
        for (PlanStatusEnum bpmnFlowEnum : values()) {
            if (bpmnFlowEnum.value.equals(flowId)) {
                return bpmnFlowEnum;
            }
        }
        return null;
    }

    public String value() {
        return this.value;
    }

    public String label() {
        return this.label;
    }

    public static String getLabelByValue(String value) {
        for (PlanStatusEnum orgTypeEnum : PlanStatusEnum.values()) {
            if (orgTypeEnum.value.equals(value)) {
                return orgTypeEnum.label;
            }
        }
        return null; // 如果未找到匹配的 label，则返回 null 或者抛出异常
    }

    public static String getValueByLabel(String label) {
        for (PlanStatusEnum orgTypeEnum : PlanStatusEnum.values()) {
            if (orgTypeEnum.label.equals(label)) {
                return orgTypeEnum.value;
            }
        }
        return null; // 如果未找到匹配的 label，则返回 null 或者抛出异常
    }

    public static List<HashMap<String, String>> printAllValuesAndLabels() {
        List<HashMap<String, String>> list = new ArrayList<>();

        for (PlanStatusEnum orgTypeEnum : PlanStatusEnum.values()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", orgTypeEnum.value);
            map.put("name", orgTypeEnum.label);
            list.add(map);

        }
        return list;
    }

}

