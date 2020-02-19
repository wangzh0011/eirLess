package io.renren.modules.miniapp.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.renren.modules.miniapp.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;


public  class AccessToken {
	private static volatile AccessToken instance=null;
	private String token;
	private Long expiresIn;
	public SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//复杂构造函数的使用
	private static SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
	protected  Logger log = LoggerFactory.getLogger(getClass());
	private AccessToken(){
	}
	
	public static AccessToken getInstance(){
		requestFactory.setConnectTimeout(3000);
//		requestFactory.setProxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("172.25.4.105", 8080)));
		if(instance==null){
			synchronized (AccessToken.class) {
				if (instance==null) {
					instance=new AccessToken();
					RestTemplate restTemplate = new RestTemplate(requestFactory);
					try {
						String result=null;
						int count=0;
						while ((null== result||result.contains("connect timed out"))&&count<6){
							count+=1;
							//SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							try {

								//System.out.println("请求开始时间:"+format.format(new Date()));
								result= restTemplate.getForObject(Constant.TokenUrl,String.class);
								//System.out.println("请求成功时间:"+format.format(new Date()));
							} catch (Exception e) {
								//System.out.println("请求异常结束时间:"+format.format(new Date()));
								e.printStackTrace();
							}
						}
						JSONObject jsonObject = JSON.parseObject(result);
						 count=0;
						while ((jsonObject.get("access_token")==null)&&count<4)
						{
							result= restTemplate.getForObject(Constant.TokenUrl,String.class);

							jsonObject = JSON.parseObject(result);

						}
                        System.out.println("access_token: "+jsonObject.getString("access_token"));
						instance.setToken(jsonObject.getString("access_token"));

						instance.setExpiresIn(jsonObject.getLong("expires_in"));



					}catch (Exception e){
						System.out.println("更新Token值出错:"+e.getMessage());
						//log.info("更新Token值出错:  "+e.getMessage());
						e.printStackTrace();
					}

				}
			}
		}else {




		}

		return instance;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public void setInstance(AccessToken instance) {
		this.instance = instance;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}


	public static void main(String[] args) {
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(new Date()));
	}
	
}
