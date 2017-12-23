package com.netCloud.cost.mapper;

import com.netCloud.cost.domain.Cost;

import java.util.Date;
import java.util.List;

public interface CostMapper {

    /**
     * 查询所有的资费信息
     *
     * @return
     */
    List<Cost> findAllCost(int sort);

    /**
     * 添加资费
     *
     * @param cost
     * @return
     */
    int addCost(Cost cost);

    /**
     * 更改资费状态
     *
     * @param cost
     * @return
     */
    int updateStatus(Cost cost);

    /**
     * 根据Id查询资费信息
     * @param costId
     * @return
     */
    Cost findCostById(int costId);

    /**
     * 删除资费
     * @param costId
     * @return
     */
    int deleteCostById(int costId);

    /**
     * 更改资费信息
     * @param cost
     * @return
     */
    int updateCost(Cost cost);
}