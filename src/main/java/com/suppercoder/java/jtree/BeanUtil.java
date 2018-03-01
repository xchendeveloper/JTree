package com.suppercoder.java.jtree;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * java bean util
 *
 * @author chenxing
 * @create 2018-03-01 10:32
 **/

public class BeanUtil {

    public static Map<String, Object> beanToMap(Object bean) {
        BeanInfo beanInfo = null;
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor pd : pds){
                String key = pd.getName();
                if(key.equals("class")){
                    continue;
                }
                Object value = pd.getReadMethod().invoke(bean);
                result.put(key,value);
            }
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static Object getFieldValue(Object bean, String fieldName){
        if (null == bean ) {
            return null;
        }

        if (bean instanceof Map) {
            return ((Map<?, ?>) bean).get(fieldName);
        } else if (bean instanceof List) {
            return ((List<?>) bean).get(Integer.parseInt(fieldName));
        } else if (bean instanceof Collection) {
            return ((Collection<?>) bean).toArray()[Integer.parseInt(fieldName)];
        } else if (bean.getClass().isArray()) {
            return Array.get(bean, Integer.parseInt(fieldName));
        } else {
            Field field;
            try {
                field = bean.getClass().getDeclaredField(fieldName);
                if (null != field) {
                    field.setAccessible(true);
                    return field.get(bean);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}
