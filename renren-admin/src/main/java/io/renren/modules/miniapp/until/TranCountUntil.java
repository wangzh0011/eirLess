package io.renren.modules.miniapp.until;

import io.renren.modules.miniapp.entity.Order;
import io.renren.modules.miniapp.entity.convert.EntityConvert;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by timmy.deng on 2018/11/5.
 */
@Component
public class TranCountUntil {



    public void setCount(List<Order> ls){

        Map<String,Object> map = new HashMap();
        for (Order l : ls) {
            if (map.get(l.getPlate()+l.getAppointmentTime())==null){
                List<Order> list = new ArrayList<>();
                list.add(l);
                map.put(l.getPlate()+l.getAppointmentTime(),list);
            }else {
                List<Order> list = (List<Order>) map.get(l.getPlate()+l.getAppointmentTime());
                list.add(l);
                map.put(l.getPlate()+l.getAppointmentTime(),list);
            }
        }

        for (Order l : ls) {

            if(l.getState().equals("5")||l.getState().equals("8")){

              l.setTranCount("未计数");

            }else {

                l.setTranCount(EntityConvert.convertToOrder((List<Order>) map.get(l.getPlate()+l.getAppointmentTime())).getActivityQuantity());

            }


        }



    }




}
