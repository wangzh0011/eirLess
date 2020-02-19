package io.renren.modules.eir.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.eir.until.StateUntil;
import io.renren.modules.miniapp.entity.HistoryOrder;
import io.renren.modules.miniapp.service.HistoryOrderService;
import io.renren.modules.miniapp.until.tranTypeUtils;
import io.renren.modules.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * Created by timmy.deng on 2018/9/21.
 */
@RestController
@RequestMapping("eir/history")
public class EirHistoryController {
    @Autowired
    private HistoryOrderService historyOrderService;
    @Autowired
    private SysConfigService sysConfigService;

    private tranTypeUtils typeUtils= new tranTypeUtils();

    private StateUntil stateUntil = new StateUntil();
    @RequestMapping("/page")
    public R getAll(@RequestParam Map<String, Object> params){
        String url = sysConfigService.getValue("uploadUrl");
        PageUtils page = historyOrderService.queryPage(params);
        for (Object o : page.getList()) {
            HistoryOrder it=(HistoryOrder)o;
            if(null != it.getSealImg()){
                it.setSealImg(url+it.getSealImg());
            }
            if(null != it.getSealImg1()){
                it.setSealImg1(url+it.getSealImg1());
            }
            if (null!=it.getEirImg()){
                it.setEirImg(url+it.getEirImg());}
            if (null != it.getAttachImg()){
                it.setAttachImg(url+it.getAttachImg());
            }
            if (null != it.getAttachImg1()){
                it.setAttachImg1(url+it.getAttachImg1());
            }
            try {
                it.setTranType(typeUtils.change(it.getTranType()));
                it.setState(stateUntil.Change(it.getState()));
            } catch (Exception e) {

            }
        }
        return R.ok().put("page", page);
    }
}
