package com.netCloud.role.mapper;

import com.netCloud.role.domain.Role;
import com.netCloud.role.domain.RoleModule;
import com.netCloud.utils.page.PageBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/12/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Unix-mybatis.xml")
public class RoleModuleMapperTest {

    @Resource
    private RoleModuleMapper mapper;

    /**
     * 根据
     * @throws Exception
     */
    @Test
    public void findRoleById() throws Exception {
        System.out.println(mapper.findRoleById(200));
    }

    /**
     * 根据页数查询数据测试
     *
     * @param
     * @throws Exception
     */
    @Test
    public void findAll() throws Exception {
        mapper.findAll(1,5).forEach(System.out::println);
    }

    /**
     * 添加测试方法
     *
     * @throws Exception
     */
    @Test
    public void addRole() throws Exception {
        for (int i = 1; i < 50; i++) {
            Role role = new Role();
            role.setRoleName("小队长"+i);
            mapper.addRole(role);
            mapper.addMpRo(role.getRoleId(), i/7);
        }
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void findModuleById() throws Exception {
        System.out.println(mapper.findModuleById(200));
    }

    @Test
    public void findAllModule() throws Exception {
        System.out.println(mapper.findAllModule());
    }


}