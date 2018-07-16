package com.example.service.common;

public enum GoodsType {

    NORMAL_GOODS(1,"普通商品"),
    FESTIVAL_GOODS(2,"节日商品"),
    PROXY_GOODS(3,"代购商品");

    private int value;
    private String description;

    GoodsType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }
}
