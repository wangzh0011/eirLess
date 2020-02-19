package io.renren.modules.miniapp.ws.config;


import io.renren.modules.miniapp.ws.service.EirLessHandleService;
import io.renren.modules.miniapp.ws.service.SendMessageTemplateService;
import io.renren.modules.miniapp.ws.service.impl.SendMessageTemplateServiceImpl;
import io.renren.modules.miniapp.ws.service.impl.UserServiceImpl;
import io.renren.modules.miniapp.ws.service.testUserService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;




/**
 * Created by timmy.deng on 2018/5/16.
 */
@Configuration
public class WsConfig {
    @Autowired
    private Bus bus;


    @Autowired
    private UserServiceImpl testuserService;

    @Autowired
    private SendMessageTemplateServiceImpl TemplateService;

    @Autowired
    private EirLessHandleService handleService;


    /*@Bean
    public ServletRegistrationBean dispatcherServlet(){
        return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
    }*/
    /*@Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus(){
        return  new SpringBus();
    }*/


    @Bean
    public testUserService testuserService(){
        return  testuserService;
    }

    @Bean
    public SendMessageTemplateService TemplateService(){
        return  TemplateService;
    }

    @Bean
    public EirLessHandleService handleService(){
        return  handleService;
    }

    @Bean
    public Endpoint TemplateServiceEndpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus,TemplateService());

        endpoint.publish("/sendMessage");
        System.out.println("sendMessage服务启动");
        return  endpoint;
    }

    @Bean
    public Endpoint endpoint(){
        org.apache.cxf.jaxws.EndpointImpl endpoint = new org.apache.cxf.jaxws.EndpointImpl(bus,testuserService());

        endpoint.publish("/user");
        System.out.println("userService服务启动");
        return  endpoint;
    }

    @Bean
    public Endpoint handleServiceEndpoint(){
        org.apache.cxf.jaxws.EndpointImpl endpoint = new org.apache.cxf.jaxws.EndpointImpl(bus,handleService());

        endpoint.publish("/eirLessHandle");
        System.out.println("eirLessHandle服务启动");
        return endpoint;
    }




}
