package paulklauser.gastracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Paul on 6/13/2015.
 */
public class CarDatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_CARS = "cars";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MAKE = "make";
    public static final String COLUMN_MODEL = "model";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_MILES = "miles";

    private static final String DATABASE_NAME = "carz.db";
    private static final int DATABSE_VERSION = 1;
    private static final String DATABASE_CREATE = "create table " + TABLE_CARS + "(" +
            COLUMN_ID + " integer primary key autoincrement, " + COLUMN_MAKE + " text not null " +
            COLUMN_MODEL + " text not null " + COLUMN_YEAR + " text not null);";

    public CarDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARS);
        onCreate(db);
    }
}
