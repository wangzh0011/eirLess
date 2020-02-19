package io.renren.modules.miniapp.constant;

import org.springframework.stereotype.Component;

/**
 * Created by timmy on 2018/5/9.
 */
@Component
public final class Constant {
     //测试
     //public final static String AppId  = "wxaedc73f468caad54";
     //生产
      private   static  String AppId ="wx3d175ab8d0c5babd";


     //测试
     //public final static String AppSecret = "f6019049353abdfd98e066936b05df21";

    //生产
     private  static  String AppSecret="f251f2ca461b6fe04cc96b5f5bb9813f";

     public final static String OpenIdUrl = "https://api.weixin.qq.com/sns/jscode2session?appid="+AppId+"&secret="+AppSecret+"&js_code=JSCODE&grant_type=authorization_code";
     public final static String TokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+AppId+"&secret="+AppSecret;
     public final static String templateUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";


}
