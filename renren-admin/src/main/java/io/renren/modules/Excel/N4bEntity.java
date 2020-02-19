package io.renren.modules.Excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * Created by timmy.deng on 2018/12/4.
 */
public class N4bEntity {

    @Excel(name = "USER_ID")
    private String USER_ID;
    @Excel(name = "USER_NAME")
    private String USER_NAME;

    @Excel(name = "ROLE_NAME")
    private String ROLE_NAME;

    @Excel(name = "USER_FIRST_NAME")
    private String USER_FIRST_NAME;
    @Excel(name = "USER_LAST_NAME")
    private String USER_LAST_NAME;

    @Excel(name = "Department")
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getROLE_NAME() {
        return ROLE_NAME;
    }

    public void setROLE_NAME(String ROLE_NAME) {
        this.ROLE_NAME = ROLE_NAME;
    }

    public String getUSER_FIRST_NAME() {
        return USER_FIRST_NAME;
    }

    public void setUSER_FIRST_NAME(String USER_FIRST_NAME) {
        this.USER_FIRST_NAME = USER_FIRST_NAME;
    }

    public String getUSER_LAST_NAME() {
        return USER_LAST_NAME;
    }

    public void setUSER_LAST_NAME(String USER_LAST_NAME) {
        this.USER_LAST_NAME = USER_LAST_NAME;
    }
}
