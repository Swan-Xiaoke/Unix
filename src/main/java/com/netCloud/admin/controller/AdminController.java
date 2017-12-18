package com.netCloud.admin.controller;

import com.netCloud.admin.domain.Admin;
import com.netCloud.admin.service.impl.AdminServiceImpl;
import com.netCloud.role.domain.AdminRole;
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
import java.util.List;

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
     * @param admin
     * @param verifyCode
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public List login(Admin admin,String verifyCode){
        List result = adminService.login(admin, verifyCode);
        return result;
    }

    /**
     * 登录跳转
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    /**
     * 管理员跳转
     * @return
     */
    @RequestMapping(value = "/admin_list")
    public String andmin_list(){
        return "admin/admin_list";
    }

    /**
     * 二维码生成
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
        session.setAttribute("imageCode",imageCode.getText());

        //获得response对象的输出流用于图像的写入
        OutputStream os = response.getOutputStream();

        //将图片对象映射到输出流中
        VerifyCode.output(image,os);
    }


    /**
     * 分页
     * @param page
     * @return
     */
    @RequestMapping(value = "/adminPage")
    @ResponseBody
    public PageBean pageCount(PageBean page,HttpSession session) {
        return adminService.page(page,session);
    }

    /**
     * 分页显示信息
     * @param page
     * @return
     */
    @RequestMapping(value = "/findAllAdmin")
    @ResponseBody
    public List<Admin> findAdminByPage(PageBean page,int moduleId,String roleName,HttpSession session){
        List<Admin> admins = adminService.findAdminByPage(page,moduleId,roleName,session);
        return admins;
    }

    /**
     * 添加按钮跳转
     * @return
     */
    @RequestMapping(value = "/admin_add")
    public String add_jump(){
       return "admin/admin_add";
    }

    /**
     * 添加角色
     * @param admin
     * @param rePassword
     * @return
     */
    @RequestMapping(value = "/addAdmin")
    @ResponseBody
    public List addAdmin(Admin admin,String rePassword,Integer[] roleId){
        List result = adminService.addAdmin(admin,rePassword,roleId);
        return result;
    }

    /**
     * 删除管理员
     * @param adminId
     * @return
     */
    @RequestMapping(value = "/deleteAdmin")
    @ResponseBody
    public int deleteAdmin(int adminId){
       return adminService.deleteAdmin(adminId);
    }

    /**
     * 添加管理员传参
     * @param session
     * @param adminId
     * @return
     */
    @RequestMapping(value = "/updateAdminEcho")
    @ResponseBody
    public String updateAdminEcho(HttpSession session,int adminId){
        session.setAttribute("adminId",adminId);
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
     * @param session
     * @return
     */
    @RequestMapping(value = "/echoAdmin")
    @ResponseBody
    public List<Admin> echoAdmin(HttpSession session){
        return adminService.echoAdmin(session);
    }
    @RequestMapping(value = "/echoAdminRole")
    @ResponseBody
    public List<AdminRole> echoAdminRole(HttpSession session){
        return adminService.echoAdminRole(session);
    }

    /**
     * 修改员工信息
     * @param admin
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/updateAdmin")
    @ResponseBody
    public List updateAdmin(Admin admin,Integer[] roleId){
        return adminService.updateAdmin(admin,roleId);
    }

}
