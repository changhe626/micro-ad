package com.onyx.ad.sponsor.constant;

/**
 * @author zk
 * @Description:
 * @date 2019-09-24 14:50
 */
public enum CommonStatus {
    VALID(1,"有效状态"),
    INVALID(2,"无效状态"),
    ;


    private int code;
    private String message;


    CommonStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
