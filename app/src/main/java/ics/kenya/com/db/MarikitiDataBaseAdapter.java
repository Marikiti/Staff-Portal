package ics.kenya.com.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import ics.kenya.com.db.DbHelper;
import ics.kenya.com.model.FileReport;
import ics.kenya.com.model.RegisterTrader;
import ics.kenya.com.model.User;


public class MarikitiDataBaseAdapter {
    public static final int NAME_COLUMN = 1;
    // Table Names
    // Database Version
    static final int DATABASE_VERSION = 13;
    public static final String TABLE_USER = "User";
    public static final String TABLE_TRADER_REGISTRATION = "Trader_Registration";
    public static final String TABLE_REGIONAL_TRADER_REGISTRATION = "Regiona_Trader_Registration";
    public static final String TABLE_PROCESS_LOANS = "Process_Loans";
    public static final String TABLE_FILE_REPORT = "File_Trader_Report";
    public static final String TABLE_NEXT_OF_KIN = "Next_Of_Kin";
    public static final String TABLE_MKT_MARITAL_STATUS = "Marital_Status";
    public static final String TABLE_REFEE = "Referee";
    public static final String TABLE_OTHER_LOANS = "Other_Loans";
    public static final String TABLE_OTHER_BUSINESS = "Other_Businesses";
    public static final String TABLE_HOME_ADDRESS = "Home_Address";
    public static final String TABLE_LANDLORD_DETAILS = "Landlord_Details";
    public static final String TABLE_BANK_DETAILS = "Bank_Details";
    public static final String TABLE_CURRENT_LOCATION = "Current_Location";


    private static final String DATABASE_NAME = "dbstaff";


    private static final String TABLE_Itinenary_meter = "itinenary_meter";
    private static final String TABLE_reading_status = "reading_status";
    private static final String TABLE_reading = "reading";
    // Common column names
    private static final String KEY_SERVER_ID = "serverId";
    private static final String KEY_DATE_CREATED_AT = "Date_created_at";

    private static final String KEY_PASSWORD = "password";
    private static final String KEY_LONGITUDE = "Longitude";
    private static final String KEY_LATITUDE = "Latitude";
    private static final String KEY_UPLOADED = "Uploaded";

    // User Table Columns names
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "trader_name";
    private static final String KEY_USER_NUMBER = "trader_number";
    private static final String KEY_USER_PIN = "user_Pin";
    private static final String KEY_CODE_NO = "verification_code";
    private static final String KEY_PHONE_NO = "phone_number";

    //registerTrader
    private static final String KEY_ID = "ID";
    private static final String KEY_APPLICANT_ID = "Applicant_id";
    private static final String KEY_NOK__ID = "Nok_id";
    private static final String KEY_Ols__ID = "Ols_id";
    private static final String KEY_OBS__ID = "OBs_id";
    private static final String KEY_MS__ID = "MS_id";
    private static final String KEY_HAD__ID = "HAD_id";
    private static final String KEY_LLD__ID = "LLD_id";
    private static final String KEY_BD__ID = "BD_id";
    private static final String KEY_REFEREE__ID = "Referee_id";
    private static final String KEY_TRADER_CODE = "trader_code";
    private static final String KEY_FULL_NAMES = "full_names";
    private static final String KEY_ID_NO = "id_number";
    private static final String KEY_DOB = "dob";
    private static final String KEY_MARIKITI_USER_NO = "marikiti_user_no";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_COUNTY = "county";
    private static final String KEY_CONSTITUENCY = "constituency";
    private static final String KEY_WARD = "ward";
    private static final String KEY_SALES_TARGET = "sales_target";
    private static final String KEY_TERMS_CONDITIONS = "terms_and_conditions";
    private static final String KEY_STATUS = "status";

