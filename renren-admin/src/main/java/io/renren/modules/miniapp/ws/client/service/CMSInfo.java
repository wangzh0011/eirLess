package io.renren.modules.miniapp.ws.client.service;

import javax.jws.WebService;

/*
 targetNamespace需要和服务器一致
 可从wsdl namespace复制
 <wsdl:import location="http://pccd:8081/webservices/cmsinfo?wsdl=CMSInfo.wsdl" namespace="http://intf.service.base.mobile.dcb1.com/"></wsdl:import>
 */

@WebService(
		targetNamespace = "http://intf.service.base.mobile.dcb1.com/")

public interface CMSInfo {

	String getCMSByLic(String lic);

	String getCMSByCard(String cardid);

}
