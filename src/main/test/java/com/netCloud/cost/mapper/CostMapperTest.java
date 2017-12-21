package com.netCloud.cost.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by dllo on 17/12/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Unix-mybatis.xml")
public class CostMapperTest {

    @Resource
    CostMapper costMapper;

    @Test
    public void findAllCost() throws Exception {
        costMapper.findAllCost(1).forEach(System.out::println);

    }

}