package com.netCloud.admin.mapper;

import com.netCloud.admin.domain.Admin;
import com.netCloud.role.domain.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dllo on 17/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Unix-mybatis.xml")
public class AdminMapperTest {
    @Test
    public void findRoleNameByRoleId() throws Exception {
        List<Role> roleNameByRoleId = mapper.findRoleNameByRoleId(300);
        System.out.println(roleNameByRoleId);
    }


    @Resource
    AdminMapper mapper;

    /**
     * 分页查询测试
     * @throws Exception
     */
    @Test
    public void findAdminByPage() throws Exception {
        mapper.findAdminByPage(3000).forEach(System.out::println);
    }

    /**
     * 根据id查询管理员数据
     * @throws Exception
     */
    @Test
    public void findAdminById() throws Exception {
        List<Admin> adminById = mapper.findAdminById(7000);
        System.out.println(adminById);
    }

    /**
     * 根据Id查询对应管理员角色信息
     * @throws Exception
     */
    @Test
    public void findRoleByAdminId() throws Exception {

        mapper.findRoleByAdminId(7000).forEach(System.out::println);
    }

    /**
     * 模糊查询角色
     * @throws Exception
     */
    @Test
    public void findRoleIdByRoleName() throws Exception {
        mapper.findRoleIdByRoleName("%%").forEach(System.out::println);
    }

}