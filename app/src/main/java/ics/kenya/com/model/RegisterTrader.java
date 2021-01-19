package ics.kenya.com.model;

import java.io.Serializable;
public class RegisterTrader implements Serializable {
    private int id;
    private String localID;
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
    private String status;

    public RegisterTrader() {
    }

    public String getLocalID() {
        return localID;
    }

    public void setLocalID(String localID) {
        this.localID = localID;
    }

    public RegisterTrader(int id, String tradercode, String fullnames, String idno, String dob, String marikitiUserNo, String username, String email, String phoneNo, String address, String county, String constituency, String ward, String salesTarget, String status) {
        this.id = id;
        this.tradercode = tradercode;
        this.fullnames = fullnames;
        this.idno = idno;
        this.dob = dob;
        this.marikitiUserNo = marikitiUserNo;
        this.username = username;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.county = county;
        this.constituency = constituency;
        this.ward = ward;
        this.salesTarget = salesTarget;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
