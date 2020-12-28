package com.example.parking;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.parking.models.Parking_places;
import com.example.parking.models.Reservation;

import java.util.ArrayList;
import java.util.List;


public class MyDatabase extends SQLiteOpenHelper{
    private static final int DB_VERSION = 16;
    private static final String DB_NAME = "Users";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_REG = "registration";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private static final String TABLE_Cities = "cities";
    private static final String CITY_ID = "id";
    private static final String CITY  = "city";
    private static final String CITY_IMAGE  = "city_image";

    private static final String TABLE_Parking = "parking";

    private static final String TABLE_Reservation = "Reservation";
    private static final String RESERVATION_ID = "id";
    private static final String USER_NAME = "username";
    private static final String CITY_NAME = "name";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String PARKING_NAME = "parking_name";
    public MyDatabase(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE1 = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT,"
                + KEY_REG + " TEXT,"
                + KEY_NUMBER + " TEXT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT"+")";
        db.execSQL(CREATE_TABLE1);
        String CREATE_TABLE2 = "CREATE TABLE " + TABLE_Reservation + "("
                + RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME + " TEXT,"
                + CITY_NAME + " TEXT,"
                + DATE + " TEXT,"
                + TIME + " TEXT,"
                + PARKING_NAME + " TEXT"+")";
        db.execSQL(CREATE_TABLE2);
        String CREATE_TABLE3 = "CREATE TABLE " + TABLE_Cities + "(" +
                CITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CITY + " TEXT,"
                + CITY_IMAGE + " INTEGER"+")";
        db.execSQL(CREATE_TABLE3);
        db.execSQL("create table " + TABLE_Parking +"(id INTEGER PRIMARY KEY AUTOINCREMENT, parking_name VARCHAR, city_name VARCHAR, total INTEGER, latitude VARCHAR, longitude VARCHAR)");
        ContentValues content = new ContentValues();
        db.execSQL("insert into " + TABLE_Parking +"(parking_name, city_name, total, latitude, longitude)" + "VALUES" + "('Гумење', 'Крушево', '40', '41.3749', '21.2469')");
        db.execSQL("insert into " + TABLE_Parking +"(parking_name, city_name, total, latitude, longitude)" + "VALUES" + "('Хотел Монтана', 'Крушево', '15', '41.36472902315995', '21.254052405480305')");
        db.execSQL("insert into " + TABLE_Parking +"(parking_name, city_name, total, latitude, longitude)" + "VALUES" + "('Центар', 'Крушево', '30', '41.36747105220081', '21.248615375489152')");
        db.execSQL("insert into " + TABLE_Parking +"(parking_name, city_name, total, latitude, longitude)" + "VALUES" + "('Билјанини извори', 'Охрид', '50', '41.118129659351986', '20.799025584248476')");
        db.execSQL("insert into " + TABLE_Parking +"(parking_name, city_name, total, latitude, longitude)" + "VALUES" + "('Долни Сарај', 'Охрид', '70', '41.11216364669871', '20.7935464692106')");
        db.execSQL("insert into " + TABLE_Parking +"(parking_name, city_name, total, latitude, longitude)" + "VALUES" + "('Центар', 'Охрид', '90', '41.112345979608044', '20.799561882700804')");
        db.execSQL("insert into " + TABLE_Parking +"(parking_name, city_name, total, latitude, longitude)" + "VALUES" + "('Метропол', 'Охрид', '100', '41.05753069106218', '20.80250771153563')");
        db.execSQL("insert into " + TABLE_Parking +"(parking_name, city_name, total, latitude, longitude)" + "VALUES" + "('City mall', 'Скопје', '120', '42.00444251022524', '21.391722526912165')");
        db.execSQL("insert into " + TABLE_Parking +"(parking_name, city_name, total, latitude, longitude)" + "VALUES" + "('Beverly Hills', 'Скопје', '90', '41.9948982490793', '21.415804484400432')");
        db.execSQL("insert into " + TABLE_Parking +"(parking_name, city_name, total, latitude, longitude)" + "VALUES" + "('Центар', 'Скопје', '150', '41.9927253032982', '21.436346026911895')");
        db.execSQL("insert into " + TABLE_Parking +"(parking_name, city_name, total, latitude, longitude)" + "VALUES" + "('Веро Џамбо', 'Скопје', '200', '41.9929570750663', '21.44197981713434')");

        //db.execSQL("create table " + TABLE_Reservation+"(id INTEGER PRIMARY KEY AUTOINCREMENT, user_name VARCHAR, city_name VARCHAR, date VARCHAR, time VARCHAR, parking_name VARCHAR)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Reservation);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Cities);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Parking);
        // Create tables again
        onCreate(db);
    }
    void insertUserDetails(String first_name, String last_name, String registration, String number, String username, String password){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys

            ContentValues cValues = new ContentValues();
            cValues.put(KEY_FIRST_NAME, first_name);
            cValues.put(KEY_LAST_NAME, last_name);
            cValues.put(KEY_REG, registration);
            cValues.put(KEY_NUMBER, number);
            cValues.put(KEY_USERNAME, username);
            cValues.put(KEY_PASSWORD, password);
            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(TABLE_Users, null, cValues);
            db.close();
    }

