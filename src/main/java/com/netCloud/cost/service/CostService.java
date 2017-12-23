package com.netCloud.cost.service;

import com.netCloud.cost.domain.Cost;
import com.netCloud.utils.page.PageBean;

/**
 * Created by dllo on 17/12/21.
 */
public interface CostService {
    /**
     * 查询所有的资费情况
     * @param pageNum
     * @return
     */
    PageBean<Cost> findAllCost(int pageNum,String sort);

    /**
     * 添加资费
     * @param cost
     * @return
     */
    int addCost(Cost cost);

    /**
     * 更改状态
     * @param cost
     * @return
     */
    int updateStatus(Cost cost);

    /**
     * 根据Id查资费
     * @param costId
     * @return
     */
    Cost findCostById(int costId);

    /**
     * 删除资费信息
     * @param costId
     * @return
     */
    int deleteCost(int costId);

    /**
     * 验证是否可以修改
     * @param costId
     * @return
     */
    String updateCostJump(int costId);

    /**
     * 修改资费信息
     * @param cost
     * @return
     */
    int updateCost(Cost cost);
}
