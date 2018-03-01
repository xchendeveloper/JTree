package com.suppercoder.java;

import com.suppercoder.java.jtree.JTree;
import com.suppercoder.java.jtree.Node;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.lang.Console;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * test
 *
 * @author chenxing
 * @create 2018-03-01 11:03
 **/

public class JTreeTest {

    List<Organization> dataList = null;

    @Before
    public void setUp() {
        dataList = new ArrayList<Organization>();
        Organization root = new Organization(1, 0, "root");
        Organization subNode1 = new Organization(2, 1, "subNode1");
        Organization subNode2 = new Organization(3, 1, "subNode2");
        Organization subNode3 = new Organization(4, 1, "subNode3");
        Organization subNode4 = new Organization(5, 1, "subNode4");
        Organization subNode5 = new Organization(6, 1, "subNode5");

        Organization subNode1_SubNode1 = new Organization(7, 2, "subNode1-SubNode1");
        Organization subNode1_SubNode2 = new Organization(8, 2, "subNode1-SubNode1");
        Organization subNode1_SubNode3 = new Organization(9, 2, "subNode1-SubNode1");

        Organization subNode2_SubNode1 = new Organization(10, 3, "subNode1-SubNode1");
        Organization subNode2_SubNode2 = new Organization(11, 3, "subNode1-SubNode1");
        Organization subNode2_SubNode3 = new Organization(12, 3, "subNode1-SubNode1");

        Organization subNode2_SubNode1_SubNode1 = new Organization(13, 7, "subNode1-SubNode1-SubNode1");
        Organization subNode2_SubNode1_SubNode2 = new Organization(14, 7, "subNode1-SubNode1-SubNode2");

        dataList.add(root);
        dataList.add(subNode1);
        dataList.add(subNode2);
        dataList.add(subNode3);
        dataList.add(subNode4);
        dataList.add(subNode5);

        dataList.add(subNode1_SubNode1);
        dataList.add(subNode1_SubNode2);
        dataList.add(subNode1_SubNode3);

        dataList.add(subNode2_SubNode1);
        dataList.add(subNode2_SubNode2);
        dataList.add(subNode2_SubNode3);

        dataList.add(subNode2_SubNode1_SubNode1);
        dataList.add(subNode2_SubNode1_SubNode2);

    }

    @Test
    public void generalTree(){
        Node node = JTree.init("mytree","orgId","parentId").generalTree(dataList);
        Map<String,Object> result = node.toMap();
        Console.log(JSONUtil.toJsonPrettyStr(result));
    }

    @Test
    public void treeToNodeList(){
        Node node = JTree.init("mytree","orgId","parentId").generalTree(dataList);
        List<Node> nodeList = new ArrayList<Node>();
        JTree.get("mytree").treeToNodeList(node,nodeList);
        for(Node node1 : nodeList){
            Organization organization = (Organization) node1.getData();
            Console.log(organization.getOrgId() + "-"+ organization.getParentId()+"-"+organization.getName());
        }
    }

    @Test
    public void getNodeById(){
        Node node = JTree.init("mytree","orgId","parentId").generalTree(dataList);
        Node node1 = JTree.get("mytree").getNodeById(node,3);
        Organization organization = (Organization) node1.getData();
        Console.log(organization.getOrgId() + "-"+ organization.getParentId()+"-"+organization.getName());
    }

    @Test
    public void getSubNodeList(){
        Node node = JTree.init("mytree","orgId","parentId").generalTree(dataList);
        Node node1 = JTree.get("mytree").getNodeById(node,3);
        List<Node> nodeList = JTree.get("mytree").getSubNodeList(node1,true,true);
        for(Node node2 : nodeList){
            Organization organization = (Organization) node2.getData();
            Console.log(organization.getOrgId() + "-"+ organization.getParentId()+"-"+organization.getName());
        }
    }

    @Test
    public void getSubNodeIdList(){
        Node node = JTree.init("mytree","orgId","parentId").generalTree(dataList);
        Node node1 = JTree.get("mytree").getNodeById(node,3);
        List<Object> list = JTree.get("mytree").getSubNodeIdList(node1,true,true);
        for(Object id : list){
            Console.log(id);
        }
    }

    @Test
    public void getTreeSubNodeListById(){
        Node node = JTree.init("mytree","orgId","parentId").generalTree(dataList);
        List<Node> nodeList = JTree.get("mytree").getTreeSubNodeListById(node,3,true,true);
        for(Node node2 : nodeList){
            Organization organization = (Organization) node2.getData();
            Console.log(organization.getOrgId() + "-"+ organization.getParentId()+"-"+organization.getName());
        }
    }

    @Test
    public void getTreeSubNodeIdListById(){
        Node node = JTree.init("mytree","orgId","parentId").generalTree(dataList);
        List<Object> nodeList = JTree.get("mytree").getTreeSubNodeIdListById(node,3,true,true);
        for(Object id : nodeList){
            Console.log(id);
        }
    }

}
