package com.hui.readwrite.multidata;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/30
 * @Time:10:32
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {
        local.set(DataSourceType.read.getType());
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        local.set(DataSourceType.write.getType());
    }

    public static String getJdbcType() {
        return local.get();
    }
}
