package com.yzchn.platform.common.utils.copy;

/**
 * @author 洛无异
 * @description //TODO
 * @date 2021/4/19 11:19
 */
public interface ModelUtilsFilter<K, V> {
    default void process(K k, V v) throws Exception {
    }

    boolean filter(K k) throws Exception;
}
