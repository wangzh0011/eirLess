package io.renren.modules.miniapp.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

/**
 * Created by timmy.deng on 2018/10/24.
 */


@WebListener
public class SessionListener  implements HttpSessionListener {

    static  {
        System.out.println("SessionListener初始化");
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext context = httpSessionEvent.getSession().getServletContext();
        if (context.getAttribute("count")==null) {
            System.out.println("放入数据");
            context.setAttribute("count", 0);
        }else {
            int count = (Integer) context.getAttribute("count");
            System.out.println("放入数据");
            context.setAttribute("count", count+1);
        }


    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("数据减1");
        ServletContext context = httpSessionEvent.getSession().getServletContext();
        if (context.getAttribute("count")==null) {
            context.setAttribute("count", 0);
        }else {
            int count = (Integer) context.getAttribute("count");
            if (count<1) {
                count = 1;
            }
            context.setAttribute("count", count-1);
        }
        HttpSession session = httpSessionEvent.getSession();
        String name = (String) session.getAttribute("name");
        HashSet<String> nameSet = (HashSet<String>) context.getAttribute("nameSet");
        nameSet.remove(name);


    }
}

