package com.example.service.common;

public enum ExtremeType {

    BEST(1,"最佳"),
    WORST(2,"最差");

    private int value;
    private String description;

    ExtremeType(int value, String description) {
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
