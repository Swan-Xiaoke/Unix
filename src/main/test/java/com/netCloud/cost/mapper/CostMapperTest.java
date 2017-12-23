package com.netCloud.cost.mapper;

import com.netCloud.cost.domain.Cost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by dllo on 17/12/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Unix-mybatis.xml")
public class CostMapperTest {
    @Resource
    CostMapper costMapper;


    /**
     * 根据Id查资费信息测试数据
     * @throws Exception
     */
    @Test
    public void findCostById() throws Exception {
        System.out.println(costMapper.findCostById(1));
    }

    /**
     * 更改资费启用状态测试
     * @throws Exception
     */
    @Test
    public void updateStatus() throws Exception {
        Cost cost = new Cost();
        cost.setCostId(2);
        cost.setStartime(new Date());
        int i = costMapper.updateStatus(cost);
        System.out.println(i);
    }



    @Test
    public void findAllCost() throws Exception {
        costMapper.findAllCost(1).forEach(System.out::println);

    }

}