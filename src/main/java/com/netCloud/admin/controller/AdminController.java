package com.netCloud.admin.controller;

import com.netCloud.admin.domain.Admin;
import com.netCloud.admin.service.impl.AdminServiceImpl;
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

    @RequestMapping(value = "/login")
    @ResponseBody
    public List login(Admin admin,String verifyCode){
        List result = adminService.login(admin, verifyCode);
        return result;
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
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
}
