package com.jinfei.jfmm.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String userId;

    private String deptId;

    private String loginName;

    private String userName;

    private String phonenumber;

    private String password;

    private String salt;

    private String roleId;

    private String status;

    private String delFlag;

    private String loginIp;

    private Date loginDate;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "{" +
                "\"userId\":\"" + userId + '\"' +
                ",\"deptId\":\"" + deptId + '\"' +
                ",\"loginName\":\"" + loginName + '\"' +
                ",\"userName\":\"" + userName + '\"' +
                ",\"phonenumber\":\"" + phonenumber + '\"' +
                ",\"password\":\"" + password + '\"' +
                ",\"salt\":\"" + salt + '\"' +
                ",\"roleId\":\"" + roleId + '\"' +
                ",\"status\":\"" + status + '\"' +
                ",\"delFlag\":\"" + delFlag + '\"' +
                ",\"loginIp\":\"" + loginIp + '\"' +
                ",\"loginDate\":\"" + loginDate + '\"' +
                ",\"createBy\":\"" + createBy + '\"' +
                ",\"createTime\":\"" + createTime + '\"' +
                ",\"updateBy\":\"" + updateBy + '\"' +
                ",\"updateTime\":\""+ updateTime + '\"' +
                '}';
    }
}