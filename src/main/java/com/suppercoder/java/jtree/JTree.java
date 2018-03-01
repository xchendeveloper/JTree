package com.suppercoder.java.jtree;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * JTree
 *
 * @author chenxing
 * @create 2017-12-25 21:11
 **/

public class JTree {

    private String id = "id";
    private String pId = "pId";

    private static ConcurrentMap<String,JTree> cache = new ConcurrentHashMap<String,JTree>();

    private JTree() {
    }

    private JTree(String id, String pId) {
        this.id = id;
        this.pId = pId;
    }

    /**
     * set the name of the field for id and parentId
     *
     * @param id  id's field name
     * @param pId pid's field name
     * @return
     */
    public static JTree init(String treeName,String id, String pId) {
        if(cache.containsKey(treeName)){
            throw new TreeInitException(treeName + " has init , do not repeat initialization");
        }
        JTree jTree = new JTree(id, pId);
        cache.put(treeName,new JTree(id, pId));
        return jTree;
    }

    /**
     * get jtree by treename
     * @param treeName
     * @return
     */
    public static JTree get(String treeName){
        if(!cache.containsKey(treeName)){
            throw new TreeInitException(treeName + " has not init ");
        }
        return cache.get(treeName);
    }

    /**
     * generate tree
     *
     * @param dataList data list
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Node generalTree(List dataList) {
        Node root = new Node(null, new ArrayList<Node>());
        List<Node> nodeList = generalNodeList(dataList);

        for (Node node : nodeList) {
            Object parentId = getFieldValue(node.getData(), pId);
            int count = 0;
            for (Node node1 : nodeList) {
                if (getFieldValue(node1.getData(), id).equals(parentId)) {
                    count++;
                    node1.getSubNodeList().add(node);
                    node.setParentNode(node1);
                    break;
                }
            }
            if (count == 0) {
                //is root node
                root.getSubNodeList().add(node);
            }
        }

        if (root.getSubNodeList().size() == 1) {
            //only root node
            return root.getSubNodeList().get(0);
        }

        return root;
    }

    //===================base method begin===================

    /**
     * transfer tree object to node list
     *
     * @param tree
     * @param nodeList
     * @return
     */
    public List<Node> treeToNodeList(Node tree, List<Node> nodeList) {

        if (tree.getSubNodeList().size() == 0 || tree.getParentNode() == null) {
            nodeList.add(tree);
        }

        for (Node node : tree.getSubNodeList()) {
            nodeList.add(node);
            if (node.getSubNodeList().size() > 0) {
                treeToNodeList(node, nodeList);
            }
        }

        return nodeList;
    }

    /**
     * Getting node through id
     *
     * @param tree tree
     * @param id   id
     * @return
     */
    public Node getNodeById(Node tree, Object id) {
        if (tree != null && tree.getData() != null && getFieldValue(tree.getData(), this.id).equals(id)) {
            return tree;
        }
        for (Node node : tree.getSubNodeList()) {
            Node returnVal = getNodeById(node, id);
            if (returnVal == null) {
                continue;
            }
            return returnVal;
        }
        return null;
    }

    /**
     * Get all the sub nodes under the specified node
     *
     * @param node        specified node
     * @param recursion   Whether or not recursion
     * @param includeSelf Whether or not it contains itself in the return list
     * @return
     */
    public List<Node> getSubNodeList(Node node, boolean recursion, boolean includeSelf) {
        List<Node> subNodeList = new ArrayList<Node>();
        if (recursion) {
            getSubNodeListRecursion(node, subNodeList);
            if (!includeSelf && subNodeList.size() > 0) {
                subNodeList.remove(0);
            }
            return subNodeList;
        }

        return node.getSubNodeList();

    }

    /**
     * Get a list of the node Id of the specified node
     *
     * @param node
     * @param recursion
     * @param includeSelf
     * @return
     */
    public List<Object> getSubNodeIdList(Node node, boolean recursion, boolean includeSelf) {
        List<Object> subNodeList = new ArrayList<Object>();
        if (recursion) {
            getSubNodeIdListRecursion(node, subNodeList);
            if (!includeSelf && subNodeList.size() > 0) {
                subNodeList.remove(0);
            }
            return subNodeList;
        }

        for (Node nd : node.getSubNodeList()) {
            subNodeList.add(getFieldValue(nd.getData(), this.id));
        }

        return subNodeList;
    }


    //===================base method end===================


    //===================extend method begin===================

    /**
     * get the sub nodes of the specified node under the tree
     *
     * @param tree
     * @param nodeId
     * @param recursion
     * @param includeSelf
     * @return
     */
    public List<Node> getTreeSubNodeListById(Node tree, Object nodeId, boolean recursion, boolean includeSelf) {
        Node parentNode = getNodeById(tree, nodeId);
        return getSubNodeList(parentNode, recursion, includeSelf);
    }

    /**
     * get a list of the child node Id of the specified node under the tree
     *
     * @param tree
     * @param nodeId
     * @param recursion
     * @param includeSelf
     * @return
     */
    public List<Object> getTreeSubNodeIdListById(Node tree, Object nodeId, boolean recursion, boolean includeSelf) {
        Node parentNode = getNodeById(tree, nodeId);
        return getSubNodeIdList(parentNode, recursion, includeSelf);
    }


    //===================extend method end===================


    //===================help method begin===================

    /**
     * Recursively get all the sub nodes (including themselves) under the specified node
     *
     * @param node
     * @param result
     */
    private void getSubNodeListRecursion(Node node, List<Node> result) {
        if (node != null) {
            result.add(node);
            for (Node subNode : node.getSubNodeList()) {
                getSubNodeListRecursion(subNode, result);
            }
        }
    }

    /**
     * Recursively get a list of Id (including itself) of all the sub nodes under the specified node
     *
     * @param node
     * @param result
     */
    private void getSubNodeIdListRecursion(Node node, List<Object> result) {
        if (node != null) {
            result.add(getFieldValue(node.getData(), this.id));
            for (Node subNode : node.getSubNodeList()) {
                getSubNodeIdListRecursion(subNode, result);
            }
        }
    }

    /**
     * Assemble the data list into a node list
     *
     * @param dataList
     * @return
     */
    private List<Node> generalNodeList(List dataList) {
        List<Node> nl = new ArrayList<Node>();
        for (Object data : dataList) {
            Node node = new Node(data, new ArrayList<Node>());
            nl.add(node);
        }
        return nl;

    }

    /**
     * Get the value of the specified fieldName in the specified bean
     *
     * @param bean
     * @param fieldName
     * @return
     */
    private Object getFieldValue(Object bean, String fieldName) {
        return BeanUtil.getFieldValue(bean, fieldName);
    }

}
