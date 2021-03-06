package io.renren.modules.miniapp.entity.bargelink;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 驳船信息类
 */
@TableName("LTR_SCHEDULE")
public class LtrSchedule implements Serializable {

    private String eta; //预计到达时间

    private String iAta; //实际到达时间

    private String ataTemp; //报到时间的临时变量

    private String iVoyCd; //进口航次

    private String oVoyCd; //出口航次

    private String voyCd; //主键

    private String updateTime;

    private String bargeNameCn; //船名

    private String ltrCd; //驳船代码

    private String onBondTel; //船上电话

    private String berth; //泊位

    private String updateUsr;

    private String inspInd;//是否办理联检  0：否  1：是

    private String qc;

    private String comeHere;//是否报到

    private String iOrdInd; //卸货下单

    private String oOrdInd; //装货下单

    private String oldEta; //修改之后的eta

    private String confirm;//中控确认

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getOldEta() {
        return oldEta;
    }

    public void setOldEta(String oldEta) {
        this.oldEta = oldEta;
    }

    public String getiOrdInd() {
        return iOrdInd;
    }

    public void setiOrdInd(String iOrdInd) {
        this.iOrdInd = iOrdInd;
    }

    public String getoOrdInd() {
        return oOrdInd;
    }

    public void setoOrdInd(String oOrdInd) {
        this.oOrdInd = oOrdInd;
    }

    public String getComeHere() {
        return comeHere;
    }

    public void setComeHere(String comeHere) {
        this.comeHere = comeHere;
    }

    public String getQc() {
        return qc;
    }

    public void setQc(String qc) {
        this.qc = qc;
    }

    public String getInspInd() {
        return inspInd;
    }

    public void setInspInd(String inspInd) {
        this.inspInd = inspInd;
    }

    public String getAtaTemp() {
        return ataTemp;
    }

    public void setAtaTemp(String ataTemp) {
        this.ataTemp = ataTemp;
    }

    public String getUpdateUsr() {
        return updateUsr;
    }

    public void setUpdateUsr(String updateUsr) {
        this.updateUsr = updateUsr;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getiAta() {
        return iAta;
    }

    public void setiAta(String iAta) {
        this.iAta = iAta;
    }

    public String getiVoyCd() {
        return iVoyCd;
    }

    public void setiVoyCd(String iVoyCd) {
        this.iVoyCd = iVoyCd;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getVoyCd() {
        return voyCd;
    }

    public void setVoyCd(String voyCd) {
        this.voyCd = voyCd;
    }

    public String getBargeNameCn() {
        return bargeNameCn;
    }

    public void setBargeNameCn(String bargeNameCn) {
        this.bargeNameCn = bargeNameCn;
    }

    public String getLtrCd() {
        return ltrCd;
    }

    public void setLtrCd(String ltrCd) {
        this.ltrCd = ltrCd;
    }

    public String getoVoyCd() {
        return oVoyCd;
    }

    public void setoVoyCd(String oVoyCd) {
        this.oVoyCd = oVoyCd;
    }

    public String getOnBondTel() {
        return onBondTel;
    }

    public void setOnBondTel(String onBondTel) {
        this.onBondTel = onBondTel;
    }

    public String getBerth() {
        return berth;
    }

    public void setBerth(String berth) {
        this.berth = berth;
    }
}
