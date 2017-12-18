package com.netCloud.admin.service.impl;

import com.netCloud.admin.domain.Admin;
import com.netCloud.admin.mapper.AdminMapper;
import com.netCloud.admin.service.AdminService;
import com.netCloud.role.domain.AdminRole;
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

    @Override
    public List login(Admin admin, String verifyCode) {

        String imageCode = (String) session.getAttribute("imageCode");
        List<Admin> admins = adminMapper.login(admin);
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
    public PageBean page(PageBean page,HttpSession session) {
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
    public List<Admin> findAdminByPage(PageBean page, int moduleId, String roleName,HttpSession session) {
        List<Admin> list = new ArrayList();
        List<Admin> result = new ArrayList<>();
        Set<Integer> ars = new HashSet<>();
        //如果搜索框无信息
        if (moduleId == -1 && roleName.equals("")) {
            list = adminMapper.findAdminByPage(0);
        } else {
            //角色信息
            if(roleName != ""){
                List<Role> rs = adminMapper.findRoleIdByRoleName("%" + roleName + "%"); //模糊查询
                for (Role role : rs) {
                    //根据查出来的角色Id查出对应的管理者Id并用set去重
                    List<AdminRole> roleList = adminMapper.findAdminRoleByRoleId(role.getRoleId());
                    for (AdminRole adminRole : roleList) {
                        ars.add(adminRole.getAdminId());
                    }
                }
            }

            //根据moduleId查询出中间表对应的roleId
            List<RoleModule> rmList = adminMapper.findRoleIdByModuleId(moduleId);
            for (RoleModule roleModule : rmList) {
                List<AdminRole> adminRoles = adminMapper.findAdminRoleByRoleId(roleModule.getRoleId());
                for (AdminRole adminRole : adminRoles) {
                    ars.add(adminRole.getAdminId());
                }
            }

            for (int ar : ars) {
                //根据管理者Id查出对应的管理者
                list.add(adminMapper.findAdminByPage(ar).get(0));
            }
        }
        session.setAttribute("page",list.size());
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
     * @param session
     * @return
     */
    @Override
    public List<Admin> echoAdmin(HttpSession session) {
        int adminId = (int) session.getAttribute("adminId");
        return adminMapper.findAdminById(adminId);
    }

    /**
     * 添加管理员角色回显
     *
     * @param session
     * @return
     */
    @Override
    public List<AdminRole> echoAdminRole(HttpSession session) {
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
}
