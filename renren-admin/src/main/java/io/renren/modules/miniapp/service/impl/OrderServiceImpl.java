package io.renren.modules.miniapp.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.miniapp.dao.OrderDao;
import io.renren.modules.miniapp.entity.Order;
import io.renren.modules.miniapp.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by timmy.deng on 2018/9/11.
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {
    @Override
    public List<Order> findList() {
        List<Order> ls = this.selectList(new EntityWrapper<Order>().in("state", new Object[]{"1", "4", "7", "6", "9"}));
        return ls;
    }

    @Override
    public List<Order> findList(Object[] objects) {
        List<Order> ls = this.selectList(new EntityWrapper<Order>().in("state", objects));
        return ls;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        String tran_Type = (String) params.get("tran_Type");
        String state = (String) params.get("state");
        String appointmentTime = (String) params.get("appointmentTime");
        String createTime = (String) params.get("createTime");
        String updateTime=(String) params.get("updateTime");
        String site=(String) params.get("site");
        if (key != null) {
            key = key.trim();
            if ("null".equals(key)){
                key="";
            }
        }
        if (tran_Type !=null){
            if ("null".equals(tran_Type)){
                tran_Type="";
            }
        }
        if (state!=null){
            if ("null".equals(state)){
                state="";
            }
        }
        if (appointmentTime!=null){
            if ("null".equals(appointmentTime)){
                appointmentTime="";
            }
        }
        if (createTime!=null){
            if ("null".equals(createTime)){
                createTime="";
            }
        }
        if (updateTime != null) {
            updateTime = updateTime.trim();
            if ("null".equals(updateTime)){
                updateTime="";
            }
        }
        if (site == null || site.equals("ALL") ){
            site=null;
        }
       // System.out.println(tran_Type+state+appointmentTime+createTime);
        //System.out.println("StringUtils.isNotBlank(key): "+StringUtils.isNotBlank(key));
        //System.out.println("StringUtils.isNotBlank(appointmentTime"+StringUtils.isNotBlank(appointmentTime));
        Page<Order> page = this.selectPage(
                new Query<Order>(params).getPage(),
                new EntityWrapper<Order>().
                        like(StringUtils.isNotBlank(key), "plate", key).
                        or().like(StringUtils.isNotBlank(key), "tran_Type", key).
                        or().like(StringUtils.isNotBlank(key), "create_Time", key).
                        or().like(StringUtils.isNotBlank(key), "user_name", key).
                        or().like(StringUtils.isNotBlank(key), "progressing", key).
                        or().like(StringUtils.isNotBlank(key), "id", key).
                      //  or().like(StringUtils.isNotBlank(key), "state", key).
                        andNew(StringUtils.isNotBlank(tran_Type)==true
                              ||StringUtils.isNotBlank(appointmentTime)==true
                              ||StringUtils.isNotBlank(state)==true
                              ||StringUtils.isNotBlank(createTime)==true,null,null

                      ).
                        like(StringUtils.isNotBlank(appointmentTime), "APPOINTMENT_TIME", appointmentTime).
                        like(StringUtils.isNotBlank(createTime), "CREATE_TIME", createTime).
                        like(StringUtils.isNotBlank(updateTime), "UPDATE_TIME", updateTime).
                        eq(StringUtils.isNotBlank(tran_Type), "tran_Type", tran_Type).
                        eq(StringUtils.isNotBlank(state), "state", state).
                        eq(StringUtils.isNotBlank(site), "SITE", site).
                        orderBy("id", false)
        );
        return new PageUtils(page);
    }

}