    //process loans table
    private static final String KEY_SHOPNAME = "shopname";
    private static final String KEY_STAFF_NUMBER = "staff_number";
    private static final String KEY_DATE = "date";
    private static final String KEY_PURPOSE = "purpose";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_YEARS_BUSINESS = "years_in_bs";
    private static final String KEY_LEVEL_EDUCATION = "education_level";
    private static final String KEY_APPLICANT_STATUS = "status";
    private static final String KEY_APPLICANT_NAME = "names";
    private static final String KEY_APPLICANT_PHONE_NO = "phone_no";
    private static final String KEY_EMPLOYMENT = "employment";
    private static final String KEY_EMPLOYER_NAME = "employer_name";
    private static final String KEY_NXT_OF_KIN_STATUS = "next_of_kin_status";
    private static final String KEY_NXT_OF_KIN_NAMES = "next_of_kin_Names";
    private static final String KEY_NXT_OF_KIN_PHONE_NO = "next_of_kin_phone_no";
    private static final String KEY_NXT_OF_KIN_EMPLOYMENT = "next_of_kin_employment";
    private static final String KEY_NXT_OF_KIN_EMPLOYER_NAME = "next_of_kin_employer_name";
    private static final String KEY_REFEREE_STATUS = "referee_status";
    private static final String KEY_REFEREE_NAMES = "referee_Names";
    private static final String KEY_REFEREE_PHONE_NO = "referee_phone_no";
    private static final String KEY_REFEREE_EMPLOYMENT = "referee_employment";
    private static final String KEY_REFEREE_EMPLOYER_NAME = "referee_employer_name";
    private static final String KEY_COUNTY_NAME = "county";
    private static final String KEY_CONSTITUENCY_NAME = "constituency";
    private static final String KEY_WARD_NAME = "ward";
    private static final String KEY_CHIEFS_NAME = "chiefs_name";
    private static final String KEY_CONTACTS = "contacts";
    private static final String KEY_LANDLORD_NAME = "landlord_name";
    private static final String KEY_LANDLORD_PHONE_NO = "landlord_phone_no";
    private static final String KEY_ACCOUNT_NAME = "account_name";
    private static final String KEY_BANK_NAME = "bank_name";
    private static final String KEY_BANK_BRANCH = "bank_branch";
    private static final String KEY_ACCOUNT_NO = "account_no";
    private static final String KEY_YEARS_WITH_BANK = "yars_with_bank";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_LOAN_AMOUNT = "loan_amount";
    private static final String KEY_BALANCE = "balance";
    private static final String KEY_BUSNES_NAME = "business_names";
    private static final String KEY_KRA_PIN = "kra_pin";
    private static final String KEY_TYPE_OF_BUS = "type_of_bus";
    private static final String KEY_COUNTYNAME = "county_name";
    private static final String KEY_BUSINESS_ADDRESS = "business_name";


    private static final String KEY_A = "Sales_Target";
    private static final String KEY_B = "Quality_of_Products_Service";
    private static final String KEY_C = "Range_of_Products_Service";
    private static final String KEY_D = "Customer_Service";
    private static final String KEY_E = "Personal_Hygiene";
    private static final String KEY_F = "Training_needs";
    private static final String KEY_G = "Period_on_Location";
    private static final String KEY_H = "Customer_complaints";
    private static final String KEY_I = "Lost_Returned_Goods";
    private static final String KEY_J = "Reported_Fraud";
    private static final String KEY_TOTAL_SCORE = "total_score";
    private static final String KEY_NEW_TARGET = "new_target";

    private static final String KEY_STREETNAME = "streetname";
    private static final String KEY_STREETNO = "streetno";
    private static final String KEY_HSNO = "houseno";
    private static final String KEY_ADDRESS_NO = "addressno";


    // create table sql query
    public static final String CREATE_USER_TABLE = "CREATE TABLE  IF NOT EXISTS " + TABLE_USER + "("
            + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USER_NAME + " TEXT,"
            + KEY_USER_NUMBER + " TEXT,"
            + KEY_PHONE_NO + " TEXT,"
            + KEY_USER_PIN + " TEXT,"
            + KEY_TERMS_CONDITIONS + " TEXT,"
            + KEY_CODE_NO + " TEXT" + ")";

    // create table sql query
    public static final String CREATE_TABLE_PROCESS_LOANS = "CREATE TABLE  IF NOT EXISTS " + TABLE_PROCESS_LOANS + "("
            + KEY_APPLICANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USER_NAME + " TEXT,"
            + KEY_TRADER_CODE + " TEXT,"
            + KEY_SHOPNAME + " TEXT,"
            + KEY_STAFF_NUMBER + " TEXT UNIQUE,"
            + KEY_DATE + " TEXT,"
            + KEY_PURPOSE + " TEXT,"
            + KEY_DURATION + " TEXT,"
            + KEY_YEARS_BUSINESS + " TEXT,"
            + KEY_LEVEL_EDUCATION + " TEXT" + ")";
    // create table sql query

    public static final String CREATE_TABLE_MKT_MARITAL_STATUS = "CREATE TABLE  IF NOT EXISTS " + TABLE_MKT_MARITAL_STATUS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_APPLICANT_ID + " TEXT,"
            + KEY_APPLICANT_STATUS + " TEXT,"
            + KEY_APPLICANT_NAME + " TEXT,"
            + KEY_APPLICANT_PHONE_NO + " TEXT,"
            + KEY_EMPLOYMENT + " TEXT,"
            + KEY_EMPLOYER_NAME + " TEXT" + ")";
    // create table sql query

