package ics.kenya.com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Called when no database exists in disk and the helper class needs
    // to create a new one.
    @Override
    public void onCreate(SQLiteDatabase _db) {
        try {
            System.out.println("Database being created");

            _db.execSQL(MarikitiDataBaseAdapter.CREATE_USER_TABLE);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_TRADER_REGISTRATION);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_REGIONAL_TRADER_REGISTRATION);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_PROCESS_LOANS);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_FILE_REPORT);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_MKT_MARITAL_STATUS);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_NEXT_OF_KIN);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_REFEE);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_OTHER_LOANS);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_OTHER_BUSINESS);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_HOME_ADDRESS);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_LANDLORD_DETAILS);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_BANK_DETAILS);
            _db.execSQL(MarikitiDataBaseAdapter.CREATE_TABLE_TABLE_CURRENT_LOCATION);


        } catch (Exception er) {

            Log.e("Error", "exceptioin");
        }

    }

    @Override
    public void onOpen(SQLiteDatabase _db){
        onCreate(_db);
    }

    // Called when there is a database version mismatch meaning that the version
    // of the database on disk needs to be upgraded to the current version.
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        System.out.println("Database being upgraded");
        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to " + _newVersion + ", which will destroy all old data");


        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        //_db.execSQL("DROP TABLE IF EXISTS " + "LOGIN");
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_USER);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_TRADER_REGISTRATION);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_REGIONAL_TRADER_REGISTRATION);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_PROCESS_LOANS);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_FILE_REPORT);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_MKT_MARITAL_STATUS);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_NEXT_OF_KIN);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_REFEE);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_OTHER_LOANS);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_OTHER_BUSINESS);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_HOME_ADDRESS);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_LANDLORD_DETAILS);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_BANK_DETAILS);
        _db.execSQL("DROP TABLE IF EXISTS " + MarikitiDataBaseAdapter.TABLE_CURRENT_LOCATION);


        // Create a new one.
        onCreate(_db);
    }


}
