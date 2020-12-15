package com.project.cache.dto;

public class CacheConfig {

    /** 连接地址字符串 */
    private String connectAddress;

    /** 最大等待连接中的数量 */
    private Integer maxIdle;

    /** 最大连接数据库连接数 */
    private Integer maxActive;

    /** 数据库密码 */
    private String cacheUser;

    /** 数据库密码 */
    private String password;

    /** 数据库编号 */
    private Integer dbNum = 0;

    /** 云端转为本地的地址*/
    private String cloudToLocalAddress;

    public String getConnectAddress() {
        return connectAddress;
    }

    public void setConnectAddress(String connectAddress) {
        this.connectAddress = connectAddress;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public String getCacheUser() {
        return cacheUser;
    }

    public void setCacheUser(String cacheUser) {
        this.cacheUser = cacheUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDbNum() {
        return dbNum;
    }

    public void setDbNum(Integer dbNum) {
        this.dbNum = dbNum;
    }

    public String getCloudToLocalAddress() {
        return cloudToLocalAddress;
    }

    public void setCloudToLocalAddress(String cloudToLocalAddress) {
        this.cloudToLocalAddress = cloudToLocalAddress;
    }

}
