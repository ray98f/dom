package com.wzmtr.dom.enums;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author lize
 * @Date 2023/6/9
 */
public enum DispatchOrderStatusEnum {
    //
    DRAFT("draft", "草稿"),
    UNAPPROVED("unapproved", "审批不通过"),
    RELIEVED("relieved", "已销令"),
    APPROVING("approving", "审批中"),
    RELEASED("released", "已发令"),
    ACCEPTED("accepted", "已受令");
    private String value;
    private String label;

    DispatchOrderStatusEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static DispatchOrderStatusEnum find(String flowId) {
        for (DispatchOrderStatusEnum bpmnFlowEnum : values()) {
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
        for (DispatchOrderStatusEnum orgTypeEnum : DispatchOrderStatusEnum.values()) {
            if (orgTypeEnum.value.equals(value)) {
                return orgTypeEnum.label;
            }
        }
        return null; // 如果未找到匹配的 label，则返回 null 或者抛出异常
    }

    public static String getValueByLabel(String label) {
        for (DispatchOrderStatusEnum orgTypeEnum : DispatchOrderStatusEnum.values()) {
            if (orgTypeEnum.label.equals(label)) {
                return orgTypeEnum.value;
            }
        }
        return null; // 如果未找到匹配的 label，则返回 null 或者抛出异常
    }

    public static List<HashMap<String, String>> printAllValuesAndLabels() {
        List<HashMap<String, String>> list = new ArrayList<>();

        for (DispatchOrderStatusEnum orgTypeEnum : DispatchOrderStatusEnum.values()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", orgTypeEnum.value);
            map.put("name", orgTypeEnum.label);
            list.add(map);

        }
        return list;
    }

}

