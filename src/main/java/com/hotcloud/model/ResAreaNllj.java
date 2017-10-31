package com.hotcloud.model;

import javax.persistence.*;

@Entity
@Table(name = "ResAreaNllj", schema = "public", catalog = "demo")
@IdClass(ResAreaNlljPK.class)
public class ResAreaNllj {
    private String dateline;
    private double areaguid;
    private Double meternllj;
    private String areaname;
    private Double jindu;
    private Double weidu;

    @Id
    @Column(name = "dateline", nullable = false, length = 50)
    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    @Id
    @Column(name = "areaguid", nullable = false, precision = 0)
    public double getAreaguid() {
        return areaguid;
    }

    public void setAreaguid(double areaguid) {
        this.areaguid = areaguid;
    }

    @Basic
    @Column(name = "meternllj", nullable = true, precision = 0)
    public Double getMeternllj() {
        return meternllj;
    }

    public void setMeternllj(Double meternllj) {
        this.meternllj = meternllj;
    }

    @Basic
    @Column(name = "areaname", nullable = true, length = 50)
    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    @Basic
    @Column(name = "jindu", nullable = true, precision = 0)
    public Double getJindu() {
        return jindu;
    }

    public void setJindu(Double jindu) {
        this.jindu = jindu;
    }

    @Basic
    @Column(name = "weidu", nullable = true, precision = 0)
    public Double getWeidu() {
        return weidu;
    }

    public void setWeidu(Double weidu) {
        this.weidu = weidu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResAreaNllj that = (ResAreaNllj) o;

        if (Double.compare(that.areaguid, areaguid) != 0) return false;
        if (dateline != null ? !dateline.equals(that.dateline) : that.dateline != null) return false;
        if (meternllj != null ? !meternllj.equals(that.meternllj) : that.meternllj != null) return false;
        if (areaname != null ? !areaname.equals(that.areaname) : that.areaname != null) return false;
        if (jindu != null ? !jindu.equals(that.jindu) : that.jindu != null) return false;
        if (weidu != null ? !weidu.equals(that.weidu) : that.weidu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = dateline != null ? dateline.hashCode() : 0;
        temp = Double.doubleToLongBits(areaguid);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (meternllj != null ? meternllj.hashCode() : 0);
        result = 31 * result + (areaname != null ? areaname.hashCode() : 0);
        result = 31 * result + (jindu != null ? jindu.hashCode() : 0);
        result = 31 * result + (weidu != null ? weidu.hashCode() : 0);
        return result;
    }
}
