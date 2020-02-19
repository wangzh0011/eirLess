package io.renren.modules.miniapp.controller;


import io.renren.modules.miniapp.entity.FormId;
import io.renren.modules.miniapp.service.FormIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by timmy.deng on 2018/6/13.
 */
@RestController
@RequestMapping("/miniApp")

public class FromIdController {

    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public FormIdService formIdService;

    public SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //复杂构造函数的使用
    //private SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();


    @Value("${gos.server.cms}")
    private  String cms_server;

    @RequestMapping("/saveFormId")
    public Object saveFormId(@RequestParam("openid") String openid,
                             @RequestParam("formId") String formId, @RequestParam("plate") String plate){
        FormId entity=new FormId();
        entity.setCreateTime(format.format(new Date()));
        entity.setExpireTime(format.format(new Date(new Date().getTime()+604800000))); //7天有效期
        entity.setFromId(formId);
        entity.setOpenId(openid);
        entity.setPlate(plate);
        if("the formId is a mock one".equals(formId) || "undefined".equals(formId)){
            entity.getId();
            return entity;
        }
        formIdService.insert(entity);
        return "成功";
    }
}
