package com.yzchn.platform.common.utils.copy;

public interface ModelUtilsProcessor<K, V> {
    void process(K k, V v) throws Exception;

    default boolean filter(K k) {
        return true;
    }
}
