# JTree

[![Build Status](https://travis-ci.org/xchendeveloper/JTree.svg?branch=master)](https://travis-ci.org/xchendeveloper/JTree)

Simple fast generation tree and traversing tree tools

# How to use

## Get JTree 
Specify the name of id and parentId of dataItem
```java
Jtree jtree = JTree.init("mytree","xxxid","xxxpid");
```
Using "id" and "pid" as data items by default
```java
Jtree jtree = JTree.init("mytree");
```

## Generate tree
```java
Node tree = jtree.generalTree(dataList);
// For details, please see test case
```
we can also do it when we init JTree
```java
Node tree = JTree.init("mytree").generalTree(dataList);
```

## transfer tree to map
```java
Map<String,Object> map = tree.toMap();
```
if your dateItem is not javaBean,toMap() function will failed，you should implement your own NodeData2MapGenerator，eg:
```java
public class Bean2MapGenerator implements NodeData2MapGenerator {
    public Map generate(Object obj) {
        return BeanUtil.beanToMap(obj);
    }
}
```
and use your generator like this:
```java
tree.toMap(new Bean2MapGenerator);
```

## Transfer tree to node list
```java
Node node = JTree.init("mytree").generalTree(dataList);
List<Node> nodeList = new ArrayList<Node>();
JTree.get("mytree").treeToNodeList(node,nodeList);
```

## Getting node through id
```java
Node node = JTree.init("mytree").generalTree(dataList);
Node node = JTree.get("mytree").getNodeById(node,3);
```

## Get all the sub nodes under the specified node
```java
Node node = JTree.init("mytree").generalTree(dataList);
Node node1 = JTree.get("mytree").getNodeById(node,3);
// getSubNodeList(Node node, boolean recursion, boolean includeSelf)
List<Node> nodeList = JTree.get("mytree").getSubNodeList(node1,true,true); 

```

## Get all the sub nodes under the specified nodeid under the tree
```java
Node node = JTree.init("mytree").generalTree(dataList);
List<Node> nodeList = JTree.get("mytree").getTreeSubNodeListById(node,3,true,true);
```

## Get a list of the node Id of the specified node
```java
Node node = JTree.init("mytree").generalTree(dataList);
Node node1 = JTree.get("mytree").getNodeById(node,3);
List<Object> idList = JTree.get("mytree").getSubNodeIdList(node1,true,true);
```

## Get a list of the node Id of the specified nodeid under the tree
```java
Node node = JTree.init("mytree").generalTree(dataList);
List<Object> nodeList = JTree.get("mytree").getTreeSubNodeIdListById(node,3,true,true);
```




