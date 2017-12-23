package com.netCloud.cost.service.impl;

import com.netCloud.cost.domain.Cost;
import com.netCloud.cost.mapper.CostMapper;
import com.netCloud.cost.service.CostService;
import com.netCloud.utils.page.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/12/21.
 */
@Service("costService")
public class CostServiceImpl implements CostService {
    @Resource
    HttpSession session;
    @Resource
    CostMapper costMapper;

    /**
     * 查询所有资费情况
     *
     * @param pageNum
     * @return
     */
    @Override
    public PageBean<Cost> findAllCost(int pageNum, String sort) {
        int sort0 = 0;
        if (sort.equals("feeDESC")) {
            sort0 = 4;
        } else if (sort.equals("feeASC")) {
            sort0 = 3;
        } else if (sort.equals("timeDESC")) {
            sort0 = 2;
        } else if (sort.equals("timeASC")) {
            sort0 = 1;
        }
        List<Cost> allCost = costMapper.findAllCost(sort0);
        List<Cost> data = new ArrayList<>();
        PageBean<Cost> pageBean = new PageBean<>(pageNum, 3, allCost.size());

        int start = (pageBean.getPageNum() - 1) * 3;
        int end = pageBean.getPageNum() * 3 ;

        if (end > pageBean.getTotalRecord()) {
            end = pageBean.getTotalRecord();
        }

        for (int i = start; i < end; i++) {
            data.add(allCost.get(i));
        }
        pageBean.setData(data);
        return pageBean;
    }

    /**
     * 添加资费
     * @param cost
     * @return
     */
    @Override
    public int addCost(Cost cost) {
        cost.setCostStatus(0);
        cost.setCreatime(new Date());
        return costMapper.addCost(cost);
    }

    /**
     * 更改资费状态
     * @param cost
     * @return
     */
    @Override
    public int updateStatus(Cost cost) {
        cost.setStartime(new Date());
        System.out.println(cost);
        return costMapper.updateStatus(cost);
    }

    /**
     * 通过id查询资费
     * @param costId
     * @return
     */
    @Override
    public Cost findCostById(int costId) {
        return costMapper.findCostById(costId);
    }

    /**
     * 删除资费信息
     * @param costId
     * @return
     */
    @Override
    public int deleteCost(int costId) {
        Cost cost = costMapper.findCostById(costId);
        if(cost.getCostStatus() == 0){
            return costMapper.deleteCostById(costId);
        }else {
            return 0;
        }
    }

    /**
     * 验证是否可以修改
     * @param costId
     * @return
     */
    @Override
    public String updateCostJump(int costId) {
        if (costMapper.findCostById(costId).getCostStatus() == 0){
            session.setAttribute("costId",costId);
            return "success";
        }else {
            return "false";
        }
    }

    /**
     * 修改资费信息
     * @param cost
     * @return
     */
    @Override
    public int updateCost(Cost cost) {
        return costMapper.updateCost(cost);
    }
}
