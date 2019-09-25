package com.onyx.ad.search.mysql.constant;

public enum OpType {

    ADD(1, "增加"),
    UPDATE(2, "更新"),
    DELETE(3, "删除"),
    OTHER(4, "其他");

    private int code;
    private String name;

    OpType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
