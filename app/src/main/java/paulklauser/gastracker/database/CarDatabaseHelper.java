package paulklauser.gastracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Paul on 6/13/2015.
 */
public class CarDatabaseHelper extends SQLiteOpenHelper {

    //Common
    public static final String COLUMN_ID = "_id";

    //Car table
    public static final String TABLE_CARS = "cars";
    public static final String COLUMN_NICK_NAME = "nickname";
    public static final String COLUMN_MAKE = "make";
    public static final String COLUMN_MODEL = "model";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_MILES = "miles";
    public static final String COLUMN_PICTURE_PATH = "picture";

    //Mileage entry table
    public static final String TABLE_MILEAGE = "mileage";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_ODOMETER = "odometer";
    public static final String COLUMN_DIFFERENCE = "miles";
    public static final String COLUMN_GALLONS = "gallons";
    public static final String COLUMN_CAR_ID = "car_id";

    private static final String DATABASE_NAME = "carz.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE_CARS = "create table " + TABLE_CARS + "(" +
            COLUMN_ID + " integer primary key autoincrement, " + COLUMN_NICK_NAME + " text, " + COLUMN_MAKE + " text not null, " +
            COLUMN_MODEL + " text not null, " + COLUMN_YEAR + " text not null, " + COLUMN_MILES + " integer, " +
            COLUMN_PICTURE_PATH + " text)";

    private static final String DATABASE_CREATE_MILEAGE = "create table " + TABLE_MILEAGE + "(" +
            COLUMN_ID + " integer primary key autoincrement, " + COLUMN_DATE + " integer, " + COLUMN_ODOMETER + " integer, " +
            COLUMN_DIFFERENCE + " integer, " + COLUMN_GALLONS + " integer, " + COLUMN_CAR_ID + " integer)";

    public CarDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_CARS);
        db.execSQL(DATABASE_CREATE_MILEAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARS);
        onCreate(db);
    }
}
