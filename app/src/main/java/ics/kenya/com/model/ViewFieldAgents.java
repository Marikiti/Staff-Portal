package ics.kenya.com.model;

import java.io.Serializable;

public class ViewFieldAgents implements Serializable {
    private int id;
    private String trader_name;
    private String phone_number;
    private String trader_code;
    private String staff_no;
    private String radio_button;
    private String county;
    private String constituency;
    private String ward;
    private  String total_sales;
    private String sales_target;
    private String number_of_traders;
    private String status;

    public ViewFieldAgents(int id, String trader_name, String phone_number, String trader_code, String staff_no, String radio_button, String county, String constituency, String ward, String total_sales, String sales_target, String number_of_traders, String status) {
        this.id = id;
        this.trader_name = trader_name;
        this.phone_number = phone_number;
        this.trader_code = trader_code;
        this.staff_no = staff_no;
        this.radio_button = radio_button;
        this.county = county;
        this.constituency = constituency;
        this.ward = ward;
        this.total_sales = total_sales;
        this.sales_target = sales_target;
        this.number_of_traders = number_of_traders;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ViewFieldAgents() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrader_name() {
        return trader_name;
    }

    public void setTrader_name(String trader_name) {
        this.trader_name = trader_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getTrader_code() {
        return trader_code;
    }

    public void setTrader_code(String trader_code) {
        this.trader_code = trader_code;
    }

    public String getStaff_no() {
        return staff_no;
    }

    public void setStaff_no(String staff_no) {
        this.staff_no = staff_no;
    }

    public String getRadio_button() {
        return radio_button;
    }

    public void setRadio_button(String radio_button) {
        this.radio_button = radio_button;
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

    public String getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(String total_sales) {
        this.total_sales = total_sales;
    }

    public String getSales_target() {
        return sales_target;
    }

    public void setSales_target(String sales_target) {
        this.sales_target = sales_target;
    }

    public String getNumber_of_traders() {
        return number_of_traders;
    }

    public void setNumber_of_traders(String number_of_traders) {
        this.number_of_traders = number_of_traders;
    }
}
