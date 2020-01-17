package com.monitor.xo.vo;

import java.util.List;
import java.util.Map;

public class CpuDataVO {

    //cpu标识
    private String  id;

    //数据集合
    private List<Map<String,Object>> valueList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Map<String, Object>> getValueList() {
        return valueList;
    }

    public void setValueList(List<Map<String, Object>> valueList) {
        this.valueList = valueList;
    }

    @Override
    public String toString() {
        return "CpuData{" +
                "id=" + id +
                ", valueList=" + valueList +
                '}';
    }
}
