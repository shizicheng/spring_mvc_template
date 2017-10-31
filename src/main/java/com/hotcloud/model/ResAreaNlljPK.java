package com.hotcloud.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ResAreaNlljPK implements Serializable {
    private String dateline;
    private double areaguid;

    @Column(name = "dateline", nullable = false, length = 50)
    @Id
    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    @Column(name = "areaguid", nullable = false, precision = 0)
    @Id
    public double getAreaguid() {
        return areaguid;
    }

    public void setAreaguid(double areaguid) {
        this.areaguid = areaguid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResAreaNlljPK that = (ResAreaNlljPK) o;

        if (Double.compare(that.areaguid, areaguid) != 0) return false;
        if (dateline != null ? !dateline.equals(that.dateline) : that.dateline != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = dateline != null ? dateline.hashCode() : 0;
        temp = Double.doubleToLongBits(areaguid);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
