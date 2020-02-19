package io.renren.modules.miniapp.entity.convert;


import io.renren.modules.miniapp.until.StateUntil;
import io.renren.modules.miniapp.entity.Order;
import io.renren.modules.miniapp.entity.model.OrderModel;
import io.renren.modules.miniapp.entity.vo.OrderVo;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by timmy.deng on 2018/8/30.
 */
public class EntityConvert {
    public static StateUntil stateUntil = new StateUntil();

    public static OrderModel convertToOrder(List<Order> list) {

        OrderModel model = new OrderModel();

        String time=null;//预约到港时间
        String activityQuantity=null; //作业数量，几交几提

        int r = 0, d = 0;// r 交 d 提
        List<OrderVo> voList = new ArrayList<>();
        for (Order it : list) {
            OrderVo vo = new OrderVo();
            vo.setOrder(it);
            String state = vo.getOrder().getState();
            vo.setWxState(stateUntil.Change(state));
            if ("1".equals(state)||"7".equals(state)) {
                System.out.println();
                vo.setWxCss("red");
            }else if("5".equals(state)){
                vo.setWxCss("gray");
            } else {
                vo.setWxCss("green");
            }
            voList.add(vo);


            if ("RE".equals(vo.getOrder().getTranType()) ) {
                if (!"3".equals(vo.getOrder().getState())){
                    r += 1;
                }

                vo.setTranType("交空");
            }
            if ( "RF".equals(vo.getOrder().getTranType())) {
                if (!"3".equals(vo.getOrder().getState())){
                r += 1;}
                vo.setTranType("交重");
            }
            if ("DE".equals(vo.getOrder().getTranType()) ) {
                if (!"3".equals(vo.getOrder().getState())){
                d += 1;}
                vo.setTranType("提空");
            }
            if ("DF".equals(vo.getOrder().getTranType())) {
                if (!"3".equals(vo.getOrder().getState())){
                d += 1;}
                vo.setTranType("提重");
            }


            if (null==time){
            time=vo.getOrder().getAppointmentTime();
            }else if (!time.equals(vo.getOrder().getAppointmentTime())){
                time=time+vo.getOrder().getAppointmentTime();
            }

        }

        if((r+d)>=4){
            model.setAdd(false);
        }else {
            model.setAdd(true);
        }

        activityQuantity=r+"交"+d+"提";
        model.setList(voList);
        model.setTime(time);
        model.setActivityQuantity(activityQuantity);
        return model;
    }

    public static OrderVo convertToOrderVo(Order order) {
            OrderVo vo = new OrderVo();
            vo.setOrder(order);
            String state = vo.getOrder().getState();
            vo.setWxState(stateUntil.Change(state));
            if ("7".equals(state)||"1".equals(state)) {
                vo.setWxCss("red");
            }else if("5".equals(state)){
                vo.setWxCss("gray");
            } else {
                vo.setWxCss("green");
            }
            if ("RE".equals(vo.getOrder().getTranType()) ) {

                vo.setTranType("交空");
            }
            if ( "RF".equals(vo.getOrder().getTranType())) {

                vo.setTranType("交重");
            }
            if ("DE".equals(vo.getOrder().getTranType()) ) {
                vo.setTranType("提空");
            }
            if ("DF".equals(vo.getOrder().getTranType())) {
                vo.setTranType("提重");
            }
        return vo;
    }

    public static List<OrderVo> convertToOrderVo(List<Order> ll) {
        List<OrderVo> ls = new ArrayList<>();

        for (Order order: ll) {
            OrderVo vo = new OrderVo();
            vo.setOrder(order);
            String state = vo.getOrder().getState();
            vo.setWxState(stateUntil.Change(state));
            if ("1".equals(state) ||"7".equals(state)) {

                vo.setWxCss("red");

            }else if("5".equals(state)){
                vo.setWxCss("gray");
            } else {
                vo.setWxCss("green");
            }
            if ("RE".equals(vo.getOrder().getTranType()) ) {
                vo.setTranType("交空");
            }
            if ( "RF".equals(vo.getOrder().getTranType())) {
                vo.setTranType("交重");
            }
            if ("DE".equals(vo.getOrder().getTranType()) ) {
                vo.setTranType("提空");
            }
            if ("DF".equals(vo.getOrder().getTranType())) {
                vo.setTranType("提重");
            }
            ls.add(vo);
        }

        return ls;
    }



}
