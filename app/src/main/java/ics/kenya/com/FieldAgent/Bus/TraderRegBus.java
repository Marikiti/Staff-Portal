package ics.kenya.com.FieldAgent.Bus;

public class TraderRegBus {
    private static TraderRegBus single_instance = null;

    private TraderRegBus() {
    }

    public static TraderRegBus getInstance() {
        if (single_instance == null)
            single_instance = new TraderRegBus();

        return single_instance;
    }

    public static TraderRegBus getSingle_instance() {
        return single_instance;
    }

    public static void setSingle_instance(TraderRegBus single_instance) {
        TraderRegBus.single_instance = single_instance;
    }
    private int id;
    private String tradercode;
    private String fullnames;
    private String idno;
    private String dob;
    private String marikitiUserNo;
    private String username;
    private String email;
    private String phoneNo;
    private String address;
    private String county;
    private String constituency;
    private String ward;
    private String salesTarget;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTradercode() {
        return tradercode;
    }

    public void setTradercode(String tradercode) {
        this.tradercode = tradercode;
    }

    public String getFullnames() {
        return fullnames;
    }

    public void setFullnames(String fullnames) {
        this.fullnames = fullnames;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMarikitiUserNo() {
        return marikitiUserNo;
    }

    public void setMarikitiUserNo(String marikitiUserNo) {
        this.marikitiUserNo = marikitiUserNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getSalesTarget() {
        return salesTarget;
    }

    public void setSalesTarget(String salesTarget) {
        this.salesTarget = salesTarget;
    }
}
