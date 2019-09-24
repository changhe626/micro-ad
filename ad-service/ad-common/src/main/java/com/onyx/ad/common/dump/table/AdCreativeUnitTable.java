package com.onyx.ad.common.dump.table;


public class AdCreativeUnitTable {

    private Long adId;
    private Long unitId;

    public AdCreativeUnitTable(Long adId, Long unitId) {
        this.adId = adId;
        this.unitId = unitId;
    }

    public AdCreativeUnitTable() {
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }


}
