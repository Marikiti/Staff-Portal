package ics.kenya.com.FieldAgent.Bus;

import java.util.Date;

public class ProcessLoanBus {
    private static ProcessLoanBus single_instance = null;

    private ProcessLoanBus() {
    }

    public static ProcessLoanBus getInstance() {
        if (single_instance == null)
            single_instance = new ProcessLoanBus();

        return single_instance;
    }

    public static ProcessLoanBus getSingle_instance() {
        return single_instance;
    }

    public static void setSingle_instance(ProcessLoanBus single_instance) {
        ProcessLoanBus.single_instance = single_instance;
    }

    private int id;
    private String localID;
    private String tradercode;
    private String name;
    private String shopname;
    private String staffno;
    private String date;
    private String purposeOfLoans;
    private String durationincurrLocation;
    private String yearsinbussiness;
    private String levelofEducation;
    private String status;
    private String names;
    private String phonenumber;
    private String employement;
    private String employername;
    private String nxtkinstatus;
    private String nxtkinnames;
    private String nxtkinphonenumber;
    private String nxtkinemployement;
    private String nxtkinemployername;
    private String refereestatus;
    private String refereenames;
    private String refereephonenumber;
    private String refereeemployement;
    private String refereeemployername;
    private String streetname;
    private String streetno;
    private String hsno;
    private String adrres;

    private String county;
    private String constituency;
    private String ward;
    private String chiefsname;
    private String contacts;
    private String landlordname;
    private String landlordphoneno;
    private String accountname;
    private String bankname;
    private String bankbranch;
    private String accountno;
    private String yearswithbank;
    private String company;
    private String loanamount;
    private String balance;
    private String businessname;
    private String krapin;
    private String typeofbusiness;
    private String countyname;
    private String businessaddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalID() {
        return localID;
    }

    public void setLocalID(String localID) {
        this.localID = localID;
    }

    public String getTradercode() {
        return tradercode;
    }

    public void setTradercode(String tradercode) {
        this.tradercode = tradercode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getStaffno() {
        return staffno;
    }

    public void setStaffno(String staffno) {
        this.staffno = staffno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPurposeOfLoans() {
        return purposeOfLoans;
    }

    public void setPurposeOfLoans(String purposeOfLoans) {
        this.purposeOfLoans = purposeOfLoans;
    }

    public String getDurationincurrLocation() {
        return durationincurrLocation;
    }

    public void setDurationincurrLocation(String durationincurrLocation) {
        this.durationincurrLocation = durationincurrLocation;
    }

    public String getYearsinbussiness() {
        return yearsinbussiness;
    }

    public void setYearsinbussiness(String yearsinbussiness) {
        this.yearsinbussiness = yearsinbussiness;
    }

    public String getLevelofEducation() {
        return levelofEducation;
    }

    public void setLevelofEducation(String levelofEducation) {
        this.levelofEducation = levelofEducation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmployement() {
        return employement;
    }

    public void setEmployement(String employement) {
        this.employement = employement;
    }

    public String getEmployername() {
        return employername;
    }

    public void setEmployername(String employername) {
        this.employername = employername;
    }

    public String getNxtkinstatus() {
        return nxtkinstatus;
    }

    public void setNxtkinstatus(String nxtkinstatus) {
        this.nxtkinstatus = nxtkinstatus;
    }

    public String getNxtkinnames() {
        return nxtkinnames;
    }

    public void setNxtkinnames(String nxtkinnames) {
        this.nxtkinnames = nxtkinnames;
    }

    public String getNxtkinphonenumber() {
        return nxtkinphonenumber;
    }

    public void setNxtkinphonenumber(String nxtkinphonenumber) {
        this.nxtkinphonenumber = nxtkinphonenumber;
    }

    public String getNxtkinemployement() {
        return nxtkinemployement;
    }

    public void setNxtkinemployement(String nxtkinemployement) {
        this.nxtkinemployement = nxtkinemployement;
    }

    public String getNxtkinemployername() {
        return nxtkinemployername;
    }

    public void setNxtkinemployername(String nxtkinemployername) {
        this.nxtkinemployername = nxtkinemployername;
    }

    public String getRefereestatus() {
        return refereestatus;
    }

    public void setRefereestatus(String refereestatus) {
        this.refereestatus = refereestatus;
    }

    public String getRefereenames() {
        return refereenames;
    }

    public void setRefereenames(String refereenames) {
        this.refereenames = refereenames;
    }

    public String getRefereephonenumber() {
        return refereephonenumber;
    }

    public void setRefereephonenumber(String refereephonenumber) {
        this.refereephonenumber = refereephonenumber;
    }

    public String getRefereeemployement() {
        return refereeemployement;
    }

    public void setRefereeemployement(String refereeemployement) {
        this.refereeemployement = refereeemployement;
    }

    public String getRefereeemployername() {
        return refereeemployername;
    }

    public void setRefereeemployername(String refereeemployername) {
        this.refereeemployername = refereeemployername;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public String getStreetno() {
        return streetno;
    }

    public void setStreetno(String streetno) {
        this.streetno = streetno;
    }

    public String getHsno() {
        return hsno;
    }

    public void setHsno(String hsno) {
        this.hsno = hsno;
    }

    public String getAdrres() {
        return adrres;
    }

    public void setAdrres(String adrres) {
        this.adrres = adrres;
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

    public String getChiefsname() {
        return chiefsname;
    }

    public void setChiefsname(String chiefsname) {
        this.chiefsname = chiefsname;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getLandlordname() {
        return landlordname;
    }

    public void setLandlordname(String landlordname) {
        this.landlordname = landlordname;
    }

    public String getLandlordphoneno() {
        return landlordphoneno;
    }

    public void setLandlordphoneno(String landlordphoneno) {
        this.landlordphoneno = landlordphoneno;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankbranch() {
        return bankbranch;
    }

    public void setBankbranch(String bankbranch) {
        this.bankbranch = bankbranch;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getYearswithbank() {
        return yearswithbank;
    }

    public void setYearswithbank(String yearswithbank) {
        this.yearswithbank = yearswithbank;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLoanamount() {
        return loanamount;
    }

    public void setLoanamount(String loanamount) {
        this.loanamount = loanamount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getKrapin() {
        return krapin;
    }

    public void setKrapin(String krapin) {
        this.krapin = krapin;
    }

    public String getTypeofbusiness() {
        return typeofbusiness;
    }

    public void setTypeofbusiness(String typeofbusiness) {
        this.typeofbusiness = typeofbusiness;
    }

    public String getCountyname() {
        return countyname;
    }

    public void setCountyname(String countyname) {
        this.countyname = countyname;
    }

    public String getBusinessaddress() {
        return businessaddress;
    }

    public void setBusinessaddress(String businessaddress) {
        this.businessaddress = businessaddress;
    }
}