    public boolean checkUser(String username, String password){
        // array of columns to fetch
        String[] columns = {
                KEY_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = KEY_USERNAME + " = ?" + " AND " + KEY_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {username, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_Users, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    public void insertReservationDetails(String username, String city_name, String date, String time, String parking_name){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(USER_NAME, username);
        cValues.put(CITY_NAME, city_name);
        cValues.put(DATE, date);
        cValues.put(TIME, time);
        cValues.put(PARKING_NAME, parking_name);

        long newRowId = db.insert(TABLE_Reservation, null, cValues);
        db.close();
    }

    public int getNumberOfReservations(String date, String time, String name) {
        SQLiteDatabase database = this.getReadableDatabase();
        try{
            onCreate(database);
        } catch (Exception e){
            e.printStackTrace();
        }
        int count = 0;
        Cursor c1 = database.rawQuery(" SELECT * FROM " + TABLE_Reservation + " WHERE DATE = '" + date + "' AND TIME = '" + time + "' AND PARKING_NAME = '" + name + "'" , null);
        if(c1.moveToFirst()) {
            count = c1.getCount();
            c1.close();
            return count;
        } else {
            return 0;
        }
    }

    public int getTotalSpaces(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            onCreate(db);
        } catch (Exception e){
            e.printStackTrace();
        }
        int total = 0;
        String query ="SELECT * FROM " + TABLE_Parking + " WHERE parking_name=?";
        Cursor c1 = db.rawQuery(query,  new String[]{name});
        if(c1.moveToFirst()) {
            total = c1.getInt(3);
            c1.close();
            return total;
        } else {
            return 0;
        }
    }
    public List<Parking_places> getAllParkingPlaces(String city) {

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Parking_places> returnList = new ArrayList<>();

        String query = "SELECT * FROM "+ TABLE_Parking + " WHERE city_name=?";

        if(city!=null) {
            Cursor cursor = db.rawQuery(query, new String[]{city});

            while (cursor.moveToNext()) {
                int parkingId = cursor.getInt(0);
                String parkingName = cursor.getString(1);
                int total = cursor.getInt(3);
                String latitude = cursor.getString(4);
                String longitude = cursor.getString(5);

                Parking_places parking =
                        new Parking_places(parkingId, parkingName, city, total, latitude, longitude);
                returnList.add(parking);
            }
            cursor.close();
            db.close();
        }
        return returnList;
    }
    public int numberResPerUser(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" Select * from " + TABLE_Reservation + " where " + USER_NAME + "=?", new String[]{user});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getCount();
            cursor.close();
            return count;
        }
        else return 0;
    }
    public List<Reservation> getAllReservations() {

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Reservation> returnList = new ArrayList<>();

        String query = " SELECT * FROM "+ TABLE_Reservation;
            Cursor cursor = db.rawQuery(query, new String[]{});

            while (cursor.moveToNext()) {
                String city = cursor.getString(2);
                String parkingName = cursor.getString(5);
                String date = cursor.getString(3);
                String time = cursor.getString(4);

                Reservation reservations=
                        new Reservation (city, parkingName, date, time);
                returnList.add(reservations);
            }
            cursor.close();
            db.close();

        return returnList;
    }

}