    public static final String CREATE_TABLE_NEXT_OF_KIN = "CREATE TABLE  IF NOT EXISTS " + TABLE_NEXT_OF_KIN + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_APPLICANT_ID + " INTEGER,"
            + KEY_NXT_OF_KIN_STATUS + " TEXT,"
            + KEY_NXT_OF_KIN_NAMES + " TEXT,"
            + KEY_NXT_OF_KIN_PHONE_NO + " TEXT,"
            + KEY_NXT_OF_KIN_EMPLOYMENT + " TEXT,"
            + KEY_NXT_OF_KIN_EMPLOYER_NAME + " TEXT" + ")";

    public static final String CREATE_TABLE_REFEE = "CREATE TABLE  IF NOT EXISTS " + TABLE_REFEE
            + "(" + KEY_REFEREE__ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_APPLICANT_ID + " INTEGER,"
            + KEY_REFEREE_STATUS + " TEXT,"
            + KEY_REFEREE_NAMES + " TEXT,"
            + KEY_REFEREE_PHONE_NO + " TEXT,"
            + KEY_REFEREE_EMPLOYMENT + " TEXT,"
            + KEY_REFEREE_EMPLOYER_NAME + " TEXT" + ")";

    public static final String CREATE_TABLE_HOME_ADDRESS = "CREATE TABLE  IF NOT EXISTS " + TABLE_HOME_ADDRESS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_APPLICANT_ID + " INTEGER,"
            + KEY_COUNTY_NAME + " TEXT,"
            + KEY_CONSTITUENCY_NAME + " TEXT,"
            + KEY_WARD_NAME + " TEXT,"
            + KEY_CHIEFS_NAME + " TEXT,"
            + KEY_CONTACTS + " TEXT" + ")";

    public static final String CREATE_TABLE_BANK_DETAILS = "CREATE TABLE  IF NOT EXISTS " + TABLE_BANK_DETAILS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_APPLICANT_ID + " INTEGER,"
            + KEY_ACCOUNT_NAME + " TEXT,"
            + KEY_BANK_NAME + " TEXT,"
            + KEY_BANK_BRANCH + " TEXT,"
            + KEY_ACCOUNT_NO + " TEXT,"
            + KEY_YEARS_WITH_BANK + " TEXT" + ")";

    public static final String CREATE_TABLE_OTHER_LOANS = "CREATE TABLE  IF NOT EXISTS " + TABLE_OTHER_LOANS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_APPLICANT_ID + " INTEGER,"
            + KEY_COMPANY + " TEXT,"
            + KEY_LOAN_AMOUNT + " TEXT,"
            + KEY_BALANCE + " TEXT" + ")";

    public static final String CREATE_TABLE_TABLE_CURRENT_LOCATION = "CREATE TABLE  IF NOT EXISTS " + TABLE_CURRENT_LOCATION
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_APPLICANT_ID + " INTEGER,"
            + KEY_STREETNAME + " TEXT,"
            + KEY_STREETNO + " TEXT,"
            + KEY_HSNO + " TEXT,"
            + KEY_LOAN_AMOUNT + " TEXT,"
            + KEY_ADDRESS_NO + " TEXT" + ")";

    public static final String CREATE_TABLE_LANDLORD_DETAILS = "CREATE TABLE  IF NOT EXISTS " + TABLE_LANDLORD_DETAILS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_APPLICANT_ID + " INTEGER,"
            + KEY_LANDLORD_NAME + " TEXT,"
            + KEY_LANDLORD_PHONE_NO + " TEXT" + ")";

    public static final String CREATE_TABLE_OTHER_BUSINESS = "CREATE TABLE  IF NOT EXISTS " + TABLE_OTHER_BUSINESS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_APPLICANT_ID + " INTEGER,"
            + KEY_BUSNES_NAME + " TEXT,"
            + KEY_KRA_PIN + " TEXT,"
            + KEY_TYPE_OF_BUS + " TEXT,"
            + KEY_COUNTYNAME + " TEXT,"
            + KEY_BUSINESS_ADDRESS + " TEXT" + ")";
    // create table sql query

