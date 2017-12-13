package com.netCloud.role.controller;

import com.netCloud.role.domain.RoleModule;
import com.netCloud.role.domain.Role;
import com.netCloud.role.service.impl.RoleServiceImpl;
import com.netCloud.utils.page.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/12/8.
 */
@Controller
public class RoleController {

    @Resource
    private RoleServiceImpl service;

    /**
     * 查询角色列表
     *
     * @return
     */
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<Role> findAll(PageBean page) {
        System.out.println(service.findAll(page));
        return service.findAll(page);
    }

    /**
     * 列表页面跳转
     *
     * @return
     */
    @RequestMapping(value = "/role_list")
    public String role_list() {
        return "role/role_list";
    }

    /**
     * 添加页面跳转
     *
     * @return
     */
    @RequestMapping(value = "/role_add")
    public String role_add() {
        return "role/role_add";
    }

    /**
     * 添加角色
     *
     * @param role
     * @param moduleId
     * @return
     */
    @RequestMapping(value = "/addRole")
    @ResponseBody
    public String addRole(Role role, Integer[] moduleId) {
        return service.addRole(role, moduleId);
    }

    /**
     * 分页
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/count")
    @ResponseBody
    public PageBean pageCount(PageBean page) {
        return service.page(page);
    }

}
