package com.zxrail.app.report.utils;


import com.zxrail.app.report.errorcode.ReportErrorCode;
import com.zxrail.framework.common.errorcode.ServiceException;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanMap;

/**
 * @ClassName: __BeanUtil
 * @Description: cglib bean工具类
 */
public class __BeanUtil {

    /**
     * @return T
     * @MethodName: copy
     * @Description: 属性拷贝，将from中的属性全部拷贝至to
     */
    public static <T, F> T copy(F from, T to) {
        try {
            BeanMap.create(to).putAll(BeanMap.create(from));
            return to;
        } catch (Exception e) {
            throw new ServiceException(ReportErrorCode.APP_RUNTIME_EXCEPTION_ERROR, "class " + from.getClass().getCanonicalName() + " can not copy to class " + to.getClass().getCanonicalName());
        }
    }

    /**
     * @MethodName: convert
     * @Description: 对象转换，将from转换为to
     */
    public static <T, F> T convert(F from, Class<T> clazz) {
        try {
            T to = clazz.newInstance();
            BeanMap.create(to).putAll(BeanMap.create(from));
            return to;
        } catch (Exception e) {
            throw new ServiceException(ReportErrorCode.APP_RUNTIME_EXCEPTION_ERROR, "class " + from.getClass().getCanonicalName() + " can not convert to class " + clazz.getCanonicalName());
        }
    }
}
