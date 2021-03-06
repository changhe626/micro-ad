package com.onyx.ad.common.dump.table;


public class AdUnitDistrictTable {

    private Long unitId;
    private String province;
    private String city;

    public AdUnitDistrictTable() {
    }

    public AdUnitDistrictTable(Long unitId, String province, String city) {
        this.unitId = unitId;
        this.province = province;
        this.city = city;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
