package io.renren.modules.miniapp.ws.client.service;

import javax.jws.WebService;

@WebService(targetNamespace = "http://intf.service.base.mobile.dcb1.com/")
public interface EIRInfo {
	String getEIRByLic(String lic);
	String getEIRByCntrno(String cntr_no);
	String getEIRByCard(String cardid);
}
