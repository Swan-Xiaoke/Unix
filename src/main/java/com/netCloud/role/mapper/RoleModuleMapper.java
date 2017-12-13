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
     * @return
     */
    List<Role> findAll(int first,int last);

    /**
     * 分页的数据数量
     * @return
     */
    List<Role> pageCount();

    /**
     * 根据名字查角色
     * @param role
     * @return
     */
    List<Role> findRole(Role role);

    /**
     * 添加角色
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

}
