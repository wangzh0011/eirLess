package io.renren.modules.miniapp.ws.service.impl;


import io.renren.modules.miniapp.ws.User;
import io.renren.modules.miniapp.ws.service.testUserService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
/**
 * Created by timmy.deng on 2018/5/16.
 */

@Component
@WebService

public class UserServiceImpl implements testUserService {
    @Override
    public String getName(String userId) {
        return "hi ,"+userId;
    }

    @Override
    public User getUser(String userId) {
        User user = new User();
        user.setAge("18");
        user.setUsername("timmy");
        return user;
    }
}
