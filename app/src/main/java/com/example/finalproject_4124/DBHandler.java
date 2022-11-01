package com.example.finalproject_4124;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "NEW_DB" ;
    private static final int DB_VERSION = 2;

    private static final String VEH_DETAILS = "VehicleDetails";
    private static final String BOOKING_DETAILS = "BookingDetails";
    private static final String USER_TYPE = "UserType";
    private static final String USER_DETAILS = "UserDetails";

    private static final String VID = "vid";
    private static final String VEHICLE_NAME = "vehiclename";
    private static final String VEHICLE_NUMBER = "vehiclenumber";
    private static final String BOOKING_STATUS = "bookingStatus";

    private static final String B_ID = "bid";
    private static final String B_USER_ID = "buserid";
    private static final String B_VEHICLE_ID = "bvehicleid";
    private static final String B_DATE = "bdate";
    private static final String B_TIME = "btime";
    private static final String B_LATITUDE = "blatitude";
    private static final String B_LONGITUDE = "blongitude";


    private static final String USER_TYPE_ID = "usertypeid";
    private static final String USER_ROLE = "userrole";

    private static final String USER_ID = "userid";
    private static final String USER_DETAILS_TYPE = "userdetailstype";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phonenumber";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String vdquery = "CREATE TABLE " + VEH_DETAILS + "("
                + VID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VEHICLE_NAME + " TEXT,"
                + VEHICLE_NUMBER + " TEXT,"
                + BOOKING_STATUS + " BOOLEAN)";
        sqLiteDatabase.execSQL(vdquery);


        String bdquery = "CREATE TABLE " + BOOKING_DETAILS + "("
                + B_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + B_USER_ID + " INTEGER,"
                + B_VEHICLE_ID + " TEXT,"
                + B_DATE + " DATE,"
                + B_TIME + " TIME,"
                + B_LATITUDE + " DOUBLE,"
                + B_LONGITUDE + " DOUBLE)";
        sqLiteDatabase.execSQL(bdquery);


        String userTypequery = "CREATE TABLE " + USER_TYPE + "("
                + USER_TYPE_ID + " INTEGER PRIMARY KEY, "
                + USER_ROLE + " TEXT)";
        sqLiteDatabase.execSQL(userTypequery);


        String userDetailsquery = "CREATE TABLE " + USER_DETAILS + "("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_DETAILS_TYPE + " INTEGER,"
                + NAME + " TEXT,"
                + EMAIL + " TEXT,"
                + PHONE_NUMBER + " TEXT,"
                + USERNAME + " TEXT,"
                + PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(userDetailsquery);

    }

    public Cursor viewBookings(){
        SQLiteDatabase db = this.getWritableDatabase();
        // db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        Cursor res = db.rawQuery("select * from " +BOOKING_DETAILS,null);
        return res;
    }
    public Cursor getVehicleNames(){
        SQLiteDatabase db = this.getWritableDatabase();
        // db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        Cursor res = db.rawQuery("select * from " +VEH_DETAILS,null);
        return res;
    }

    public void dropTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+USER_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS "+BOOKING_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS "+VEH_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS "+USER_TYPE);
    }

    public void addTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String bdquery = "CREATE TABLE " + BOOKING_DETAILS + "("
                + B_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + B_USER_ID + " INTEGER,"
                + B_VEHICLE_ID + " TEXT,"
                + B_DATE + " DATE,"
                + B_TIME + " TIME,"
                + B_LATITUDE + " DOUBLE,"
                + B_LONGITUDE + " DOUBLE)";
        db.execSQL(bdquery);


        String vdquery = "CREATE TABLE " + VEH_DETAILS + "("
                + VID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VEHICLE_NAME + " TEXT,"
                + VEHICLE_NUMBER + " TEXT,"
                + BOOKING_STATUS + " BOOLEAN)";
        db.execSQL(vdquery);


        String userTypequery = "CREATE TABLE " + USER_TYPE + "("
                + USER_TYPE_ID + " INTEGER PRIMARY KEY, "
                + USER_ROLE + " TEXT)";
        db.execSQL(userTypequery);

        String userDetailsquery = "CREATE TABLE " + USER_DETAILS + "("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_DETAILS_TYPE + " INTEGER,"
                + NAME + " TEXT,"
                + EMAIL + " TEXT,"
                + PHONE_NUMBER + " TEXT,"
                + USERNAME + " TEXT,"
                + PASSWORD + " TEXT)";
        db.execSQL(userDetailsquery);
    }

