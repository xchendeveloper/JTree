package com.suppercoder.java;

/**
 * ${DESCRIPTION}
 *
 * @author chenxing
 * @create 2018-03-01 11:03
 **/

public class Organization {

    public Organization(int orgId, int parentId, String name) {
        this.orgId = orgId;
        this.parentId = parentId;
        this.name = name;
    }

    private int orgId;
    private int parentId;
    private String name;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
