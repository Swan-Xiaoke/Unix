package com.netCloud.admin.mapper;

import com.netCloud.admin.domain.Admin;
import com.netCloud.role.domain.AdminRole;
import com.netCloud.role.domain.Role;
import com.netCloud.role.domain.RoleModule;

import java.util.List;


public interface AdminMapper {

    /**
     * 登录验证
     *
     * @param record
     * @return
     */
    List<Admin> login(Admin record);

    /**
     * 根据页数显示数据
     *
     * @return
     */
    List<Admin> findAdminByPage(int adminId);

    /**
     * 添加管理员
     *
     * @param admin
     */
    void addAdmin(Admin admin);

    /**
     * 添加管理员角色
     *
     * @param adminId
     * @param integer
     */
    void addAdminRole(Integer adminId, Integer integer);

    /**
     * 根据Id删除管理员和角色的中间表
     *
     * @param adminId
     */
    void deleteAdminRole(int adminId);

    /**
     * 根据Id删除管理员
     *
     * @param adminId
     * @return
     */
    int deleteAdmin(int adminId);

    /**
     * 根据Id查询管理员
     *
     * @param adminId
     * @return
     */
    List<Admin> findAdminById(int adminId);

    /**
     * 根据id查询当前管理员所有角色
     *
     * @param adminId
     * @return
     */
    List<AdminRole> findRoleByAdminId(int adminId);

    /**
     * 根据Id修改管理员信息
     *
     * @param admin
     */
    void updateAdmin(Admin admin);

    /**
     * 根据roleName查询出对应的RoleId
     *
     * @return
     */
    List<Role> findRoleIdByRoleName(String roleName);

    /**
     * 根据角色Id查出中间表中对应的角色Id
     * @param roleId
     * @return
     */
    List<AdminRole> findAdminRoleByRoleId(int roleId);

    /**
     * 根据权限Id查询出对应的角色Id
     * @param moduleId
     * @return
     */
    List<RoleModule> findRoleIdByModuleId(int moduleId);

    /**
     * 通过角色查权限
     * @param roleId
     */
    List<RoleModule> findModuleIdByRoleId(int roleId);

    /**
     * 根据角色Id查询出角色名字
     * @return
     */
    List<Role> findRoleNameByRoleId(int roleId);

}