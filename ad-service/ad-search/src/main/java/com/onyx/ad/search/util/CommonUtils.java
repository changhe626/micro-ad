package com.onyx.ad.search.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;



@Slf4j
public class CommonUtils {

    /**
     * 存在再放到map中去,节省了创建集合的过程
     *
     Map<String, List<String>> map = new HashMap<>();
     List<String> list;

     // 一般这样写
     list = map.get("list-1");
     if (list == null) {
     list = new LinkedList<>();
     map.put("list-1", list);
     }
     list.add("one");

     // 使用 computeIfAbsent 可以这样写
     list = map.computeIfAbsent("list-1", k -> new ArrayList<>());
     list.add("one");

     * @param key
     * @param map
     * @param factory
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> V getorCreate(K key, Map<K, V> map,
                                       Supplier<V> factory) {
        return map.computeIfAbsent(key, k -> factory.get());
    }

    public static String stringConcat(String... args) {

        StringBuilder result = new StringBuilder();
        for (String arg : args) {
            result.append(arg);
            result.append("-");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }


    public static Date parseStringDate(String dateString) {

        try {

            DateFormat dateFormat = new SimpleDateFormat(
                    "EEE MMM dd HH:mm:ss zzz yyyy",
                    Locale.US
            );
            return DateUtils.addHours(
                    dateFormat.parse(dateString),
                    -8
            );

        } catch (ParseException ex) {
            log.error("parseStringDate error: {}", dateString);
            return null;
        }
    }
}
