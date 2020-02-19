package io.renren.modules.miniapp.entity.vo;

import io.renren.modules.miniapp.entity.Order;

/**
 * Created by timmy.deng on 2018/8/30.
 */
public class OrderVo {
    private String workState;
    private String wxState; //小程序上显示的
    private Order order;
    private String wxCss;
    private String tranType;

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState;
    }

    public String getWxState() {
        return wxState;
    }

    public void setWxState(String wxState) {
        this.wxState = wxState;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getWxCss() {
        return wxCss;
    }

    public void setWxCss(String wxCss) {
        this.wxCss = wxCss;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }
}
