package cn.theoriz.ruoyi.common.utils;

public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {
    public static boolean isNull(Object o) {
        return allNull(o);
    }

    public static boolean isNotNull(Object o) {
        return allNotNull(o);
    }
}
