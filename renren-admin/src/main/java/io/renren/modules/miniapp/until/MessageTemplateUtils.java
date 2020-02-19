package io.renren.modules.miniapp.until;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.modules.miniapp.constant.Constant;
import io.renren.modules.miniapp.entity.AccessToken;
import io.renren.modules.miniapp.entity.FormId;
import io.renren.modules.miniapp.service.FormIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by timmy.deng on 2018/5/28.
 */
@Component

public  class MessageTemplateUtils {
    protected Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private FormIdService formIdService;

    //复杂构造函数的使用
    private SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

    public SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public  String sendMessageTemplate(Map map) {

        log.info("**********************************begin to send message**********************************");
        log.info("the map is " + map.toString());
        Date now = new Date();
        Date expire = new Date();

        //设置连接超时时间
        requestFactory.setConnectTimeout(3000);
//        requestFactory.setProxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("172.25.4.105", 8080)));

        RestTemplate restTemplate = new RestTemplate(requestFactory);




        List<FormId> list =  formIdService.selectList(new EntityWrapper<FormId>().eq("open_id",(String) map.get("touser")));// formIdService.findByOpenIdOrderByExpireTime((String) map.get("touser"));

        //删除过期数据


        for (FormId s : list) {
            String result = null;
            int count=0;


            try {
                expire=format.parse(s.getExpireTime());
                if (expire.getTime()<now.getTime()){
                    formIdService.delete(new EntityWrapper<>(s));
                    continue;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }




            map.put("form_id", s.getFromId());
            if (null==result||result.contains("connect timed out")){
                count=+1;
                String token =AccessToken.getInstance().getToken();
                try {
                    if(count>=1){

                        Thread.sleep(1000);

                    }


                    if (null==token){

                        token =  AccessTokenUtils.updateAccessToken().getToken();


                    }


                    result = restTemplate.postForObject(Constant.templateUrl.replace("ACCESS_TOKEN", token) , map, String.class);
                    //System.out.println("result值：  "+result);
                    log.info("推送时map值:"+JSON.toJSONString(map));
                } catch (Exception e) {
                    log.info("**********************************************************************");
                    log.info("推送异常 token = " + token);
                    log.info("推送异常 map = " + map);
                    log.error("推送异常 result = " + result,e);
                    log.info("**********************************************************************");
                }
            }

            if(result != null && (result.contains("40037") || result.contains("40037"))){
                System.out.println("推送失败,原因为  "+result);
                return  result;
            }
            if (result != null && (result.contains("40001") || result.contains("42001"))) {
                AccessTokenUtils.updateAccessToken();
                result = restTemplate.postForObject(Constant.templateUrl.replace("ACCESS_TOKEN", AccessToken.getInstance().getToken()) , map, String.class);

            }
            if (result != null && (result.contains("41028") || result.contains("41029")||result.contains("40003"))) {
                System.out.println("推送失败,原因为  "+result+"   删除记录id为："+ s.getId());
              formIdService.delete(new EntityWrapper<>(s));
            }
            if (result != null && result.contains("\"errcode\":0")) {
                System.out.println("推送成功,删除记录id为："+ s.getId());
                formIdService.delete(new EntityWrapper<>(s));
                return result;
            }

        }

            HashMap m = new HashMap();
            m.put("code",-1);
            m.put("msg","无formId");
            log.info("无formId");
            return JSON.toJSONString(m);

    }




    public static void main(String[] args) {

        MessageTemplateUtils utils = new MessageTemplateUtils();
        System.out.println(JSON.toJSONString(null));
         SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



            Date now = new Date();
            Date exprire= new Date();
        try {
            exprire=format.parse("2018-11-28 16:33:30");

            System.out.println(exprire.getTime()<now.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (exprire.getTime()<now.getTime()){


        }

    }
}
