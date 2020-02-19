package io.renren.modules.miniapp.controller;

import com.alibaba.fastjson.JSON;
import io.renren.modules.miniapp.ws.client.schema.Documents;
import io.renren.modules.miniapp.ws.client.service.EIRInfo;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;

/**
 * Created by timmy.deng on 2018/6/26.
 */
@Controller
@RequestMapping("/miniApp")
public class EIRController {

    @Value("${gos.server.eir}")
    private  String eir_server;

    @RequestMapping("/getEir")
    @ResponseBody
    public Object getEirInfo(@RequestParam String cntr_no , @RequestParam String plate){
        String xml = "";
        EIRInfo client = getEIRInfo();
        try{
            if(!cntr_no.equals("")){
                xml = client.getEIRByCntrno(cntr_no.toUpperCase());
            }else if(!plate.equals("")){
                xml = client.getEIRByLic(plate.toUpperCase());
            }
        }catch(Exception e){
            e.printStackTrace();
            return "eir is null";
        }
        //System.out.println(xml);
        if(xml!=null&&!xml.equals("")){
            try{
                JAXBContext jaxbContext = JAXBContext.newInstance(Documents.class);
                Unmarshaller um = jaxbContext.createUnmarshaller();
               //return JSON.toJSONString( (Documents) um.unmarshal(new ByteArrayInputStream(xml.getBytes("UTF-8"))) );
               return JSON.toJSONString( (Documents) um.unmarshal(new ByteArrayInputStream(xml.getBytes())) ).getBytes("UTF-8");
//	        setCont(getSchedules().getSchedule().size());
            }catch(Exception e){
                e.printStackTrace();
                return "eir is null" ;
            }

        }else{
            return "eir is null";
        }





    }
    private EIRInfo getEIRInfo(){

        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        //System.out.println(eir_server);
        jaxWsProxyFactoryBean.setAddress(eir_server);
        jaxWsProxyFactoryBean.setServiceClass(EIRInfo.class);
        return  (EIRInfo) jaxWsProxyFactoryBean.create();
    }
}
