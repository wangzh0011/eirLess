package io.renren.modules.miniapp.ws.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.modules.miniapp.entity.Result;
import io.renren.modules.miniapp.entity.WXUser;
import io.renren.modules.miniapp.service.WXUserService;
import io.renren.modules.miniapp.until.MessageTemplateUtils;
import io.renren.modules.miniapp.until.ResultUtils;
import io.renren.modules.miniapp.ws.service.SendMessageTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by timmy.deng on 2018/6/15.
 */
@Component
@WebService(endpointInterface = "io.renren.modules.miniapp.ws.service.SendMessageTemplateService",
        targetNamespace = "http://serviceImpl.ws.dcb.com/"
)
public class SendMessageTemplateServiceImpl implements SendMessageTemplateService {
    //@Autowired
    // private FormIdService formIdService;
    @Autowired
    private WXUserService wxUserService;
    @Autowired
    private MessageTemplateUtils utils;
    private ResultUtils resultUtils = new ResultUtils();

    protected Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String SendMessage(String mapString) {

        System.out.println("webservice被调用了!得到的参数为:"+mapString);
        Map map;
        try {
            map=  JSON.parseObject(mapString,Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("传入的String不能解析为map");
            return JSON.toJSONString(resultUtils.Error(-1,"传入的String不能解析为map"));
        }
        if(null==map.get("data")){
            return JSON.toJSONString(resultUtils.Error(-1,"map 的data不能为空"));
        }
        if(null==map.get("template_id")){
            return JSON.toJSONString(resultUtils.Error(-1,"map 的template_id不能为空"));
        }

        if(null==map.get("plate") && null==map.get("touser") && null == map.get("phone")){
            return JSON.toJSONString(resultUtils.Error(-1,"map 的plate或openid不能为空"));
        }else {

            Result result=null;
            if(null != map.get("touser")){
                String  msg=utils.sendMessageTemplate(map);
                if (msg.contains("\"errcode\":0")){
                    result=resultUtils.Success(msg);
                }else {
                    result=resultUtils.Error(msg);
                }
                if(null==result){
                    return JSON.toJSONString(resultUtils.Error(-1,"该号码无fromId"));
                }
                return JSON.toJSONString(result);
            }


            String plate=(String) map.get("plate");
            String phone=(String) map.get("phone");
            List<WXUser> users = new ArrayList<>();
            //一个车牌号码被多个用户绑定的情况
            if(plate != null){
                map.remove(plate);
                users =  wxUserService.selectList(new EntityWrapper<WXUser>().eq("plate",plate));
            }else if(phone != null){
                map.remove(phone);
                String[] phones = phone.split(",");
                users =  wxUserService.selectList(new EntityWrapper<WXUser>().in("phone",phones));
            }

            List<String> openid =new ArrayList<>();

            for (WXUser it:users) {
                if (!"undefined".equals(it.getOpenid())){
                    openid.add(it.getOpenid());
                }
            }

            if(openid.size()==0){
                 return JSON.toJSONString(resultUtils.Error(-1,"该号码未绑定小程序"));
            }

            if(plate != null){
                for (String openId :openid) {
                    map.put("touser",openId);
                    String  msg=utils.sendMessageTemplate(map);
                    if (msg.contains("\"errcode\":0")){
                        result=resultUtils.Success(msg);
                    }else {
                        result=resultUtils.Error(msg);
                    }
                }
            }

            //原有逻辑不变，重写驳船推送返回信息的规则  ==>  在多用户情况下，只要推送成功一个就算成功
            if(phone != null){
                for (String openId :openid) {
                    map.put("touser",openId);
                    String  msg = utils.sendMessageTemplate(map);
                    if (msg.contains("\"errcode\":0")){
                        result = resultUtils.Success(msg);
                    }else {
                        if(result != null && result.getCode() == 0){
                            result = resultUtils.Success("推送成功");
                            continue;
                        }
                        result = resultUtils.Error(msg);
                    }
                    log.info("openID ==> " + openId + " 通过websevice推送消息返回值 " + result);
                }
            }

            if(null==result){
                return JSON.toJSONString(resultUtils.Error(-1,"该号码无fromId"));
            }
            return JSON.toJSONString(result);
        }

    }
}
