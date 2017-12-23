package com.netCloud.cost.domain;

import java.util.Date;

public class Cost {
    private Integer costId;

    private String costName;

    private Double baseDuration;

    private Double baseCost;

    private Double unitCost;

    private Integer costStatus;

    private String descr;

    private Date creatime;

    private Date startime;

    private Integer costType;

    public Cost() {
    }

    public Cost(Integer costId, String costName, Double baseDuration, Double baseCost, Double unitCost, Integer costStatus, String descr, Date creatime, Date startime, Integer costType) {
        this.costId = costId;
        this.costName = costName;
        this.baseDuration = baseDuration;
        this.baseCost = baseCost;
        this.unitCost = unitCost;
        this.costStatus = costStatus;
        this.descr = descr;
        this.creatime = creatime;
        this.startime = startime;
        this.costType = costType;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public Double getBaseDuration() {
        return baseDuration;
    }

    public void setBaseDuration(Double baseDuration) {
        this.baseDuration = baseDuration;
    }

    public Double getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(Double baseCost) {
        this.baseCost = baseCost;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Integer getCostStatus() {
        return costStatus;
    }

    public void setCostStatus(Integer costStatus) {
        this.costStatus = costStatus;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }

    public Date getStartime() {
        return startime;
    }

    public void setStartime(Date startime) {
        this.startime = startime;
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "costId=" + costId +
                ", costName='" + costName + '\'' +
                ", baseDuration=" + baseDuration +
                ", baseCost=" + baseCost +
                ", unitCost=" + unitCost +
                ", costStatus=" + costStatus +
                ", descr='" + descr + '\'' +
                ", creatime=" + creatime +
                ", startime=" + startime +
                ", costType=" + costType +
                '}';
    }
}