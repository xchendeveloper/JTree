package com.suppercoder.java.jtree;
import java.util.Map;

/**
 * java bean to map generator
 *
 * @author chenxing
 * @create 2018-02-01 12:11
 **/

public class Bean2MapGenerator implements NodeData2MapGenerator {
    public Map generate(Object obj) {
        return BeanUtil.beanToMap(obj);
    }
}
