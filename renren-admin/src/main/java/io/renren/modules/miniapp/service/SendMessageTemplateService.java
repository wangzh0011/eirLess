package io.renren.modules.miniapp.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by timmy.deng on 2018/6/14.
 */
@WebService
public interface SendMessageTemplateService {
    /**
     *
     * @param mapString map  MAP类型的json String
     * @return  json String 推送结果
     */
     String SendMessage(@WebParam(name = "mapString") String mapString);


}
