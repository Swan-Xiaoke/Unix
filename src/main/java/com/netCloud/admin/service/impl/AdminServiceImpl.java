package com.netCloud.admin.service.impl;

import com.netCloud.admin.domain.Admin;
import com.netCloud.admin.mapper.AdminMapper;
import com.netCloud.admin.service.AdminService;
import com.netCloud.role.domain.AdminRole;
import com.netCloud.role.domain.Module;
import com.netCloud.role.domain.Role;
import com.netCloud.role.domain.RoleModule;
import com.netCloud.utils.page.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by dllo on 17/12/7.
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private HttpSession session;

    /**
     * 登录方法
     *
     * @param admin
     * @param verifyCode
     * @return
     */
    @Override
    public List login(Admin admin, String verifyCode) {
        String imageCode = (String) session.getAttribute("imageCode");
        List<Admin> admins = adminMapper.login(admin);
        if (admins.size() > 0) {
            session.setMaxInactiveInterval(30 * 60);
            session.setAttribute("user", admins.get(0));
            session.setAttribute("adminIds", admins.get(0).getAdminId());
        }
        List error = new ArrayList();
        if (admin.getAdminCode().equals("")) {
            error.add("adminCodeError");
        } else if (admin.getPassword().equals("")) {
            error.add("passwordError");
        } else if (admins.size() == 0) {
            error.add("loginError");
        }
        if (verifyCode.equals("")) {
            error.add("emptyCodeError");
        } else if (!imageCode.equalsIgnoreCase(verifyCode)) {
            error.add("verifyCodeError");
        }
        if (error.size() == 0) {
            error.add("success");
        }
        return error;
    }

    /**
     * 分页
     *
     * @param page
     * @return
     */
    @Override
    public PageBean page(PageBean page, HttpSession session) {
        int totalPage = (int) session.getAttribute("page");
        return new PageBean(page.getPageNum(), 5, totalPage);
    }

    /**
     * 分页查询管理员信息
     *
     * @param page
     * @return
     */
    @Override
    public List<Admin> findAdminByPage(PageBean page, int moduleId, String roleName) {
        List<Admin> list = new ArrayList();
        List<Admin> result = new ArrayList<>();
        Set<Integer> ars = new HashSet<>();
        //如果搜索框无信息
        if (moduleId == -1 && roleName.equals("")) {
            list = adminMapper.findAdminByPage(0);
        } else {
            //角色信息
            if (roleName != "") {
                List<Role> rs = adminMapper.findRoleIdByRoleName("%" + roleName + "%"); //模糊查询
                for (Role role : rs) {
                    //根据查出来的角色Id查出对应的管理者Id并用set去重
                    List<AdminRole> roleList = adminMapper.findAdminRoleByRoleId(role.getRoleId());
                    for (AdminRole adminRole : roleList) {
                        ars.add(adminRole.getAdminId());
                    }
                }
            }

            if (moduleId != -1) {
                //根据moduleId查询出中间表对应的roleId
                List<RoleModule> rmList = adminMapper.findRoleIdByModuleId(moduleId);
                for (RoleModule roleModule : rmList) {
                    List<AdminRole> adminRoles = adminMapper.findAdminRoleByRoleId(roleModule.getRoleId());
                    for (AdminRole adminRole : adminRoles) {
                        ars.add(adminRole.getAdminId());
                    }
                }
            }

            System.out.println(ars);
            for (int ar : ars) {
                //根据管理者Id查出对应的管理者
                list.add(adminMapper.findAdminByPage(ar).get(0));
            }
        }

        session.setAttribute("page", list.size());
        int pageNum = (page.getPageNum() - 1) * 5;
        int end = pageNum + 5;
        if (end >= list.size()) {
            end = list.size();
        }
        for (int i = pageNum; i < end; i++) {
            result.add(list.get(i));
        }
        return result;
    }

    /**
     * 添加
     *
     * @return
     */
    @Override
    public List addAdmin(Admin admin, String rePassword, Integer[] roleId) {

        List list = new ArrayList();
        if (admin.getName().equals("") || admin.getName() == null) {
            list.add("nameEmpty");
        }
        if (admin.getAdminCode().equals("") || admin.getAdminCode() == null) {
            list.add("adminCodeEmpty");
        }
        if (admin.getPassword().equals("") || admin.getPassword() == null) {
            list.add("pwdEmpty");
        }
        if (rePassword.equals("")) {
            list.add("rePwdEmpty");
        } else if (!rePassword.equals(admin.getPassword())) {
            list.add("rePwdFormat");
        }
        if (admin.getTelephone().equals("") || admin.getTelephone() == null) {
            list.add("telEmpty");
        }
        if (admin.getEmail().equals("") || admin.getEmail() == null) {
            list.add("emailEmpty");
        }
        if (roleId[0] == 0) {
            list.add("roleEmpty");
        }

        if (list.size() == 0) {
            admin.setEnrolldate(new Date());    //给定添加时间
            try {
                adminMapper.addAdmin(admin);
            } catch (Exception e) {
                list.add("codeExist");
            }
            if (list.size() == 0) {
                for (Integer integer : roleId) {
                    adminMapper.addAdminRole(admin.getAdminId(), integer);
                }
                list.add("success");
            }
        }

        return list;
    }

    /**
     * 删除管理员
     *
     * @param adminId
     * @return
     */
    @Override
    public int deleteAdmin(int adminId) {
        adminMapper.deleteAdminRole(adminId);
        int result = adminMapper.deleteAdmin(adminId);
        System.out.println(result);
        return result;
    }

    /**
     * 添加管理员信息回显
     *
     * @return
     */
    @Override
    public List<Admin> echoAdmin() {
        int adminId = (int) session.getAttribute("adminId");
        return adminMapper.findAdminById(adminId);
    }

    /**
     * 添加管理员角色回显
     *
     * @return
     */
    @Override
    public List<AdminRole> echoAdminRole() {
        int adminId = (int) session.getAttribute("adminId");
        return adminMapper.findRoleByAdminId(adminId);
    }

    /**
     * 修改角色
     *
     * @param admin
     * @param roleId
     * @return
     */
    @Override
    public List updateAdmin(Admin admin, Integer[] roleId) {
        List list = new ArrayList();
        if (admin.getName().equals("") || admin.getName() == null) {
            list.add("nameEmpty");
        }
        if (admin.getTelephone().equals("") || admin.getTelephone() == null) {
            list.add("telEmpty");
        }
        if (admin.getEmail().equals("") || admin.getEmail() == null) {
            list.add("emailEmpty");
        }
        if (roleId[0] == 0) {
            list.add("roleEmpty");
        }
        if (list.size() == 0) {
            try {
                adminMapper.updateAdmin(admin);
            } catch (Exception e) {
                list.add("codeExist");
            }
            if (list.size() == 0) {
                adminMapper.deleteAdminRole(admin.getAdminId());
                for (Integer integer : roleId) {
                    adminMapper.addAdminRole(admin.getAdminId(), integer);
                }
                list.add("success");
            }
        }
        return list;
    }

    /**
     * 查询管理员所有权限
     *
     * @param adminId
     * @return
     */
    @Override
    public Set<RoleModule> findAdminMo(Integer adminId) {
        Set<RoleModule> rm = new HashSet<>();
        List<AdminRole> roles = adminMapper.findRoleByAdminId(adminId);
        for (AdminRole role : roles) {
            List<RoleModule> moduleIdByRole = adminMapper.findModuleIdByRoleId(role.getRoleId());
            rm.addAll(moduleIdByRole);
        }
        return rm;
    }

    /**
     * 根据管理员Id查角色
     *
     * @return
     */
    @Override
    public List findRoleByUserId(int adminId) {
        List list = new ArrayList();
        List<Role> roles = new ArrayList<>();
        List<AdminRole> adminRoles = adminMapper.findRoleByAdminId(adminId);
        for (AdminRole adminRole : adminRoles) {
            roles.add(adminMapper.findRoleNameByRoleId(adminRole.getRoleId()).get(0));
        }
        for (Role role : roles) {
            list.add(role.getRoleName());
        }
        return list;
    }

    /**
     * 更该个人信息
     *
     * @param admin
     */
    @Override
    public void changeAdminInfo(Admin admin) {
        adminMapper.updateAdmin(admin);
    }

    /**
     * 修改个人的密码
     *
     * @param oldPwd
     * @param newPwd
     * @param reNewPwd
     * @return
     */
    @Override
    public List changeAdminPwd(String oldPwd, String newPwd, String reNewPwd) {
        Admin user = (Admin) session.getAttribute("user");
        List list = new ArrayList();
        if (oldPwd.equals("")) {
            list.add("passwordEmpty");
        }
        if (newPwd.equals("")) {
            list.add("newPwdEmpty");
        }
        if (!newPwd.equals(reNewPwd)) {
            list.add("newPwdError");
        }
        System.out.println(user.getPassword());
        if (!user.getPassword().equals(oldPwd)) {
            list.add("passwordError");
        } else if(list.size() == 0) {
            user.setPassword(newPwd);
            System.out.println(user);
            adminMapper.updateAdmin(user);
            list.add("success");
        }
        return list;
    }
}
