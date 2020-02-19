package io.renren.modules.eir.until;

import io.renren.modules.miniapp.entity.Order;

import java.util.List;

/**
 * Created by timmy.deng on 2018/10/9.
 */
public class StateUntil {

    public  void Change(List<Order> ls){
        for (int i = 0; i <ls.size() ; i++) {
            ls.get(i).setState(Change(ls.get(i).getState()));
        }
    }

    public  String Change(String code){
        String state;
        switch (code){
            case "1": state = "待审核"; break;
            case "2": state = "审核通过";break;
            case "3": state = "审核不通过";break;
            case "4": state = "待码头取消";break;
            case "5": state = "已取消";break;
            case "6": state = "待进闸确认";break;
            case "7": state = "挂起";break;
            case "8": state = "已过期";break;
            case "9": state = "待复核";break;
            case "10": state = "已复核";break;
            case "11": state = "已完成";break;
            default:state= "未知状态:"+code;break;
        }
        return state;
    }

    public static void main(String[] args) {
        StateUntil until = new StateUntil();
        System.out.println(until.Change("2"));
    }
}
