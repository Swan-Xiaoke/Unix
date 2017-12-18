package com.netCloud.role.domain;

/**
 * Created by dllo on 17/12/15.
 */
public class AdminRole {
    private int adminId,roleId;

    public AdminRole() {
    }

    public AdminRole(int adminId, int roleId) {
        this.adminId = adminId;
        this.roleId = roleId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "AdminRole{" +
                "adminId=" + adminId +
                ", roleId=" + roleId +
                '}';
    }
}
