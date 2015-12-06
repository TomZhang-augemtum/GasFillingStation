package com.gas.model;

import java.io.Serializable;

public class RoleMenuID implements Serializable {
    private Long roleid;
    private Long menuid;

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

}
