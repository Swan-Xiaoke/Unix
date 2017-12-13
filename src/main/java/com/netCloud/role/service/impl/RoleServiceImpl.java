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
        PageBean pageBean = new PageBean(page.getPageNum(),5,roles.size());
        return mapper.findAll((pageBean.getPageNum()-1)*5 ,5);
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
        return new PageBean(page.getPageNum(),5,totalPage);
    }

}
