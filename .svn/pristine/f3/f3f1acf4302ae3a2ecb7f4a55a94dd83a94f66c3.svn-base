package io.renren.modules.eir.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.R;
import io.renren.modules.miniapp.entity.FormId;
import io.renren.modules.miniapp.service.FormIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by timmy.deng on 2018/9/28.
 */
@RestController
@RequestMapping("eir/form")
public class FormIdController {
    @Autowired
    private FormIdService formIdService;
    public SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @RequestMapping("/count")
    public  R getCountFormId( @RequestParam String openid){
        System.out.println(openid);
        List<FormId> ls = formIdService.selectList(new EntityWrapper<FormId>().eq("open_id",openid));
        List<FormId> dl = new ArrayList<>();
        //删除无效的formId


        for (FormId it : ls) {
            try {
                if (format.parse(it.getExpireTime()).getTime() < new Date().getTime()){
                    formIdService.deleteById(it.getId());
                    dl.add(it);
                }

            }catch (Exception e){

            }

        }

        if (null != dl && dl.size()>0 ){
            System.out.println("无效的form_id:"+dl.size());
            for (FormId formId : dl) {
                System.out.println(formId.getId());
                formIdService.delete(new EntityWrapper<>(formId));
            }


        }
        ls.removeAll(dl);

        System.out.println("ls.size "+ls.size());

        return R.ok(ls.size()+"");
    }

}
