package com.onyx.ad.sponsor.constant;

/**
 * @author zk
 * @Description:
 * @date 2019-09-24 15:11
 */
public enum CreativeMetrialEnum {

    JPG(1, "jpg"),
    BMP(2, "bmp"),
    MP4(3, "mp4"),
    AVI(4, "avi"),
    TXT(5, "txt");

    private int type;
    private String desc;

    CreativeMetrialEnum(int type, String desc) {
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
