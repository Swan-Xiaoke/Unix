package com.netCloud.role.service.impl;

import com.netCloud.role.domain.RoleModule;
import com.netCloud.role.domain.Role;
import com.netCloud.role.mapper.RoleModuleMapper;
import com.netCloud.role.service.RoleService;
import com.netCloud.utils.page.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/12/8.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleModuleMapper mapper;

    /**
     * 查找所有角色
     * 分页显示
     *
     * @return
     */
    @Override
    public List<Role> findAll(PageBean page) {
        List<Role> roles = mapper.pageCount();
        PageBean pageBean = new PageBean(page.getPageNum(), 5, roles.size());
        return mapper.findAll((pageBean.getPageNum() - 1) * 5, 5);
    }

    /**
     * 添加角色并获取Id
     *
     * @param role
     */
    @Override
    public String addRole(Role role, Integer[] moduleId) {
        List<Role> list = mapper.findRole(role);
        if (list.size() == 0) {
            mapper.addRole(role);
            int roleId = role.getRoleId();
            for (int i = 0; i < moduleId.length; i++) {
                mapper.addMpRo(roleId, moduleId[i]);
            }
            return "success";
        } else {
            return "false";
        }
    }

    /**
     * 分页
     *
     * @param page
     * @return
     */
    @Override
    public PageBean page(PageBean page) {
        int totalPage = mapper.pageCount().size();
        return new PageBean(page.getPageNum(), 5, totalPage);
    }

    /**
     * 根据Id查名字
     *
     * @param roleId
     * @return
     */
    @Override
    public String findRoleNameById(int roleId) {
        return mapper.findRoleById(roleId);

    }

    /**
     * 查询所有的权限
     *
     * @return
     */
    @Override
    public List<RoleModule> findAllModule() {
        return mapper.findAllModule();
    }

    /**
     * 修改角色以及角色权限
     *
     * @param roleId
     * @param moduleId
     * @return
     */
    @Override
    public String updateRole(String roleName, int roleId, Integer[] moduleId) {
        try {
            mapper.updateRole(roleName, roleId);
        } catch (Exception e) {
            return "false";
        }
        mapper.deleteModule(roleId);
        for (Integer integer : moduleId) {
            mapper.addMpRo(roleId, integer);
        }
        return "success";
    }


    /**
     * 根据Id查出
     *
     * @param roleId
     * @return
     */
    public List<RoleModule> findModuleIdById(int roleId) {
        return mapper.findModuleById(roleId);
    }

    /**
     * 根据Id删除角色
     * @param roleId
     * @return
     */
    @Override
    public String deleteRole(int roleId) {
        List list =  mapper.findAdminByRoleId(roleId);
        if(list.size() > 0){
            return "false";
        }else {
            mapper.deleteRole(roleId);
            mapper.deleteModule(roleId);
            return "success";
        }
    }

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> findAllRole() {
        return mapper.findAllRole();
    }


}
