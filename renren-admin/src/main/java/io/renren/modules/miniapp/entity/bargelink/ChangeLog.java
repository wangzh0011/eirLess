package io.renren.modules.miniapp.entity.bargelink;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * bargelink中修改船期的日志类
 */
@TableName("CHANGE_LOG")
public class ChangeLog {

    @TableId
    private int logId;
    private String logType;
    private String logDtl;
    private String logStatus;
    private String updTime;
    private String voyCd;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogDtl() {
        return logDtl;
    }

    public void setLogDtl(String logDtl) {
        this.logDtl = logDtl;
    }

    public String getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(String logStatus) {
        this.logStatus = logStatus;
    }

    public String getUpdTime() {
        return updTime;
    }

    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    public String getVoyCd() {
        return voyCd;
    }

    public void setVoyCd(String voyCd) {
        this.voyCd = voyCd;
    }
}
