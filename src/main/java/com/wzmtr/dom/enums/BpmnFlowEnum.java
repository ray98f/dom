package com.wzmtr.dom.enums;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author lize
 * @Date 2023/6/9
 */
public enum BpmnFlowEnum {
    //
    traffic_daily("traffic_daily", "客运部日报审批流程"),
    traffic_weekly("traffic_weekly", "客运部周报审批流程"),
    traffic_monthly("traffic_monthly", "客运部月报审批流程");
    private String value;
    private String label;

    BpmnFlowEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static BpmnFlowEnum find(String flowId) {
        for (BpmnFlowEnum bpmnFlowEnum : values()) {
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
        for (BpmnFlowEnum orgTypeEnum : BpmnFlowEnum.values()) {
            if (orgTypeEnum.value.equals(value)) {
                return orgTypeEnum.label;
            }
        }
        return null; // 如果未找到匹配的 label，则返回 null 或者抛出异常
    }

    public static String getValueByLabel(String label) {
        for (BpmnFlowEnum orgTypeEnum : BpmnFlowEnum.values()) {
            if (orgTypeEnum.label.equals(label)) {
                return orgTypeEnum.value;
            }
        }
        return null; // 如果未找到匹配的 label，则返回 null 或者抛出异常
    }

    public static List<HashMap<String, String>> printAllValuesAndLabels() {
        List<HashMap<String, String>> list = new ArrayList<>();

        for (BpmnFlowEnum orgTypeEnum : BpmnFlowEnum.values()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", orgTypeEnum.value);
            map.put("name", orgTypeEnum.label);
            list.add(map);

        }
        return list;
    }

}

