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
public class CostController {
    @Resource
    HttpSession session;

    @Resource(name = "costService")
    CostService costService;

    /**
     * 资费管理页面跳转
     *
     * @return
     */
    @RequestMapping(value = "fee_list")
    public String costJump() {
        return "fee/fee_list";
    }

    /**
     * @param pageNum
     * @param sort    排序依据
     * @return
     */
    @RequestMapping(value = "findAllCost")
    @ResponseBody
    public PageBean<Cost> findAllCost(int pageNum, String sort) {
        PageBean<Cost> pageBean = costService.findAllCost(pageNum, sort);
        return pageBean;
    }

    /**
     * 添加页面跳转
     *
     * @return
     */
    @RequestMapping(value = "fee_add")
    public String addCostJump() {
        return "fee/fee_add";
    }

    /**
     * 添加资费信息
     *
     * @param cost
     * @return
     */
    @RequestMapping(value = "/addCost")
    @ResponseBody
    public int addCost(Cost cost) {
        return costService.addCost(cost);
    }

    /**
     * 资费详情跳转
     *
     * @return
     */
    @RequestMapping(value = "/fee_detail")
    @ResponseBody
    public String feeDetailJump(int costId) {
        session.setAttribute("costId", costId);
        return "feeDetail";
    }
    @RequestMapping(value = "/feeDetail")
    public String detail() {
        return "fee/fee_detail";
    }

    /**
     * 资费信息回显
     *
     * @return
     */
    @RequestMapping(value = "/echoCostFee")
    @ResponseBody
    public Cost echoFeeDetail() {
        int costId = (int) session.getAttribute("costId");
        System.out.println(costId);
        return costService.findCostById(costId);
    }

    /**
     * 资费启用状态
     *
     * @param cost
     * @return
     */
    @RequestMapping(value = "/starUsing")
    @ResponseBody
    public int openFee(Cost cost) {
        return costService.updateStatus(cost);
    }

    /**
     * 删除资费信息
     *
     * @param costId
     * @return
     */
    @RequestMapping(value = "/deleteCost")
    @ResponseBody
    public int deletCost(int costId) {
        return costService.deleteCost(costId);
    }

    /**
     * 修改资费页面跳转
     * @param costId
     * @return
     */
    @RequestMapping(value = "/updateCostJump")
    @ResponseBody
    public String updateJump(int costId){
        return costService.updateCostJump(costId);
    }
    @RequestMapping(value = "/updateJump")
    public String update(){
        return "fee/fee_modi";
    }

    /**
     * 更改资费信息
     * @param cost
     * @return
     */
    @RequestMapping(value = "/updateCost")
    @ResponseBody
    public int updateCost(Cost cost){
        System.out.println(cost);
        return costService.updateCost(cost);
    }


}
