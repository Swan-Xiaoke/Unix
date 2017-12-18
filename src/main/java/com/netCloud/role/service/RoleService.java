package com.netCloud.role.service;

import com.netCloud.role.domain.RoleModule;
import com.netCloud.role.domain.Role;
import com.netCloud.utils.page.PageBean;

import java.util.List;

/**
 * Created by dllo on 17/12/8.
 */
public interface RoleService {

    List<Role> findAll(PageBean page);

    String addRole(Role role,Integer[] moduleId);

    PageBean page(PageBean page);

    String findRoleNameById(int roleId);

    List<RoleModule> findAllModule();

    String updateRole(String roleName,int roleId, Integer[] moduleId);

    String deleteRole(int roleId);

    List<Role> findAllRole();
}
