package io.renren.modules.eir.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.datasources.DataSourceNames;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.eir.until.SendTemplateMessageUtil;
import io.renren.modules.miniapp.entity.bargelink.LtrRegDtl;
import io.renren.modules.miniapp.entity.bargelink.LtrSchedule;
import io.renren.modules.miniapp.entity.Order;
import io.renren.modules.miniapp.service.LtrRegDtlService;
import io.renren.modules.miniapp.service.LtrScheduleService;
import io.renren.modules.miniapp.until.MessageTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 废弃  2019-06-25  alan.wang
 */
@RestController
@RequestMapping("eir/ltrSchedule")
public class EirLtrScheduleController {

    @Resource
    private LtrScheduleService ltrScheduleService;

    @Resource
    private LtrRegDtlService ltrRegDtlService;

    @Resource
    MessageTemplateUtils messageTemplateUtils;

    @RequestMapping("/ataList")
    @DataSource(name = DataSourceNames.SECOND)
    public R getAta(@RequestParam Map<String, Object> params){
        PageUtils page = ltrScheduleService.queryPage(params);
        List<LtrSchedule> list = (List<LtrSchedule>) page.getList();
        for (LtrSchedule ltr : list) {
            LtrRegDtl ltrRegDtl = ltrRegDtlService.selectOne(new EntityWrapper<LtrRegDtl>().eq("ltr_cd",ltr.getLtrCd()));
            if(ltrRegDtl == null){
                continue;
            }
            ltr.setBargeNameCn(ltrRegDtl.getLocalLtrName());
        }
        return R.ok().put("page", page);
    }

    @RequestMapping("/etaList")
    @DataSource(name = DataSourceNames.SECOND)
    public R getEta(@RequestParam Map<String, Object> params){
        PageUtils page = ltrScheduleService.queryPage(params);
        List<LtrSchedule> list = (List<LtrSchedule>) page.getList();
        for (LtrSchedule ltr : list) {
            LtrRegDtl ltrRegDtl = ltrRegDtlService.selectOne(new EntityWrapper<LtrRegDtl>().eq("ltr_cd",ltr.getLtrCd()));
            if(ltrRegDtl == null){
                continue;
            }
            ltr.setBargeNameCn(ltrRegDtl.getLocalLtrName());
        }
        return R.ok().put("page", page);
    }

    @RequestMapping("/showInfo")
    @DataSource(name = DataSourceNames.SECOND)
    public R getInfoByVoyCd(@RequestParam String voyCd){
        LtrSchedule ltrSchedule = ltrScheduleService.selectOne(new EntityWrapper<LtrSchedule>().eq("voy_cd",voyCd));
        LtrRegDtl ltrRegDtl = ltrRegDtlService.selectOne(new EntityWrapper<LtrRegDtl>().eq("ltr_cd",ltrSchedule.getLtrCd()));
        if(ltrRegDtl == null){
            return R.ok().put("ltrSchedule", ltrSchedule);
        }
        ltrSchedule.setBargeNameCn(ltrRegDtl.getLocalLtrName());
        return R.ok().put("ltrSchedule", ltrSchedule);
    }

    //test
    @RequestMapping("/sendMsg")
    public R sendMsg(@RequestParam String iVoyCd, @RequestParam String berth, @RequestParam String bargeNameCn, @RequestParam String ata){
        Order order = new Order();
        order.setOpenId("oVczl5bppC7pMH8zS4ZtCyux65U8");
        Map map = SendTemplateMessageUtil.sendWxMsg(order, "test", "-vG2tZDZyVUX5QZSaI7OKKlgaIL0fBSiclNJpHcCdt8", "测试", "");
        String result = messageTemplateUtils.sendMessageTemplate(map);
        return R.ok();
    }


}
