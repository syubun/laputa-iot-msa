package com.laputa.iot.common.data.mybatis.conditions;


import com.laputa.iot.common.data.mybatis.conditions.query.LaputaWrapper;
import com.laputa.iot.common.data.mybatis.conditions.update.LpuWrapper;

/**
 * Wrappers 工具类， 该方法的主要目的是为了 缩短代码长度
 *
 */
public class Wraps {

    private Wraps() {
        // ignore
    }

    /**
     * 获取 HyLambdaQueryWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    public static <T> LaputaWrapper<T> lbQ() {
        return new LaputaWrapper<>();
    }

    /**
     * 获取 HyLambdaQueryWrapper&lt;T&gt;
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    public static <T> LaputaWrapper<T> lbQ(T entity) {
        return new LaputaWrapper<>(entity);
    }

    /**
     * 获取 HyLambdaQueryWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    public static <T> LpuWrapper<T> lbU() {
        return new LpuWrapper<>();
    }

    /**
     * 获取 HyLambdaQueryWrapper&lt;T&gt;
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    public static <T> LpuWrapper<T> lbU(T entity) {
        return new LpuWrapper<>(entity);
    }

}
