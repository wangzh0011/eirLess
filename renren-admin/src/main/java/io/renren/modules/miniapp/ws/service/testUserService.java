package io.renren.modules.miniapp.ws.service;


import io.renren.modules.miniapp.ws.User;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by timmy.deng on 2018/5/16.
 */
@WebService
public interface testUserService {
      String getName(@WebParam(name = "userId") String userId);
      User getUser(@WebParam(name = "userId") String userId);
}
