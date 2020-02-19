package io.renren.modules.miniapp.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.R;
import io.renren.datasources.DataSourceNames;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.miniapp.constant.Constant;
import io.renren.modules.miniapp.entity.bargelink.LtrRegDtl;
import io.renren.modules.miniapp.entity.WXUser;
import io.renren.modules.miniapp.service.LtrRegDtlService;
import io.renren.modules.miniapp.service.WXUserService;
import io.renren.modules.miniapp.until.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by timmy.deng on 2018/9/12.
 */
@RestController
@RequestMapping("/miniApp")
public class WxUserController {
    @Autowired
    public WXUserService wxUserService;

    @Autowired
    private LtrRegDtlService ltrRegDtlService;

    public SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //复杂构造函数的使用
    private SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

    private ResultUtils resultUtils = new ResultUtils();

    private Logger log = LoggerFactory.getLogger(getClass());


    @Value("${gos.server.cms}")
    private  String cms_server;

    @Value("${img.location}")
    private String location;



    @PostMapping(value = "/addUser")
    // @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public  Object addUser(@RequestParam(value = "openId")String openid, @RequestParam(value = "phone")String phone,
                           @RequestParam(value = "plate")String plate, @RequestParam(value = "userCardId")String cardId,
                           @RequestParam(value = "userName")String username,String userType){
        System.out.println("添加用户POST方式");

        WXUser wxUser = new WXUser();

        wxUser.setUserName(username);
        wxUser.setOpenid(openid);

        List<WXUser> list =  wxUserService.selectList(new EntityWrapper<WXUser>().eq("openid",openid));
        if (list.size()>0){
            wxUser.setId(list.get(0).getId());
        }
        if("truck".equals(userType)){
            wxUser.setPlate(plate);
            wxUser.setUserCardId(cardId);
        }
        wxUser.setPhone(phone);
        wxUser.setCreateTime(format.format(new Date()));
        wxUser.setLastLoginTime(format.format(new Date()));
        wxUser.setUserType(userType);//用于区分船舶和拖车用户
        wxUserService.insertOrUpdate(wxUser);

        return wxUser;
    }
    @RequestMapping("/getUser/{openid}")
    public WXUser getWXUserByOpenId(@PathVariable String openid){
        return  wxUserService.selectList(new EntityWrapper<WXUser>().eq("openid",openid)).get(0);

    }

    @RequestMapping("/userInfo/{code}")
    public Object getUserInfo(@PathVariable String code){
        //  System.out.println(code);
        String  Url = Constant.OpenIdUrl.replace("JSCODE",code);
        /*SimpleClientHttpRequestFactory Factory =new  SimpleClientHttpRequestFactory();
        Factory.setConnectTimeout(3000);
        Factory.setReadTimeout(3000);
        Factory.createRequest(new Url(Url), HttpMethod.GET);
        ;*/

        //设置超时参数
        requestFactory.setConnectTimeout(3000);

//        requestFactory.setProxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("172.25.4.105", 8080)));



        RestTemplate restTemplate = new RestTemplate(requestFactory);
        //System.out.println(Url);

        String result=null;
        int count=0;
        while ((null==result||result.contains("connect timed out"))&&count<3){
            count+=1;
            try {
                result = restTemplate.getForObject(Url,String.class);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        /*Map<String,String> result=new HashMap();
        for (String s: result.keySet()){
            System.out.println("key : "+s+"   value : "+result.get(s));
        }*/
        // System.out.println(result);
        //System.out.println("result:    " +result);
        Map<String,String> map=JSON.parseObject(result,Map.class);



        if(map == null || null==map.get("openid") || "undefined".equals(map.get("openid"))){
            System.out.println("未能获取openid:  "+result);
            return R.error("未能获取openid:  "+result);
        }

        log.info("**********************************************************");
        log.info("*****************************"+ map.get("openid") + "*****************************");
        log.info("**********************************************************");
        List<WXUser> wxUser = wxUserService.selectList(new EntityWrapper<WXUser>().eq("openid",map.get("openid"))) ;

        if (wxUser.size()==0){
            //   System.out.println("null");
            return JSON.parse(result);
        }else {
            WXUser user= wxUser.get(0);
            if (user.getUserType()==null){
                user.setUserType("truck");
            }
            return   user;
        }
    }




    @RequestMapping("/updateUser")
    public Object UpdateUser(WXUser user){
        WXUser wxuser= wxUserService.selectOne(new EntityWrapper<WXUser>().eq("id",user.getId()));
        //更新登录时间
        if(user.getLastLoginTime() != null && user.getLastLoginTime() != ""){
            wxuser.setLastLoginTime(user.getLastLoginTime());
            if(user.getNickName() != null){
                wxuser.setNickName(user.getNickName());//后期版本新加字段，所以在用户登录时更新此字段
            }
        }else{
            wxuser.setPlate(user.getPlate());
            wxuser.setUserCardId(user.getUserCardId());
            wxuser.setUserType(user.getUserType());
            wxuser.setPhone(user.getPhone());
            wxuser.setUpdateTime(format.format(new Date()));
            wxuser.setUserName(user.getUserName());
        }

        wxUserService.insertOrUpdate(wxuser);
        return wxuser;
    }

    /**
     * 查询注册信息中的手机号是否存在
     * @param phone
     * @return
     */
    @RequestMapping("/checkPhoneIsExist")
    @DataSource(name = DataSourceNames.SECOND)
    public int selectLtrRegDtl(String phone){
        int count = ltrRegDtlService.selectCount(new EntityWrapper<LtrRegDtl>().like("ob_mob",phone));
        if(count > 0){
            return 1;//exist
        }
        return 0;//not exist
    }


}
