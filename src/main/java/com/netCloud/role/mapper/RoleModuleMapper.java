package com.netCloud.role.mapper;

import com.netCloud.role.domain.RoleModule;
import com.netCloud.role.domain.Role;
import com.netCloud.utils.page.PageBean;

import java.util.List;

/**
 * Created by dllo on 17/12/8.
 */
public interface RoleModuleMapper {

    /**
     * 分页查询数据明细
     *
     * @return
     */
    List<Role> findAll(int first, int last);

    /**
     * 分页的数据数量
     *
     * @return
     */
    List<Role> pageCount();

    /**
     * 根据名字查角色
     *
     * @param role
     * @return
     */
    List<Role> findRole(Role role);

    /**
     * 添加角色
     *
     * @param role
     */
    void addRole(Role role);

    /**
     * 添加角色权限
     *
     * @param roleId
     * @param moduleId
     */
    void addMpRo(int roleId, int moduleId);

    /**
     * 查信息
     *
     * @param roleId
     * @return
     */
    String findRoleById(int roleId);

    /**
     * 根据id查出权限Id
     *
     * @param roleId
     * @return
     */
    List<RoleModule> findModuleById(int roleId);

    /**
     * 查询所有的权限
     *
     * @return
     */
    List<RoleModule> findAllModule();

    /**
     * 根据Id更改角色名称
     *
     * @param roleId
     * @return
     */
    int updateRole(String roleName, int roleId);

    /**
     * 根据roleId查询出管理管理员是否已使用
     *
     * @param roleId
     * @return
     */
    List findAdminByRoleId(int roleId);

    /**
     * 根据roleId删除角色名称
     *
     * @param roleId
     */
    void deleteRole(int roleId);

    /**
     * 根据角色Id删除权限
     *
     * @param roleId
     * @return
     */
    int deleteModule(int roleId);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAllRole();
}
