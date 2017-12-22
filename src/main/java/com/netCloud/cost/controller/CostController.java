package com.netCloud.cost.controller;

import com.netCloud.cost.domain.Cost;
import com.netCloud.cost.service.CostService;
import com.netCloud.utils.page.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by dllo on 17/12/21.
 */
@Controller
public class CostController{
    @Resource
    HttpSession session;

    @Resource(name = "costService")
    CostService costService;

    /**
     * 资费管理页面跳转
     * @return
     */
    @RequestMapping(value = "fee_list")
    public String costJump(){
        return "fee/fee_list";
    }

    /**
     *
     * @param pageNum
     * @param sort  排序依据
     * @return
     */
    @RequestMapping(value = "findAllCost")
    @ResponseBody
    public PageBean<Cost> findAllCost(int pageNum,String sort){
        PageBean<Cost> pageBean = costService.findAllCost(pageNum,sort);
        return pageBean;
    }

    /**
     * 添加页面跳转
     * @return
     */
    @RequestMapping(value = "fee_add")
    public String addCostJump(){
        return "fee/fee_add";
    }

    /**
     * 添加资费信息
     * @param cost
     * @return
     */
    @RequestMapping(value = "/addCost")
    public int addCost(Cost cost){
        return costService.addCost(cost);
    }

    /**
     * 资费详情跳转
     * @return
     */
    @RequestMapping(value = "/fee_detail")
    @ResponseBody
    public String feeDetailJump(int costId){
        session.setAttribute("costId",costId);
        return "feeDetail";
    }
    @RequestMapping(value = "/feeDetail")
    public String detail(){
        return "fee/fee_detail";
    }

    @RequestMapping(value = "/starUsing")
    @ResponseBody
    public int openFee(Cost cost){
        return costService.updateStatus(cost);
    }



}