    public static final String CREATE_TABLE_TRADER_REGISTRATION = "CREATE TABLE  IF NOT EXISTS "
            + TABLE_TRADER_REGISTRATION + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TRADER_CODE + " TEXT,"
            + KEY_FULL_NAMES + " TEXT,"
            + KEY_ID_NO + " TEXT,"
            + KEY_DOB + " TEXT,"
            + KEY_MARIKITI_USER_NO + " TEXT,"
            + KEY_USERNAME + " TEXT,"
            + KEY_EMAIL + " TEXT,"
            + KEY_PHONE_NUMBER + " TEXT,"
            + KEY_ADDRESS + " TEXT,"
            + KEY_COUNTY + " TEXT,"
            + KEY_CONSTITUENCY + " TEXT,"
            + KEY_WARD + " TEXT,"
            + KEY_SALES_TARGET + " TEXT,"
            + KEY_STATUS + " TEXT" + ")";


    public static final String CREATE_TABLE_REGIONAL_TRADER_REGISTRATION = "CREATE TABLE  IF NOT EXISTS " + TABLE_REGIONAL_TRADER_REGISTRATION
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TRADER_CODE + " TEXT,"
            + KEY_FULL_NAMES + " TEXT,"
            + KEY_ID_NO + " TEXT,"
            + KEY_DOB + " TEXT,"
            + KEY_MARIKITI_USER_NO + " TEXT,"
            + KEY_USERNAME + " TEXT,"
            + KEY_EMAIL + " TEXT,"
            + KEY_PHONE_NUMBER + " TEXT,"
            + KEY_ADDRESS + " TEXT,"
            + KEY_COUNTY + " TEXT,"
            + KEY_CONSTITUENCY + " TEXT,"
            + KEY_WARD + " TEXT,"
            + KEY_SALES_TARGET + " TEXT,"
            + KEY_STATUS + " TEXT" + ")";

    // create table sql query
    public static final String CREATE_TABLE_FILE_REPORT = "CREATE TABLE  IF NOT EXISTS " + TABLE_FILE_REPORT + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USER_NAME + " TEXT,"
            + KEY_TRADER_CODE + " TEXT,"
            + KEY_SHOPNAME + " TEXT,"
            + KEY_STAFF_NUMBER + " TEXT,"
            + KEY_DATE + " TEXT,"
            + KEY_A + " TEXT,"
            + KEY_B + " TEXT,"
            + KEY_C + " TEXT,"
            + KEY_D + " TEXT,"
            + KEY_E + " TEXT,"
            + KEY_F + " TEXT,"
            + KEY_G + " TEXT,"
            + KEY_H + " TEXT,"
            + KEY_I + " TEXT,"
            + KEY_J + " TEXT,"
            + KEY_TOTAL_SCORE + " TEXT,"
            + KEY_NEW_TARGET + " TEXT" + ")";

    // create table sql query


    //the user table
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Database open/upgrade helper
    private static DbHelper dbHelper;
    String ok = "OK";
    // Context of the application using the database.
    private Context context = null;

    public MarikitiDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //local brand Stocked

