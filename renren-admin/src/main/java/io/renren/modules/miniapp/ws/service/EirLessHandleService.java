package io.renren.modules.miniapp.ws.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by timmy.deng on 2018/10/24.
 */
@WebService
public interface EirLessHandleService {

     /**
      *
      * @param mapString map  MAP类型的json String
      * @return  json String 推送结果
      */

      String handle(@WebParam String mapString );
}
