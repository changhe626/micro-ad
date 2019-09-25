package com.onyx.ad.search.index;

/**
 * 索引的接口,
 * 没有使用elasticsearch.自己再内存中进行查询缓存
 * @param <K>
 * @param <V>
 */
public interface IndexAware<K,V> {

    /**
     * 获取
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 添加
     * @param key
     * @param value
     */
    void add(K key,V value);

    /**
     * 更新
     * @param key
     * @param value
     */
    void update(K key,V value);

    /**
     * 删除
     * @param key
     * @param value
     */
    void delete(K key,V value);

    /**
     * 删除
     * @param key
     */
    void delete(K key);

}
