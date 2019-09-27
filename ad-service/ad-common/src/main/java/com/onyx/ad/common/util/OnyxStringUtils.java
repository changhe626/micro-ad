package com.onyx.ad.common.util;


import org.springframework.util.StringUtils;

/**
 * 字符串工具类
 */
public class OnyxStringUtils {


    /**
     * 字符串a_b_c  变成aBC
     *
     * @param source
     * @return
     */
    public static String formatString(String source) {
        if (StringUtils.isEmpty(source)) {
            return source;
        }
        int index = source.indexOf("_");
        if (index == -1) {
            return source;
        }
        String[] strings = source.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            String tmp = strings[i];
            if (!StringUtils.isEmpty(tmp)) {
                char value = tmp.charAt(0);
                if (Character.isLetter(value)) {
                    sb.append(String.valueOf(value).toUpperCase());
                    sb.append(tmp.substring(1, tmp.length()));
                }
            }
        }
        return sb.toString();
    }


}
