package io.renren.modules.miniapp.until;
import io.renren.modules.miniapp.entity.Result;


/**
 * Created by timmy.deng on 2018/6/13.
 */
public class ResultUtils {
    public    Result Success(Object data){
        Result result =new Result();
        result.setCode(0);
        result.setMsg("SUCCESS");
        result.setData(data);
        return result;
    }
    public    Result Success(String msg){
        Result result =new Result();
        result.setData(msg);
        result.setCode(0);
        result.setMsg(msg);

        return result;
    }
    public    Result Error(String msg){
        Result result =new Result();
        result.setCode(-1);
        result.setMsg(msg);
        return result;
    }
    public    Result Error(Integer code,String msg){
        Result result =new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
