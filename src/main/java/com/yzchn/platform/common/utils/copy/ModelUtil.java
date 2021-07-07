package com.yzchn.platform.common.utils.copy;

import com.yzchn.platform.common.exception.BusinessEnum;
import com.yzchn.platform.common.exception.BusinessException;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 洛无异
 * @description //TODO model copy 工具类
 * @date 2021/4/19 11:13
 */
public class ModelUtil {
    public static <K, V> V copyModel(K k, Class<V> vClass) {
        try {
            return copyModel(k, vClass.newInstance());
        } catch (Exception e) {
            throw new BusinessException(BusinessEnum.SYSTEM_HANDLE_ERROR);
        }
    }

    public static <K, V> V copyModel(K k, Class<V> vClass, ModelUtilsProcessor<K, V> processor) {
        try {
            return copyModel(k, vClass.newInstance(), processor);
        } catch (Exception e) {
            throw new BusinessException(BusinessEnum.SYSTEM_HANDLE_ERROR);
        }
    }

    public static <K, V> V copyModel(K k, Class<V> vClass, ModelUtilsFilter<K, V> filter) {
        try {
            return copyModel(k, vClass.newInstance(), filter);
        } catch (Exception e) {
            throw new BusinessException(BusinessEnum.SYSTEM_HANDLE_ERROR);
        }
    }

    /**
     * 复制kList的每一项到list中
     *
     * @param kList  source
     * @param classV 生成的list中每一项的类型
     * @return java.util.List<V>
     * @author 洛无异
     * @description //TODO 创建包含kList.size()个ClassV实例的list,并调用org.springframework.beans.BeanUtils，
     * @date 2021/4/19 11:14
     */
    public static <K, V> List<V> copyModelList(Iterable<K> kList, Class<V> classV) {
        List<V> vList = new ArrayList<>();
        try {
            for (K k : kList)
                vList.add(copyModel(k, classV.newInstance()));
        } catch (Exception e) {
            throw new BusinessException(BusinessEnum.SYSTEM_HANDLE_ERROR);
        }
        return vList;
    }

    /**
     * @param kList     source
     * @param classV    生成的list中每一项的类型
     * @param processor 每次复制时对BeanUtils.copyProperties(source, target)中的source,target调用方法processor.process(source, target)
     * @return java.util.List<V>
     * @author 洛无异
     * @description //TODO   创建包含kList.size()个ClassV实例的vlist,并调用org.springframework.beans.BeanUtils.copyProperties()， 复制kList的每一项到vlist中，之后再对vlist的每一项执行processor.process(k, v)
     * @date 2021/4/19 11:15
     */
    public static <K, V> List<V> copyModelList(Iterable<K> kList, Class<V> classV, ModelUtilsProcessor<K, V> processor)
            throws Exception {
        List<V> vList = new ArrayList<>();
        try {
            for (K k : kList)
                if (processor.filter(k))
                    vList.add(copyModel(k, classV.newInstance(), processor));
        } catch (Exception e) {
            throw new BusinessException(BusinessEnum.SYSTEM_HANDLE_ERROR);
        }
        return vList;
    }

    /**
     * @param kList
     * @param classV
     * @param filter
     * @return java.util.List<V>
     * @author 洛无异
     * @description //TODO   同上，但是接口换成了默认实现process()的ModelUtilsFilter，便于使用lambda表达式
     * @date 2021/4/19 11:15
     */
    public static <K, V> List<V> copyModelList(Iterable<K> kList, Class<V> classV, ModelUtilsFilter<K, V> filter)
            throws Exception {
        List<V> vList = new ArrayList<>();
        try {
            for (K k : kList)
                if (filter.filter(k))
                    vList.add(copyModel(k, classV.newInstance(), filter));
        } catch (Exception e) {
            throw new BusinessException(BusinessEnum.SYSTEM_HANDLE_ERROR);
        }
        return vList;
    }

    /**
     * @param kList  modelList
     * @param classK modelList的类型
     * @return java.util.List<java.lang.String>
     * @throws Exception 如果classV.getId()返回值类型不是string类型的，会抛出异常
     * @author 洛无异
     * @description //TODO 获取kList中的所有id
     * @date 2021/4/19 11:16
     */
    public static <K> List<String> getModelIdList(Iterable<K> kList, Class<K> classK) throws Exception {
        List<String> idList = new ArrayList<>();
        for (K k : kList) {
            Method getId = classK.getMethod("getId");
            Object idobj = getId.invoke(k);
            if (!(idobj instanceof String))
                throw new Exception("The return value of " + classK.toString() + ".getId() is not a String type.");

            String id = (String) idobj;
            idList.add(id);
        }

        return idList;
    }

    private static <K, V> V copyModel(K k, V v) {
        BeanUtils.copyProperties(k, v);
        return v;
    }

    private static <K, V> V copyModel(K k, V v, ModelUtilsProcessor<K, V> processor) throws Exception {
        BeanUtils.copyProperties(k, v);
        processor.process(k, v);
        return v;
    }

    private static <K, V> V copyModel(K k, V v, ModelUtilsFilter<K, V> filter) throws Exception {
        BeanUtils.copyProperties(k, v);
        filter.process(k, v);
        return v;
    }
}
