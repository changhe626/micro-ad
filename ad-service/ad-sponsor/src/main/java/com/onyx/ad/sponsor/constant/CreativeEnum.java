package com.onyx.ad.sponsor.constant;

/**
 * @author zk
 * @Description: 类型
 * @date 2019-09-24 15:09
 */
public enum CreativeEnum {

    IMAGE(1,"图像"),
    VIDEO(2, "视频"),
    TEXT(3, "文本");

    private int type;
    private String desc;

    CreativeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
