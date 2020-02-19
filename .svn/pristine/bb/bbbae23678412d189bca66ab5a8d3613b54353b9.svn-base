package io.renren.modules.miniapp.controller;

import com.alibaba.fastjson.JSON;
import io.renren.modules.miniapp.ws.client.schema.Documents;
import io.renren.modules.miniapp.ws.client.service.CMSInfo;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;

/**
 * Created by timmy.deng on 2018/9/13.
 */
@RestController
@RequestMapping("/miniApp")
public class CmsController {
    @Value("${gos.server.cms}")
    private  String cms_server;
    @RequestMapping(value = "/getCms/{plate}")//headers = "application/json;charset=UTF-8"
    public Object getCms(@PathVariable String plate){

        //System.out.println(plate);
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        //System.out.println(cms_server);
        jaxWsProxyFactoryBean.setAddress(cms_server);
        jaxWsProxyFactoryBean.setServiceClass(CMSInfo.class);
        CMSInfo cs = (CMSInfo) jaxWsProxyFactoryBean.create();
        String result = cs.getCMSByLic(plate);
        //System.out.println("成功获取到数据getCMSByCard： "+ reslut);
        System.out.println("成功获取到数据getCMSByLic： "+cs.getCMSByLic(plate));

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Documents.class);
            Unmarshaller um = jaxbContext.createUnmarshaller();
            return JSON.toJSONString(um.unmarshal(new ByteArrayInputStream(result.getBytes()))).getBytes("utf-8");
        } catch (Exception e) {
           System.out.println(plate+"的小票信息未能获取.");
        }

        //Documents documents;
        return "cms is null";
    }
}
