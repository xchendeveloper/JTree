package com.suppercoder.java.jtree;

/**
 * get NodeData2MapGenerator
 *
 * @author chenxing
 * @create 2018-02-01 12:10
 **/

public class Data2MapFactory {

    public static NodeData2MapGenerator get(Node node){
        Object o = node.getData();
        return new Bean2MapGenerator();
    }

}