public void addAdminData(){

    SQLiteDatabase db = this.getWritableDatabase();
    //creating a variable for content values.
    ContentValues values = new ContentValues();

    //passing all values along with its key and value pair
   // values.put(USER_TYPE_ID,0);
    values.put(USER_ROLE,"Admin");
    db.insert(USER_TYPE, null, values);

  //  values.put(USER_TYPE_ID,1);
    values.put(USER_ROLE,"User");
    db.insert(USER_TYPE, null, values);

    db.close();

}

public void insertAdminDetails(){
    SQLiteDatabase db = this.getWritableDatabase();

    //creating a variable for content values.
    ContentValues values = new ContentValues();

    //passing all values along with its key and value pair
    values.put(USER_DETAILS_TYPE,1);
    values.put(NAME, "admin");
    values.put(EMAIL, "admin@bookaride.com");
    values.put(PHONE_NUMBER, "7056326276");
    values.put(USERNAME, "admin");
    values.put(PASSWORD, "admin");

    //passing content values to table.
    db.insert(USER_DETAILS, null, values);

    db.close();
}

    public void insertvehicleDetails(){
        SQLiteDatabase db = this.getWritableDatabase();

        //creating a variable for content values.
        ContentValues values = new ContentValues();




        //passing all values along with its key and value pair
        values.put(VEHICLE_NAME,"Chevrolet Cobalt");
        values.put(VEHICLE_NUMBER, "CA 38594");


        //passing content values to table.
        db.insert(VEH_DETAILS, null, values);

        db.close();
    }




    public void addUserDetails(String name, String email, String phonenum, String username, String password) {

        //creating a variable for database and calling writable method for  writing data in to database.
        SQLiteDatabase db = this.getWritableDatabase();

        //creating a variable for content values.
        ContentValues values = new ContentValues();

        //passing all values along with its key and value pair
        values.put(USER_DETAILS_TYPE,2);
        values.put(NAME, name);
        values.put(EMAIL, email);
        values.put(PHONE_NUMBER, phonenum);
        values.put(USERNAME, username);
        values.put(PASSWORD, password);

        //passing content values to table.
        db.insert(USER_DETAILS, null, values);

        db.close();

    }

    public void addBookingDetails(Integer userLoginId,String mname, String date, String time, String latitude, String longitude) {

        //creating a variable for database and calling writable method for  writing data in to database.
        SQLiteDatabase db = this.getWritableDatabase();

        //creating a variable for content values.
        ContentValues values = new ContentValues();

        //passing all values along with its key and value pair
        values.put(B_USER_ID,userLoginId);
        values.put(B_VEHICLE_ID, mname);
        values.put(B_DATE, date);
        values.put(B_TIME, time);
        values.put(B_LATITUDE, latitude);
        values.put(B_LONGITUDE, longitude);

        //values.put(PASSWORD, password);

        //passing content values to table.
        db.insert(BOOKING_DETAILS, null, values);

        db.close();

    }


    public Cursor getdetails(int id1){
        SQLiteDatabase db = this.getWritableDatabase();
        //System.out.println(id1);
        int id = id1+1;
        Cursor res = db.rawQuery("select * from " +BOOKING_DETAILS+ " where bid = " + id ,null);
        // query issue fixed
        return res;
    }

    public Cursor getLat(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor lat = db.rawQuery("select * from " +BOOKING_DETAILS+ " where bid = " + id ,null);
        return lat;
    }
    public Cursor getLong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor lon = db.rawQuery("select * from " +BOOKING_DETAILS+ " where bid = " + id ,null);
        return lon;
    }

    public void add_veh_details(String vehname, String vehnumber) {

        //creating a variable for database and calling writable method for  writing data in to database.
        SQLiteDatabase db = this.getWritableDatabase();

        //creating a variable for content values.
        ContentValues values = new ContentValues();

        //passing all values along with its key and value pair
        values.put(VEHICLE_NAME, vehname);
        values.put(VEHICLE_NUMBER, vehnumber);

        //passing content values to table.
        db.insert(VEH_DETAILS, null, values);

        db.close();

    }


    public Cursor getLoginID(String tfusername, String tfpassword){
        int userdetailstype = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM UserDetails WHERE username = ? AND password = ?", new String[] {tfusername, tfpassword});
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        if (i == 1 && i1 == 2) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + VEH_DETAILS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BOOKING_DETAILS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_DETAILS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TYPE);
            onCreate(sqLiteDatabase);
        }
    }
}