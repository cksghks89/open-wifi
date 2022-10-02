package api;

public class WifiInfo {
    private double distance;
    private String mgrNo;
    private String wrdofc;
    private String mainName;
    private String adres1;
    private String adres2;
    private String instlFloor;
    private String instlTy;
    private String instlMby;
    private String svcSe;
    private String cmcwr;
    private String cnstcYear;
    private String inOutDoor;
    private String remars3;
    private double lat;
    private double lnt;
    private String workDttm;

    public void setMgrNo(String mgrNo) {
        this.mgrNo = mgrNo;
    }

    public void setDistance(double distance) {
        this.distance = Math.round(distance * Math.pow(10, 4)) / Math.pow(10, 4);
    }
    public void setWrdofc(String wrdofc) {
        this.wrdofc = wrdofc;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public void setAdres1(String adres1) {
        this.adres1 = adres1;
    }

    public void setAdres2(String adres2) {
        this.adres2 = adres2;
    }

    public void setInstlFloor(String instlFloor) {
        this.instlFloor = instlFloor;
    }

    public void setInstlTy(String instlTy) {
        this.instlTy = instlTy;
    }

    public void setInstlMby(String instlMby) {
        this.instlMby = instlMby;
    }

    public void setSvcSe(String svcSe) {
        this.svcSe = svcSe;
    }

    public void setCmcwr(String cmcwr) {
        this.cmcwr = cmcwr;
    }

    public void setCnstcYear(String cnstcYear) {
        this.cnstcYear = cnstcYear;
    }

    public void setInOutDoor(String inOutDoor) {
        this.inOutDoor = inOutDoor;
    }

    public void setRemars3(String remars3) {
        this.remars3 = remars3;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLnt(double lnt) {
        this.lnt = lnt;
    }

    public void setWorkDttm(String workDttm) {
        this.workDttm = workDttm;
    }

    public String getMgrNo() {
        return mgrNo;
    }

    public String getWrdofc() {
        return wrdofc;
    }

    public String getMainName() {
        return mainName;
    }

    public String getAdres1() {
        return adres1;
    }

    public String getAdres2() {
        return adres2;
    }

    public String getInstlFloor() {
        return instlFloor;
    }

    public String getInstlTy() {
        return instlTy;
    }

    public String getInstlMby() {
        return instlMby;
    }

    public String getSvcSe() {
        return svcSe;
    }

    public String getCmcwr() {
        return cmcwr;
    }

    public String getCnstcYear() {
        return cnstcYear;
    }

    public String getInOutDoor() {
        return inOutDoor;
    }

    public String getRemars3() {
        return remars3;
    }

    public double getLat() {
        return lat;
    }

    public double getLnt() {
        return lnt;
    }

    public String getWorkDttm() {
        return workDttm;
    }

    public double getDistance() {
        return distance;
    }
}
