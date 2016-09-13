package com.algo.dict;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * IWContractSOA
 *
 * @author dk
 * @date 2016/3/25
 */
public enum OrderReportType {
    YINGSHOUYEJI(1, "二手房应收业绩", ""),
    SHISHOUKUAN(2, "二手房实收收款项", ""),
    JIANQI(3, "二手房件齐订单", ""),
    WANGQIAN(4, "二手房网签单", ""),
    GUOHU(5, "二手房过户单", ""),
    ANJIE_JIEDUAN(6, "二手房按揭单接单", ""),
    ANJIE_MIANQIANDUAN(7, "二手房按揭面签单", ""),
    ANJIE_PIDIAIDUAN(8, "二手房按揭批贷单", ""),
    JIEDAN(9, "二手房结单", ""),
    NULL(-1, "", "");
    private static final Map<Integer, OrderReportType> map = new HashMap<>(values().length);

    static {
        for(OrderReportType instance : values()) {
            map.put(instance.intValue(), instance);
        }
    }

    private int id;
    private String name;
    private String serialCode;

    private OrderReportType(int id, String name, String serialCode) {
        this.id = id;
        this.name = name;
        this.serialCode = serialCode;
    }

    public static List<OrderReportType> getAllEnum() {
        List<OrderReportType> list = new LinkedList<>();
        for(OrderReportType instance : values()) {
            if(!instance.isNull()) {
                list.add(instance);
            }
        }
        return list;
    }

    public static OrderReportType value2Obj(Integer intValue) {
        OrderReportType instance = map.get(intValue);
        return instance == null ? NULL : instance;
    }

    public int intValue() {
        return id;
    }

    public OrderReportType getNull() {
        return NULL;
    }

    public boolean isNull() {
        return NULL == this;
    }

    public String getName() {
        return name;
    }
}
