package com.suppercoder.java.jtree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {

    private Object data;
    private Node parentNode;
    private List<Node> subNodeList;

    public Node(Object data, List<Node> subNodeList){
        this.data = data;
        this.subNodeList = subNodeList;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public List<Node> getSubNodeList() {
        return subNodeList;
    }

    public void setSubNodeList(List<Node> subNodeList) {
        this.subNodeList = subNodeList;
    }

    //Node to map
    public Map toMap(){
        NodeData2MapGenerator generator = Data2MapFactory.get(this);
        return toMap(generator);
    }

    /**
     * if data field is not java bean , creating your own NodeData2MapGenerator to generate map structure
     * @param generator
     * @return
     */
    public Map toMap(NodeData2MapGenerator generator){
        Map map = generator.generate(this.getData());
        toMap(this,map,generator);
        return map;
    }

    private void toMap(Node node , Map map, NodeData2MapGenerator generator){
        List<Node> subNodeList = node.getSubNodeList();
        List<Map> subNodeMapList = new ArrayList<Map>();
        if(subNodeList.size() > 0){
            for(Node n : subNodeList){
                Map subNodeMap = generator.generate(n.getData());
                subNodeMapList.add(subNodeMap);
                if(n.getSubNodeList().size() > 0){
                    toMap(n,subNodeMap,generator);
                }
            }
        }
        map.put("subNodeList",subNodeMapList);
    }

}
