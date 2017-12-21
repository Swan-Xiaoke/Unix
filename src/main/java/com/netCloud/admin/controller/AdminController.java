package com.netCloud.admin.controller;

import com.netCloud.admin.domain.Admin;
import com.netCloud.admin.service.impl.AdminServiceImpl;
import com.netCloud.role.domain.AdminRole;
import com.netCloud.role.domain.RoleModule;
import com.netCloud.utils.page.PageBean;
import com.netCloud.utils.verifyCode.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 管理员控制层
 * Created by dllo on 17/12/2.
 */
@Controller
public class AdminController {

    @Resource(name = "adminService")
    private AdminServiceImpl adminService;

    /**
     * 登录验证
     *
     * @param admin
     * @param verifyCode
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public List login(Admin admin, String verifyCode) {
        List result = adminService.login(admin, verifyCode);
        return result;
    }

    /**
     * 登录跳转
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    /**
     * 权限显示
     *
     * @return
     */
    @RequestMapping(value = "/module")
    @ResponseBody
    public List<Integer> module(HttpSession session) {
        List<Integer> modules = (List<Integer>) session.getAttribute("moduleId");
        return modules;
    }

    /**
     * 管理员跳转
     *
     * @return
     */
    @RequestMapping(value = "/admin_list")
    public String andmin_list() {
        return "admin/admin_list";
    }

    /**
     * 二维码生成
     *
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/verifyCode")
    public void verifyCode(HttpSession session, HttpServletResponse response) throws Exception {
        //用于处理真正的返回结果
        //创建工具类对象
        VerifyCode imageCode = new VerifyCode();

        //验证码工具生成图片对象
        BufferedImage image = imageCode.getImage();

        //保存生成的字符串对象
        session.setAttribute("imageCode", imageCode.getText());

        //获得response对象的输出流用于图像的写入
        OutputStream os = response.getOutputStream();

        //将图片对象映射到输出流中
        VerifyCode.output(image, os);
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/adminPage")
    @ResponseBody
    public PageBean pageCount(PageBean page, HttpSession session) {
        return adminService.page(page, session);
    }

    /**
     * 分页显示信息
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/findAllAdmin")
    @ResponseBody
    public List<Admin> findAdminByPage(PageBean page, int moduleId, String roleName) {
        List<Admin> admins = adminService.findAdminByPage(page, moduleId, roleName);
        return admins;
    }

    /**
     * 添加按钮跳转
     *
     * @return
     */
    @RequestMapping(value = "/admin_add")
    public String add_jump() {
        return "admin/admin_add";
    }

    /**
     * 添加角色
     *
     * @param admin
     * @param rePassword
     * @return
     */
    @RequestMapping(value = "/addAdmin")
    @ResponseBody
    public List addAdmin(Admin admin, String rePassword, Integer[] roleId) {
        List result = adminService.addAdmin(admin, rePassword, roleId);
        return result;
    }

    /**
     * 删除管理员
     *
     * @param adminId
     * @return
     */
    @RequestMapping(value = "/deleteAdmin")
    @ResponseBody
    public int deleteAdmin(int adminId) {
        return adminService.deleteAdmin(adminId);
    }

    /**
     * 添加管理员传参
     *
     * @param session
     * @param adminId
     * @return
     */
    @RequestMapping(value = "/updateAdminEcho")
    @ResponseBody
    public String updateAdminEcho(HttpSession session, int adminId) {
        session.setAttribute("adminId", adminId);
        return "success";
    }

    /**
     * 添加管理员跳转
     *
     * @return
     */
    @RequestMapping(value = "/updateAdminJump")
    public String updateAdminJump() {
        return "admin/admin_modi";
    }

    /**
     * 添加管理员页面回显
     *
     * @return
     */
    @RequestMapping(value = "/echoAdmin")
    @ResponseBody
    public List<Admin> echoAdmin() {
        return adminService.echoAdmin();
    }

    @RequestMapping(value = "/echoAdminRole")
    @ResponseBody
    public List<AdminRole> echoAdminRole() {
        return adminService.echoAdminRole();
    }

    /**
     * 修改员工信息
     *
     * @param admin
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/updateAdmin")
    @ResponseBody
    public List updateAdmin(Admin admin, Integer[] roleId) {
        return adminService.updateAdmin(admin, roleId);
    }

    /**
     * 权限图标显示
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/findAdminMo")
    @ResponseBody
    public Set<Integer> findAdminMo(HttpSession session) {
        Set<Integer> moduleIds = new HashSet<>();
        Integer adminId = (Integer) session.getAttribute("adminIds");
        for (RoleModule roleModule : adminService.findAdminMo(adminId)) {
            moduleIds.add(roleModule.getModuleId());
        }
        return moduleIds;
    }

    /**
     * 个人信息跳转
     *
     * @return
     */
    @RequestMapping(value = "/user_info")
    public String user() {
        return "user/user_info";
    }

    /**
     * 个人信息回显
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/echoInfo")
    @ResponseBody
    public Admin echoInfo(HttpSession session) {
        Admin user = (Admin) session.getAttribute("user");
        return user;
    }

    /**
     * 个人信息角色向回显
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/echoUserRole")
    @ResponseBody
    public List echoUserRole(HttpSession session) {
        Admin user = (Admin) session.getAttribute("user");
        return adminService.findRoleByUserId(user.getAdminId());
    }

    /**
     * 修改个人信息
     *
     * @return
     */
    @RequestMapping(value = "/changeAdminInfo")
    @ResponseBody
    public String changeAdminInfo(Admin admin) {
        System.out.println(admin);
        adminService.changeAdminInfo(admin);
        return "success";
    }

    /**
     * 修改密码页面跳转
     *
     * @return
     */
    @RequestMapping(value = "/user_modi_pwc")
    public String changePwd() {
        return "user/user_modi_pwd";
    }

    /**
     * 报表页面跳转
     *
     * @return
     */
    @RequestMapping(value = "report_list")
    public String report() {
        return "report/report_list";
    }


    /**
     * 修改个人密码
     *
     * @return
     */
    @RequestMapping(value = "/changeAdminPwd")
    @ResponseBody
    public List changeAdminPwd(String oldPwd,String newPwd,String reNewPwd) {
        System.out.println(oldPwd + newPwd + reNewPwd);
        List result = adminService.changeAdminPwd(oldPwd,newPwd,reNewPwd);
        System.out.println(result);
        return result;
    }


}
