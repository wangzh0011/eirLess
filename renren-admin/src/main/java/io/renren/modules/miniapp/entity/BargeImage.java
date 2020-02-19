package io.renren.modules.miniapp.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 驳船上传的上一港缴费清单类
 */
@TableName("barge_image")
public class BargeImage {

    @TableId(type = IdType.UUID)
    private String id;

    private String iVoyCd;

    private String oVoyCd;

    private String imagePath;

    private String vesselName;

    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getiVoyCd() {
        return iVoyCd;
    }

    public void setiVoyCd(String iVoyCd) {
        this.iVoyCd = iVoyCd;
    }

    public String getoVoyCd() {
        return oVoyCd;
    }

    public void setoVoyCd(String oVoyCd) {
        this.oVoyCd = oVoyCd;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
