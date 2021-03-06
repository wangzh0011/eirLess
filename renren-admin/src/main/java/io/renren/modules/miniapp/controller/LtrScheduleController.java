package io.renren.modules.miniapp.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.datasources.DataSourceNames;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.miniapp.entity.bargelink.ChangeLog;
import io.renren.modules.miniapp.entity.bargelink.LtrLdDtl;
import io.renren.modules.miniapp.entity.bargelink.LtrSchedule;
import io.renren.modules.miniapp.service.ChangeLogService;
import io.renren.modules.miniapp.service.LtrLdDtlService;
import io.renren.modules.miniapp.service.LtrScheduleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/miniApp")
public class LtrScheduleController {

    @Resource
    private LtrScheduleService ltrScheduleService;

    @Resource
    private ChangeLogService changeLogService;

    @Resource
    private LtrLdDtlService ltrLdDtlService;

    /**
     * 获取船期
     * @param phone 船方绑定手机号
     * @param userName 船名
     * @return
     */
    @RequestMapping("/getEta")
    @DataSource(name = DataSourceNames.SECOND)
    public List<LtrSchedule> getEta(String phone,String userName){
        List<LtrSchedule> list = ltrScheduleService.selectList(new EntityWrapper<LtrSchedule>().like("on_bond_tel",phone)
                .or().eq("barge_name_cn",userName)
                .andNew()
                .isNotNull("i_dcb_voy_cd").isNull("i_atd")
                .or().isNotNull("o_dcb_voy_cd").isNull("o_atd")
                /*.ge("eta",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime()-1000*60*60*24*5))*/
        );
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    /**
     * 船方修改ETA
     * @param eta
     * @param voyCd
     * @return
     */
    @RequestMapping("/saveEta")
    @DataSource(name = DataSourceNames.SECOND)
    public boolean saveEta(String eta,String voyCd){

        //修改之前的信息
        LtrSchedule oldSchedule = ltrScheduleService.selectOne(new EntityWrapper<LtrSchedule>().eq("voy_cd",voyCd));

        /*船期信息*/
        LtrSchedule schedule = new LtrSchedule();
        eta = eta.replace("-","").replace(":","").replace(" ","")+"00";
        schedule.setEta(eta);
        schedule.setUpdateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        schedule.setUpdateUsr("");
        schedule.setOldEta(oldSchedule.getEta());

        /*记录修改的日志信息*/
        ChangeLog log = new ChangeLog();
        log.setLogDtl("ETA " + oldSchedule.getEta() + " -> " + eta);
        log.setLogStatus("N");
        log.setLogType("0");
        log.setUpdTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        log.setVoyCd(voyCd);

        //update eta --bargelink
        boolean flag = ltrScheduleService.update(schedule,new EntityWrapper<LtrSchedule>()
                .eq("voy_cd",voyCd));

        //insert changelog
        changeLogService.insertValue(log.getLogType(),log.getLogDtl(),log.getLogStatus(),log.getUpdTime(),log.getVoyCd());

        return flag;
    }

    /**
     * 报到
     * @param ata
     * @param voyCd
     * @param isTakeStep
     * @param comeHere
     * @return
     */
    @RequestMapping("/saveAta")
    @DataSource(name = DataSourceNames.SECOND)
    public boolean saveAta(String ata,String voyCd,String isTakeStep,String comeHere){

        //判断用户是否具有报到资格
        if(!CanSetAta(voyCd)){
            return false;
        }

        //开始报到
        LtrSchedule schedule = new LtrSchedule();
        ata = ata + ":00";
        schedule.setAtaTemp(ata);
        schedule.setInspInd(isTakeStep);//联检
        schedule.setComeHere(comeHere);//报到按钮显示
        schedule.setConfirm("N");//将中控确认设置成N  bargelink上显示取消报到
        boolean flag = ltrScheduleService.update(schedule,new EntityWrapper<LtrSchedule>()
                .eq("voy_cd",voyCd));
        return flag;
    }

    /**
     * 取消报到
     * @param ata
     * @param voyCd
     * @param comeHere
     * @return
     */
    @RequestMapping("/reSetAta")
    @DataSource(name = DataSourceNames.SECOND)
    public boolean reSetAta(String ata,String voyCd,String comeHere){
        LtrSchedule schedule = new LtrSchedule();
        schedule.setAtaTemp(ata);
        schedule.setQc("");
        schedule.setComeHere(comeHere);
        schedule.setConfirm("N");//将中控确认设置成N  bargelink上显示取消报到
        boolean flag = ltrScheduleService.update(schedule,new EntityWrapper<LtrSchedule>()
                .eq("voy_cd",voyCd));
        return flag;
    }

    /**
     * 判断用户是否具有报到资格
     *
     * 卸/装量	    卸/装下单    可否报到	提示
     * 无卸量/有装量	无Y/无Y	    不可报到	提示：报到失败。原因：资料不齐，请联系驳船计划组：0755-29022956
     * 	            无Y/有Y	    可报到	　
     * 有卸量/无装量	无Y/无Y	    不可报到	提示：报到失败。原因：资料不齐，请联系驳船计划组：0755-29022956
     * 	            有Y/无Y	    可报到	　
     * 有卸量/有装量	无Y/无Y	    不可报到	提示：报到失败。原因：资料不齐，请联系驳船计划组：0755-29022956
     * 	            无Y/有Y	    不可报到	提示：报到失败。原因：资料不齐，请联系驳船计划组：0755-29022956
     * 	            有Y/无Y	    可报到	　
     * 	            有Y/有Y	    可报到	　
     * @param voyCd
     * @return
     */
    @DataSource(name = DataSourceNames.SECOND)
    public boolean CanSetAta(String voyCd){
        LtrSchedule ltrSchedule = ltrScheduleService.selectOne(new EntityWrapper<LtrSchedule>().eq("voy_cd",voyCd));
        List<LtrLdDtl> list = ltrLdDtlService.selectList(new EntityWrapper<LtrLdDtl>().eq("voy_cd",voyCd));
        String iOrdInd = ltrSchedule.getiOrdInd();//卸货下单
        String oOrdInd = ltrSchedule.getoOrdInd();//装货下单
        String ldInd = null;
        //判断有卸量/有装量的情况   或者只有多个装量、多个卸量
        if(list.size() > 1){
            StringBuffer sb = new StringBuffer();
            for (LtrLdDtl ld : list) {
                ldInd = ld.getlDInd();
                if("L".equals(ldInd)){
                    sb.append(ldInd);
                }
                if("D".equals(ldInd)){
                    sb.append(ldInd);
                }
            }
            if(sb.toString().contains("LD") || sb.toString().contains("DL")){
                if((iOrdInd == null || "".equals(iOrdInd)) && (oOrdInd == null || "".equals(oOrdInd))){//无装、卸下单
                    return false;
                }
                if((iOrdInd == null || "".equals(iOrdInd)) && "Y".equals(oOrdInd)){//无卸，有装下单
                    return false;
                }
                if("Y".equals(iOrdInd) && (oOrdInd == null || "".equals(oOrdInd))){//有卸，无装下单
                    return true;
                }
                if("Y".equals(iOrdInd) && "Y".equals(oOrdInd)){//有卸，有装下单
                    return true;
                }
            }else {//多个装量、多个卸量
                ldInd = list.get(0).getlDInd();
                if((iOrdInd == null || "".equals(iOrdInd)) && (oOrdInd == null || "".equals(oOrdInd))){//无装、卸下单
                    return false;
                }
                if("L".equals(ldInd) && "Y".equals(oOrdInd)){//有装量且有装下单
                    return true;
                }
                if("D".equals(ldInd) && "Y".equals(iOrdInd)){//有卸量且有卸下单
                    return true;
                }
            }
        }

        //判断只有装量或者只有卸量的情况
        if(list.size() == 1){
            ldInd = list.get(0).getlDInd();
            if((iOrdInd == null || "".equals(iOrdInd)) && (oOrdInd == null || "".equals(oOrdInd))){//无装、卸下单
                return false;
            }
            if("L".equals(ldInd) && "Y".equals(oOrdInd)){//有装量且有装下单
                return true;
            }
            if("D".equals(ldInd) && "Y".equals(iOrdInd)){//有卸量且有卸下单
                return true;
            }
        }

        return false;
    }

}
