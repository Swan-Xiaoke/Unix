package com.netCloud.role.controller;

import com.netCloud.role.domain.RoleModule;
import com.netCloud.role.domain.Role;
import com.netCloud.role.service.impl.RoleServiceImpl;
import com.netCloud.utils.page.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dllo on 17/12/8.
 */
@Controller
public class RoleController {

    @Resource
    private RoleServiceImpl service;


    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping(value = "/findAllRole")
    @ResponseBody
    public List<Role> findAllRole(){
        return service.findAllRole();
    }


    /**
     * 分页查询角色列表
     *
     * @return
     */
    @RequestMapping(value = "/findAllRoleByPage")
    @ResponseBody
    public List<Role> findAll(PageBean page) {
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

    /**
     * 修改传参
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/updateRole")
    @ResponseBody
    public String update(int roleId, HttpSession session) {
        session.setAttribute("roleId", roleId);
        return "success";
    }

    /**
     * 修改跳转
     *
     * @return
     */
    @RequestMapping(value = "/update_Role")
    public String update_Role() {
        return "role/role_modi";
    }

    /**
     * 更改角色及相关权限
     * @param session
     * @param moduleId
     * @return
     */
    @RequestMapping(value = "/updateRoleModule")
    @ResponseBody
    public String updateRole(String roleName,HttpSession session,Integer[] moduleId){
        int roleId = (int) session.getAttribute("roleId");
        String result= service.updateRole(roleName, roleId, moduleId);
        System.out.println(result);
        return result;
    }

    /**
     * 角色名称回显
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/echoRoleName", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String echoRole(HttpSession session) {
        int roleId = (int) session.getAttribute("roleId");
        String roleName = service.findRoleNameById(roleId);
        return roleName;
    }

    /**
     * 已选择权限框勾选回显
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/echoModuleId")
    @ResponseBody
    public List echoModule(HttpSession session) {
        int roleId = (int) session.getAttribute("roleId");
        List ids = service.findModuleIdById(roleId);
        return ids;
    }

    /**
     * 查询所有权限
     *
     * @return
     */
    @RequestMapping(value = "/findAllModule")
    @ResponseBody
    public List<RoleModule> findAllModule() {
        return service.findAllModule();
    }


    /**
     * 角色删除
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/deleteRole")
    @ResponseBody
    public String deleteRole(int roleId){
        String result = service.deleteRole(roleId);
        return result;
    }

}
