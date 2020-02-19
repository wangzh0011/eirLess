package io.renren.modules.job.task;

/**
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.modules.eir.until.StateUntil;
import io.renren.modules.job.entity.ScheduleJobLogEntity;
import io.renren.modules.job.service.ScheduleJobLogService;
import io.renren.modules.miniapp.entity.HistoryOrder;
import io.renren.modules.miniapp.entity.Order;
import io.renren.modules.miniapp.service.HistoryOrderService;
import io.renren.modules.miniapp.service.OrderService;
import io.renren.modules.miniapp.until.TranCountUntil;
import io.renren.modules.miniapp.until.tranTypeUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysConfigService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试定时任务(演示Demo，可删除)
 *
 * testTask为spring bean的名称
 *

 */
@Component("testTask")
public class TestTask {
    private Logger logger = LoggerFactory.getLogger(getClass());
	
	/*@Autowired
	private SysUserService sysUserService;*/

    @Autowired
    WebApplicationContext webApplicationConnect;
    @Autowired
    private OrderService orderService;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    @Autowired
    private HistoryOrderService historyOrderService;

    private StateUntil stateUntil = new StateUntil();

    private tranTypeUtils typeUtils = new tranTypeUtils();

    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    @Value("${img.location}")
    private String uploadPath;


    @Autowired
    TranCountUntil countUntil;

    public void test(String params) {


        logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		/*SysUserEntity user = sysUserService.selectById(1L);
		System.out.println(ToStringBuilder.reflectionToString(user));*/

    }


    /**
     * 定时获取数据，并存入内存中
     */
    public void eirLess() {
        List<Order> ls = orderService.selectList(new EntityWrapper<Order>().in("state", new Object[]{"1", "4", "7", "6", "9"}).orderBy("id", true));

        /*String url = sysConfigService.getValue("uploadUrl");*/
        List<Order> list = new ArrayList<>();
        List<Order> handUp = new ArrayList<>();
        List<Order> handUp_D = new ArrayList<>();
        List<Order> handUp_I = new ArrayList<>();
        List<Order> reconfirm = new ArrayList<>();
        List<Order> reconfirm_D = new ArrayList<>();
        List<Order> reconfirm_I = new ArrayList<>();
        List<Order> list_D = new ArrayList<>();
        List<Order> list_I = new ArrayList<>();
        for (Order it : ls) {
				/*if(null != it.getSealImg()){
					it.setSealImg(url+it.getSealImg());
				}
				if (null!=it.getEirImg()){
					it.setEirImg(url+it.getEirImg());}
				if (null != it.getAttachImg()){
					it.setAttachImg(url+it.getAttachImg());
				}*/

            //飞单页面数据
            if (it.getState().equals("1") || it.getState().equals("4") || it.getState().equals("6")) {
                list.add(it);
                if (it.getSite().equals("D")) {
                    list_D.add(it);
                }
                if (it.getSite().equals("I")) {
                    list_I.add(it);
                }
            }
            //挂起
            if (it.getState().equals("7")) {

                handUp.add(it);
                if (it.getSite().equals("D")) {
                    handUp_D.add(it);
                }
                if (it.getSite().equals("I")) {
                    handUp_I.add(it);
                }

            }
            //待复核
            if (it.getState().equals("9")) {
                reconfirm.add(it);
                if (it.getSite().equals("D")){
                    reconfirm_D.add(it);
                }
                if (it.getSite().equals("I")){
                    reconfirm_I.add(it);
                }
            }
            it.setState(stateUntil.Change(it.getState()));
            it.setTranType(typeUtils.change(it.getTranType()));

        }
        webApplicationConnect.getServletContext().setAttribute("eirList_D", list_D);

        webApplicationConnect.getServletContext().setAttribute("eirList_I", list_I);
        webApplicationConnect.getServletContext().setAttribute("eirList", list);
        webApplicationConnect.getServletContext().setAttribute("handUp", handUp);
        webApplicationConnect.getServletContext().setAttribute("handUp_D", handUp_D);
        webApplicationConnect.getServletContext().setAttribute("handUp_I", handUp_I);
        webApplicationConnect.getServletContext().setAttribute("reconfirm", reconfirm);
        webApplicationConnect.getServletContext().setAttribute("reconfirm_D", reconfirm_D);
        webApplicationConnect.getServletContext().setAttribute("reconfirm_I", reconfirm_I);
    }

    public void test2() {

        logger.info("我是不带参数的test2方法，正在被执行");
        List<Order> ls = orderService.selectList(new EntityWrapper<Order>().
                in("state", new Object[]{"2", "3"}).and("TO_DATE(EXPIRE_TIME,'yyyy-mm-dd hh24:mi') < SYSDATE")

        );
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getState().equals("3")) {
                ls.get(i).setState("5");//设置已过期
                ls.get(i).setUpdateTime(format.format(new Date()));//设置已过期
                ls.get(i).setUpdateRemark("两天内未处理，设置成已过期.");
            } else {
                ls.get(i).setState("8");//设置已过期
                ls.get(i).setUpdateTime(format.format(new Date()));//设置已过期
                ls.get(i).setUpdateRemark("两天内未处理，设置成已过期.");
            }
        }
        System.out.println(ls);
        System.out.println(ls.size());

        if (null != ls && ls.size() != 00) {
            System.out.println("更新数据");
            orderService.insertOrUpdateBatch(ls);

            for (Order order : ls) {
                saveHistoryOrder(order);
            }
        }


    }


    public void delete() {

        List<Order> ls = orderService.selectList(new EntityWrapper<Order>().
                in("state", new Object[]{"5",}).isNull("DELETE_IMG").and("TO_DATE(SUBSTR(CREATE_TIME,1,16),'yyyy-mm-dd hh24:mi') < SYSDATE-7")

        );
        for (Order l : ls) {

            if (l.getSealImg() != null) {
                try {
                    File file = new File(uploadPath + l.getSealImg());
                    if (file.exists() && file.isFile()) {

                        file.delete();
                        System.out.println("删除成功file.getName() = " + file.getName());
                    }
                    l.setDeleteImg("Y");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (l.getEirImg() != null) {
                try {
                    File file = new File(uploadPath + l.getEirImg());
                    if (file.exists() && file.isFile()) {
                        file.delete();
                        System.out.println("删除成功file.getName() = " + file.getName());
                    }
                    l.setDeleteImg("Y");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (l.getAttachImg() != null) {
                try {
                    File file = new File(uploadPath + l.getAttachImg());
                    if (file.exists() && file.isFile()) {
                        file.delete();
                        System.out.println("删除成功file.getName() = " + file.getName());
                    }
                    l.setDeleteImg("Y");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        orderService.insertOrUpdateBatch(ls);


    }

    public void deleteScheduleJobLog() {

        scheduleJobLogService.delete(new EntityWrapper<ScheduleJobLogEntity>().where("CREATE_TIME > SYSDATE-1 ", null));

    }


    public void saveHistoryOrder(Order order) {
        HistoryOrder hi = new HistoryOrder();
        try {
            System.out.println();
            BeanUtils.copyProperties(hi, order);
            hi.setUpdateTime(format.format(new Date()));
            hi.setOrderId(order.getId());
            String userName = null;
            try {
                userName = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
            } catch (Exception e) {

            }
            if (null == userName) {
                hi.setOperator("司机");
            } else {
                hi.setOperator(userName);
            }

            boolean s = historyOrderService.insert(hi);
            System.out.println("保存成功" + s);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        System.out.println("2018-11-26 16:00".length());
    }
}
