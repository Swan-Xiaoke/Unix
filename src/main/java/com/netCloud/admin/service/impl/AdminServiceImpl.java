package com.netCloud.admin.service.impl;

import com.netCloud.admin.domain.Admin;
import com.netCloud.admin.mapper.AdminMapper;
import com.netCloud.admin.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
        }else if (!imageCode.equalsIgnoreCase(verifyCode)) {
            error.add("verifyCodeError");
        }
        if (error.size() == 0) {
            error.add("success");
        }
        return error;
    }
}
