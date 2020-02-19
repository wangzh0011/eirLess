package io.renren.modules.miniapp.ws.service;

import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by timmy.deng on 2018/6/14.
 */
@Service
@WebService(
        targetNamespace = "http://serviceImpl.ws.dcb.com/")
public interface SendMessageTemplateService {
    /**
     *
     * @param mapString map  MAP类型的json String
     * @return  json String 推送结果
     */
     String SendMessage(@WebParam(name = "mapString") String mapString);



}
