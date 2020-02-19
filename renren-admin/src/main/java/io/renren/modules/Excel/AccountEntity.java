package io.renren.modules.Excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * Created by timmy.deng on 2018/12/6.
 */
public class AccountEntity {

    @Excel(name = "UID")
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
