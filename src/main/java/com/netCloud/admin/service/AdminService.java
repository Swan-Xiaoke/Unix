package com.netCloud.admin.service;

import com.netCloud.admin.domain.Admin;
import com.netCloud.role.domain.AdminRole;
import com.netCloud.role.domain.RoleModule;
import com.netCloud.utils.page.PageBean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * Created by dllo on 17/12/7.
 */
public interface AdminService {

    List login(Admin admin, String verifyCode);

    PageBean page(PageBean page,HttpSession session);

    List<Admin> findAdminByPage(PageBean page, int moduleId, String roleName);

    List addAdmin(Admin admin,String rePassword,Integer[] roleId);

    int deleteAdmin(int adminId);

    List<Admin> echoAdmin();

    List<AdminRole> echoAdminRole();

    List updateAdmin(Admin admin, Integer[] roleId);

    Set<RoleModule> findAdminMo(Integer adminId);

    List findRoleByUserId(int adminId);

    void changeAdminInfo(Admin admin);

    List changeAdminPwd(String oldPwd, String newPwd, String reNewPwd);

}
