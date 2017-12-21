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
}
