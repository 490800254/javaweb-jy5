package com.itdr.pojo;

public class Users {
    private Integer uid;
    private String uname;
    private String psd;
    private String tel;
    private Integer type=0;
    private Integer status=0;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer id) {
        this.uid = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStats() {
        return status;
    }

    public void setStats(Integer stats) {
        this.status = stats;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + uid +
                ", uname='" + uname + '\'' +
                ", psd='" + psd + '\'' +
                ", tel='" + tel + '\'' +
                ", type=" + type +
                ", stats=" + status +
                '}';
    }
}
