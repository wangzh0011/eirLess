package io.renren.modules.miniapp.until;

import java.util.HashMap;

/**
 * Created by timmy.deng on 2018/10/12.
 */
public class tranTypeUtils {

    public String change(String tranType){




         switch (tranType){
             case "RE":return "交空";
             case "RF":return "交重";
             case "DE":return "提空";
             case "DF":return "提重";
             default:return "未知作业类型"+tranType;
         }
    }

    public static void main(String[] args) {
        TranCountUntil tranCountUntil = new TranCountUntil();


        HashMap map = new HashMap();

    }
}
