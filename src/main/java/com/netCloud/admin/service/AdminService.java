package com.netCloud.admin.service;

import com.netCloud.admin.domain.Admin;
import com.netCloud.role.domain.AdminRole;
import com.netCloud.utils.page.PageBean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dllo on 17/12/7.
 */
public interface AdminService {

    List login(Admin admin, String verifyCode);

    PageBean page(PageBean page,HttpSession session);

    List<Admin> findAdminByPage(PageBean page, int moduleId, String roleName,HttpSession session);

    List addAdmin(Admin admin,String rePassword,Integer[] roleId);

    int deleteAdmin(int adminId);

    List<Admin> echoAdmin(HttpSession session);

    List<AdminRole> echoAdminRole(HttpSession session);

    List updateAdmin(Admin admin, Integer[] roleId);
}
