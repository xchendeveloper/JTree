package com.suppercoder.java.jtree;

import java.util.Map;

/**
 * if data field is not java bean , creating your own NodeData2MapGenerator to generate map structure
 *
 * @author chenxing
 * @create 2018-02-01 11:13
 **/

public interface NodeData2MapGenerator {
    Map generate(Object obj);
}
