package io.renren.modules.miniapp.listener;

import io.renren.modules.miniapp.constant.Constant;
import io.renren.modules.miniapp.entity.AccessToken;
import io.renren.modules.miniapp.until.AccessTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by timmy.deng on 2018/5/16.
 */

@WebListener
public class CustomServletContextListener implements ServletContextListener  {


    protected Logger log = LoggerFactory.getLogger(getClass());
    Timer timer =new Timer();
    public SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //复杂构造函数的使用
    private SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        requestFactory.setConnectTimeout(3000);
        System.out.println("程序启动");
        System.out.println(Constant.TokenUrl);
        //log.error("test");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                 AccessTokenUtils.updateAccessToken().getToken();


                log.info("监听器更新Token值成功:"+ AccessToken.getInstance().getToken());




/*                RestTemplate restTemplate = new RestTemplate(requestFactory);

                AccessToken accessToken = AccessToken.getInstance();
                try {
                    String result=null;
                    int count=0;
                    while ((null==result||result.contains("connect timed out"))&&count<10){
                        count=count+1;
                            try {
                                result= restTemplate.getForObject(Constant.TokenUrl,String.class);
                            } catch (Exception e) {


                                e.printStackTrace();
                            }
                    }
                    JSONObject jsonObject = JSON.parseObject(result);
                    count=0;
                    while ((jsonObject.get("access_token")==null)&&count<3)
                    {
                        count+=1;

                        result= restTemplate.getForObject(Constant.TokenUrl,String.class);

                        jsonObject = JSON.parseObject(result);
                    }

                    accessToken.setToken(jsonObject.getString("access_token"));

                    accessToken.setExpiresIn(jsonObject.getLong("expires_in"));

                    System.out.println(format.format(new Date())+"  获取Token值"+accessToken.getToken());
                }catch (Exception e){
                    //System.out.println("出错");
                    log.info("更新Token值出错:  "+e.getMessage());
                    e.printStackTrace();
                }
                  */

            }
        },0,117*60*1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {



        System.out.println("程序停止");


    }



    public static void main(String[] args) {
        final SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(new Date(new Date().getTime()+117*60*1000)));
        Timer timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(format.format(new Date()));
            }
        },0,1000);

       /* AccessToken accessToken = AccessToken.getInstance();
        RestTemplate restTemplate = new RestTemplate();
        String result= restTemplate.getForObject(Constant.TokenUrl,String.class);
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result);
        while (jsonObject.get("access_token")==null){
            System.out.println("access_token is null");
        }
        accessToken.setToken(jsonObject.getString("access_token"));
        System.out.println("asd    "+AccessToken.getInstance().getToken());

        String openid="o4K350FEfkY7Pz92DZ-ugglvhIV8";
        String template_id = "ake66mj4ONcCkWuEbw1rjgNS5u0U5bgpAcyMCIf0_Yw";
        String form_id="1527151225228";
        Map<String,String> map = new HashMap<>();

        map.put("touser",openid);
        map.put("template_id",template_id);
        map.put("form_id",form_id);
        map.put("data","data");
        //map.put("page","")
        //restTemplate.postForObject("")

        //发送模板消息

       String resultTemplate = restTemplate.postForObject(Constant.templateUrl.replace("ACCESS_TOKEN",AccessToken.getInstance().getToken())+"1",map,String.class);
       System.out.println(resultTemplate);
*/

    }
}