    // Method to openthe Database
    public MarikitiDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;


    }

    // Method to close the Database
    public void close() {

        db.close();
    }

    // method returns an Instance of the Database
    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public long insertmaritalstatus(int applicant_id, String status,
                                    String names, String phone_no,
                                    String employment, String employer_name) {
        db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_APPLICANT_ID, applicant_id);
        values.put(KEY_APPLICANT_STATUS, status);
        values.put(KEY_APPLICANT_NAME, names);
        values.put(KEY_APPLICANT_PHONE_NO, phone_no);
        values.put(KEY_EMPLOYMENT, employment);
        values.put(KEY_EMPLOYER_NAME, employer_name);

        // insert row
        // long app_id = db.insertWithOnConflict(TABLE_MKT_MARITAL_STATUS, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        long app_id = db.insert(TABLE_MKT_MARITAL_STATUS, null, values);
        db.close();
        return app_id;
    }

    public long insertcurrentlocation(int applicant_id, String streetname, String streetno, String hsno, String adres) {
        db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_APPLICANT_ID, applicant_id);
        values.put(KEY_STREETNAME, streetname);
        values.put(KEY_STREETNO, streetno);
        values.put(KEY_HSNO, hsno);
        values.put(KEY_ADDRESS_NO, adres);
        // insert row
        // long app_id = db.insertWithOnConflict(TABLE_MKT_MARITAL_STATUS, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        long app_id = db.insert(TABLE_CURRENT_LOCATION, null, values);
        db.close();
        return app_id;
    }

    public long insertlandlorddetails(int applicant_id, String name,
                                      String phoneno) {
        db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_APPLICANT_ID, applicant_id);
        values.put(KEY_LANDLORD_NAME, name);
        values.put(KEY_LANDLORD_PHONE_NO, phoneno);

        // insert row
        long app_id = db.insert(TABLE_LANDLORD_DETAILS, null, values);

        return applicant_id;
    }

    public long insertNextofkin(int applicant_id, String status,
                                String kin_names, String kin_phone_no,
                                String employment, String employer_name) {
        db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_APPLICANT_ID, applicant_id);
        values.put(KEY_NXT_OF_KIN_STATUS, status);
        values.put(KEY_NXT_OF_KIN_NAMES, kin_names);
        values.put(KEY_NXT_OF_KIN_PHONE_NO, kin_phone_no);
        values.put(KEY_NXT_OF_KIN_EMPLOYMENT, employment);
        values.put(KEY_NXT_OF_KIN_EMPLOYER_NAME, employer_name);

        // insert row
        long app_id = db.insert(TABLE_NEXT_OF_KIN, null, values);

        return applicant_id;
    }

    public long insertbankdetails(int applicant_id, String accnt_name,
                                  String bank_name, String bank_branch,
                                  String accnt_no, String years_bank) {
        db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_APPLICANT_ID, applicant_id);
        values.put(KEY_ACCOUNT_NAME, accnt_name);
        values.put(KEY_BANK_NAME, bank_name);
        values.put(KEY_BANK_BRANCH, bank_branch);
        values.put(KEY_ACCOUNT_NO, accnt_no);
        values.put(KEY_YEARS_WITH_BANK, years_bank);

        // insert row
        long app_id = db.insert(TABLE_BANK_DETAILS, null, values);

        return app_id;
    }

    public long insertReferee(int applicant_id, String status,
                              String kin_names, String kin_phone_no,
                              String employment, String employer_name) {
        db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_APPLICANT_ID, applicant_id);
        values.put(KEY_REFEREE_STATUS, String.valueOf(status));
        values.put(KEY_REFEREE_NAMES, String.valueOf(kin_names));
        values.put(KEY_REFEREE_PHONE_NO, String.valueOf(kin_phone_no));
        values.put(KEY_REFEREE_EMPLOYMENT, String.valueOf(employment));
        values.put(KEY_REFEREE_EMPLOYER_NAME, employer_name);

        // insert row
        long app_id = db.insert(TABLE_REFEE, null, values);

        return app_id;
    }

    public long inserthomeaddress(int applicant_id, String county,
                                  String constituency, String ward,
                                  String chiefsname, String contacts) {
        db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_APPLICANT_ID, applicant_id);
        values.put(KEY_COUNTY_NAME, county);
        values.put(KEY_CONSTITUENCY_NAME, constituency);
        values.put(KEY_WARD_NAME, ward);
        values.put(KEY_CHIEFS_NAME, chiefsname);
        values.put(KEY_CONTACTS, contacts);

        // insert row
        long app_id = db.insert(TABLE_HOME_ADDRESS, null, values);

        return app_id;
    }

    public long insertOtherLoans(int applicant_id, String company,
                                 String loan_amount, String balance) {
        db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_APPLICANT_ID, applicant_id);
        values.put(KEY_COMPANY, String.valueOf(company));
        values.put(KEY_LOAN_AMOUNT, String.valueOf(loan_amount));
        values.put(KEY_BALANCE, String.valueOf(balance));

        // insert row
        long app_id = db.insert(TABLE_OTHER_LOANS, null, values);

        return app_id;
    }

    public long insertOtherBusiness(int applicant_id, String business_name,
                                    String kra_pin, String type_of_bus,
                                    String county, String address) {
        db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_APPLICANT_ID, applicant_id);
        values.put(KEY_BUSNES_NAME, String.valueOf(business_name));
        values.put(KEY_KRA_PIN, String.valueOf(kra_pin));
        values.put(KEY_TYPE_OF_BUS, String.valueOf(type_of_bus));
        values.put(KEY_COUNTYNAME, String.valueOf(county));
        values.put(KEY_BUSINESS_ADDRESS, String.valueOf(address));

        // insert row
        long app_id = db.insert(TABLE_OTHER_BUSINESS, null, values);

        return app_id;
    }

    public void addUser(User user) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getName());
        values.put(KEY_USER_NUMBER, user.getIdno());
        values.put(KEY_PHONE_NO, user.getPhoneNumber());
        values.put(KEY_USER_PIN, user.getPin());
        values.put(KEY_TERMS_CONDITIONS, user.getTermscontions());
        values.put(KEY_CODE_NO, user.getVerificationcode());


        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void registterTrader(RegisterTrader registerTrader) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TRADER_CODE, registerTrader.getTradercode());
        values.put(KEY_FULL_NAMES, registerTrader.getFullnames());
        values.put(KEY_ID_NO, registerTrader.getIdno());
        values.put(KEY_DOB, registerTrader.getDob());
        values.put(KEY_MARIKITI_USER_NO, registerTrader.getMarikitiUserNo());
        values.put(KEY_USERNAME, registerTrader.getUsername());
        values.put(KEY_EMAIL, registerTrader.getEmail());
        values.put(KEY_PHONE_NUMBER, registerTrader.getPhoneNo());
        values.put(KEY_ADDRESS, registerTrader.getAddress());
        values.put(KEY_COUNTY, "Nairobi");
        values.put(KEY_CONSTITUENCY, "Starehe");
        values.put(KEY_WARD, "Kamukunji");
        values.put(KEY_SALES_TARGET, "200,000");
        values.put(KEY_STATUS, "ACTIVE");


        // Inserting Row
        db.insert(TABLE_TRADER_REGISTRATION, null, values);
        db.close();
    }

    public long insertfilereport(FileReport fileReport) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_USER_NAME, fileReport.getUsername());
        values.put(KEY_TRADER_CODE, fileReport.getTradercode());
        values.put(KEY_SHOPNAME, fileReport.getShopname());
        values.put(KEY_STAFF_NUMBER, fileReport.getStaffno());
        values.put(KEY_DATE, fileReport.getDate());
        values.put(KEY_A, fileReport.getA());
        values.put(KEY_B, fileReport.getB());
        values.put(KEY_C, fileReport.getC());
        values.put(KEY_D, fileReport.getD());
        values.put(KEY_E, fileReport.getE());
        values.put(KEY_F, fileReport.getF());
        values.put(KEY_G, fileReport.getG());
        values.put(KEY_H, fileReport.getH());
        values.put(KEY_I, fileReport.getI());
        values.put(KEY_J, fileReport.getJ());
        values.put(KEY_TOTAL_SCORE, fileReport.getJ());
        values.put(KEY_NEW_TARGET, fileReport.getNewtarget());

        // insert row
        long firptID = db.insertWithOnConflict(TABLE_FILE_REPORT, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        System.out.println("firptID" + firptID);
        //closeDB();
        closeDB();
        return firptID;

    }

    public long processloan(String uname, String shopname,
                            String staffno, String date, String purpose,
                            String duration, String yearsInBus,
                            String educationLevel) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_USER_NAME, uname);
        values.put(KEY_SHOPNAME, shopname);
        values.put(KEY_STAFF_NUMBER, staffno);
        values.put(KEY_DATE, date);
        values.put(KEY_PURPOSE, purpose);
        values.put(KEY_DURATION, duration);
        values.put(KEY_YEARS_BUSINESS, yearsInBus);
        values.put(KEY_LEVEL_EDUCATION, educationLevel);

        // Inserting Row
        long appl_id = db.insertWithOnConflict(TABLE_PROCESS_LOANS, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        return appl_id;
    }

    public void regionalTraderRegistration(RegisterTrader registerTrader) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TRADER_CODE, registerTrader.getTradercode());
        values.put(KEY_FULL_NAMES, registerTrader.getFullnames());
        values.put(KEY_ID_NO, registerTrader.getIdno());
        values.put(KEY_DOB, registerTrader.getDob());
        values.put(KEY_MARIKITI_USER_NO, registerTrader.getMarikitiUserNo());
        values.put(KEY_USERNAME, registerTrader.getUsername());
        values.put(KEY_EMAIL, registerTrader.getEmail());
        values.put(KEY_PHONE_NUMBER, registerTrader.getPhoneNo());
        values.put(KEY_ADDRESS, registerTrader.getAddress());
        values.put(KEY_COUNTY, registerTrader.getCounty());
        values.put(KEY_CONSTITUENCY, registerTrader.getConstituency());
        values.put(KEY_WARD, registerTrader.getWard());
        values.put(KEY_SALES_TARGET, registerTrader.getSalesTarget());
        values.put(KEY_STATUS, "INACTIVE");

        // Inserting Row
        db.insert(TABLE_REGIONAL_TRADER_REGISTRATION, null, values);
        db.close();
    }


    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                KEY_USER_ID,
                KEY_USER_NUMBER,
                KEY_USER_NAME,
                KEY_USER_PIN
        };
        // sorting orders
        String sortOrder =
                KEY_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        db = dbHelper.getWritableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,id_number,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
                user.setIdno(cursor.getString(cursor.getColumnIndex(KEY_USER_NUMBER)));
                user.setPin(cursor.getString(cursor.getColumnIndex(KEY_USER_PIN)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT full_names, phone_number, trader_code,status FROM " + TABLE_TRADER_REGISTRATION;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("full_names", cursor.getString(cursor.getColumnIndex(KEY_FULL_NAMES)));
            user.put("phone_number", cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER)));
            user.put("trader_code", cursor.getString(cursor.getColumnIndex(KEY_TRADER_CODE)));
            user.put("status", cursor.getString(cursor.getColumnIndex(KEY_STATUS)));
            userList.add(user);
        }
        return userList;
    }

    // Update User Details
    public int UpdateUserDetails(String status, int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(KEY_STATUS, status);
        int count = db.update(TABLE_REGIONAL_TRADER_REGISTRATION, val, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        return count;
    }

    // code to get all TraderList in a list view
    public List getAllTraders() {
        List registerTraderList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_REGIONAL_TRADER_REGISTRATION;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                RegisterTrader registerTrader = new RegisterTrader();
                registerTrader.setId(Integer.parseInt(cursor.getString(0)));
                registerTrader.setFullnames(cursor.getString(1));
                registerTrader.setPhoneNo(cursor.getString(2));
                registerTrader.setTradercode(cursor.getString(3));
                registerTrader.setStatus(cursor.getString(4));
                registerTraderList.add(registerTrader);
            } while (cursor.moveToNext());
        }
        return registerTraderList;
    }

    public List<RegisterTrader> getRegisterTraderList() {
        List<RegisterTrader> registertraderList = new ArrayList<RegisterTrader>();
        RegisterTrader td = new RegisterTrader();
        // td.setName("- Required -");
        // String selectQuery = "SELECT  * FROM " + TABLE_REGIONAL_TRADER_REGISTRATION + " where " + KEY_STATUS + " =" + "'ACTIVE'";
        String selectQuery = "SELECT  * FROM " + TABLE_TRADER_REGISTRATION;

        db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list AFA_PD_Inspect_Processor_Commercial Nursery Operator Farm Inspection
        try {
            if (c.moveToFirst()) {
                do {
                    td = new RegisterTrader();

                    td.setLocalID(c.getString((c.getColumnIndex(KEY_ID))));
                    td.setFullnames((c.getString((c.getColumnIndex(KEY_FULL_NAMES)))));
                    td.setPhoneNo((c.getString((c.getColumnIndex(KEY_PHONE_NO)))));
                    td.setTradercode((c.getString((c.getColumnIndex(KEY_TRADER_CODE)))));
                    td.setStatus((c.getString((c.getColumnIndex(KEY_STATUS)))));
                    td.setCounty((c.getString((c.getColumnIndex(KEY_COUNTY)))));
                    td.setConstituency((c.getString((c.getColumnIndex(KEY_CONSTITUENCY)))));
                    td.setWard((c.getString((c.getColumnIndex(KEY_WARD)))));
                    td.setSalesTarget((c.getString((c.getColumnIndex(KEY_SALES_TARGET)))));
                    td.setStatus((c.getString((c.getColumnIndex(KEY_STATUS)))));
                    // adding to itinerary list
                    registertraderList.add(td);
                } while (c.moveToNext());
            }
        } finally {
            if (c != null)
                c.close();
            // closeDB();
            // RIGHT: ensure resource is always recovered
        }
        System.out.println("Retrieving data from db: " + registertraderList.size());
        return registertraderList;
    }

    public List<FileReport> getfiledreportfortrader() {
        List<FileReport> fileReportList = new ArrayList<FileReport>();
        FileReport td = new FileReport();
        // td.setName("- Required -");
        // String selectQuery = "SELECT  * FROM " + TABLE_REGIONAL_TRADER_REGISTRATION + " where " + KEY_STATUS + " =" + "'ACTIVE'";
        String selectQuery = "SELECT  * FROM " + TABLE_FILE_REPORT;

        db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        try {
            if (c.moveToFirst()) {
                do {
                    td = new FileReport();

                    td.setLocalId(c.getString((c.getColumnIndex(KEY_ID))));
                    td.setUsername((c.getString((c.getColumnIndex(KEY_USER_NAME)))));
                    td.setTradercode((c.getString((c.getColumnIndex(KEY_TRADER_CODE)))));
                    td.setShopname((c.getString((c.getColumnIndex(KEY_SHOPNAME)))));
                    td.setStaffno((c.getString((c.getColumnIndex(KEY_STAFF_NUMBER)))));
                    td.setDate((c.getString((c.getColumnIndex(KEY_DATE)))));
                    td.setA((c.getString((c.getColumnIndex(KEY_A)))));
                    td.setB((c.getString((c.getColumnIndex(KEY_B)))));
                    td.setC((c.getString((c.getColumnIndex(KEY_C)))));
                    td.setD((c.getString((c.getColumnIndex(KEY_D)))));
                    td.setE((c.getString((c.getColumnIndex(KEY_E)))));
                    td.setF((c.getString((c.getColumnIndex(KEY_F)))));
                    td.setG((c.getString((c.getColumnIndex(KEY_G)))));
                    td.setH((c.getString((c.getColumnIndex(KEY_H)))));
                    td.setI((c.getString((c.getColumnIndex(KEY_I)))));
                    td.setJ((c.getString((c.getColumnIndex(KEY_J)))));
                    td.setTotalscore((c.getString((c.getColumnIndex(KEY_TOTAL_SCORE)))));
                    td.setNewtarget((c.getString((c.getColumnIndex(KEY_NEW_TARGET)))));
                    // adding to itinerary list
                    fileReportList.add(td);
                } while (c.moveToNext());
            }
        } finally {
            if (c != null)
                c.close();
            // closeDB();
            // RIGHT: ensure resource is always recovered
        }
        System.out.println("Retrieving data from db: " + fileReportList.size());
        return fileReportList;
    }

    // code to update the single contact
    public boolean updateRegisterTrader(int id, String status) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_STATUS, status);
        db.update(TABLE_TRADER_REGISTRATION, contentValues, KEY_ID + " = ? ", new String[]{String.valueOf(id)});

        return true;
    }

    // Getting Trader Count
    public int getTraderCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_TRADER_REGISTRATION;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null && !cursor.isClosed()) {
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getName());
        values.put(KEY_USER_NUMBER, user.getIdno());
        values.put(KEY_PHONE_NO, user.getPhoneNumber());
        values.put(KEY_USER_PIN, user.getPin());
        values.put(KEY_CODE_NO, user.getVerificationcode());

        // updating row
        db.update(TABLE_USER, values, KEY_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        db = dbHelper.getWritableDatabase();

        // delete user record by id
        db.delete(TABLE_USER, KEY_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public boolean checkUser(String phonenumber) {

        // array of columns to fetch
        String[] columns = {
                KEY_USER_ID
        };
        db = dbHelper.getWritableDatabase();

        // selection criteria
        String selection = KEY_PHONE_NO + " = ?";

        // selection argument
        String[] selectionArgs = {phonenumber};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE id_number = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                KEY_USER_ID
        };
        db = dbHelper.getWritableDatabase();

        // selection criteria
        String selection = KEY_PHONE_NO + " = ?" + " AND " + KEY_USER_PIN + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE id_number = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        return cursorCount > 0;
    }


    //end of the crop details
    // closing database
    public void closeDB() {
        db = dbHelper.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void onCreate() {
        db = dbHelper.getReadableDatabase();

        // creating required tables
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TABLE_TRADER_REGISTRATION);
        db.execSQL(CREATE_TABLE_REGIONAL_TRADER_REGISTRATION);
        db.execSQL(CREATE_TABLE_PROCESS_LOANS);
        db.execSQL(CREATE_TABLE_FILE_REPORT);
        db.execSQL(CREATE_TABLE_MKT_MARITAL_STATUS);
        db.execSQL(CREATE_TABLE_NEXT_OF_KIN);
        db.execSQL(CREATE_TABLE_REFEE);
        db.execSQL(CREATE_TABLE_OTHER_LOANS);
        db.execSQL(CREATE_TABLE_OTHER_BUSINESS);
        db.execSQL(CREATE_TABLE_HOME_ADDRESS);
        db.execSQL(CREATE_TABLE_LANDLORD_DETAILS);
        db.execSQL(CREATE_TABLE_BANK_DETAILS);


        // db.execSQL(CREATE_TABLE_TAG); db.execSQL(CREATE_TABLE_TODO_TAG);

    }

    public void onUpgrade(int oldVersion, int newVersion) {
        // on upgrade drop older tables

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRADER_REGISTRATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGIONAL_TRADER_REGISTRATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROCESS_LOANS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILE_REPORT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MKT_MARITAL_STATUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEXT_OF_KIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REFEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OTHER_LOANS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OTHER_BUSINESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOME_ADDRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LANDLORD_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BANK_DETAILS);


        // create new tables
        onCreate();
    }

}