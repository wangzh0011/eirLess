package io.renren.modules.miniapp.until;

/**
 * Created by timmy.deng on 2018/10/9.
 */
public class StateUntil {


    public  String Change(String code){
        switch (code){
            case "1": return "待审核";
            case "2": return "审核通过";
            case "3": return "审核不通过";
            case "4": return "待码头取消";
            case "5": return "已取消";
            case "6": return "待进闸确认";
            case "7": return "待审核";
            case "8": return "已过期";
            case "9": return "已完成";
            case "10": return "已完成";
            case "11": return "已完成";
            default:return "未知状态:"+code;
        }
    }

    public static void main(String[] args) {
        StateUntil until = new StateUntil();
        System.out.println(until.Change("2"));
    }
}
