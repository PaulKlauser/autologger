package paulklauser.gastracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import paulklauser.gastracker.utils.BitMapUtils;

/**
 * Created by Paul on 6/13/2015.
 */
public class CarDataSource {

    private static final String DBG_TAG = "CarDataSource";

    private SQLiteDatabase mDatabase;
    private CarDatabaseHelper mDbHelper;
    private String[] allColumns = {
            CarDatabaseHelper.COLUMN_ID,
            CarDatabaseHelper.COLUMN_NICK_NAME,
            CarDatabaseHelper.COLUMN_MAKE,
            CarDatabaseHelper.COLUMN_MODEL,
            CarDatabaseHelper.COLUMN_YEAR,
            CarDatabaseHelper.COLUMN_MILES,
            CarDatabaseHelper.COLUMN_PICTURE_PATH };
    private Context mContext;

    public CarDataSource(Context context) {
        mDbHelper = new CarDatabaseHelper(context);
        mContext = context;
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Car createCar(String nickName, String make, String model, String year, int miles) {
        ContentValues values = new ContentValues();
        values.put(CarDatabaseHelper.COLUMN_NICK_NAME, nickName);
        values.put(CarDatabaseHelper.COLUMN_MAKE, make);
        values.put(CarDatabaseHelper.COLUMN_MODEL, model);
        values.put(CarDatabaseHelper.COLUMN_YEAR, year);
        values.put(CarDatabaseHelper.COLUMN_MILES, miles);
        values.put(CarDatabaseHelper.COLUMN_PICTURE_PATH, "");
        long insertId = mDatabase.insert(CarDatabaseHelper.TABLE_CARS, null, values);
        Cursor cursor = mDatabase.query(CarDatabaseHelper.TABLE_CARS, allColumns, CarDatabaseHelper.COLUMN_ID +
        " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Car newCar = cursorToCar(cursor);
        cursor.close();
        Log.d(DBG_TAG, "Created car with ID: " + insertId);
        return newCar;
    }

    public void updateCar(Car car) {
        ContentValues values = new ContentValues();
        values.put(CarDatabaseHelper.COLUMN_NICK_NAME, car.getNickName());
        values.put(CarDatabaseHelper.COLUMN_MAKE, car.getMake());
        values.put(CarDatabaseHelper.COLUMN_MODEL, car.getModel());
        values.put(CarDatabaseHelper.COLUMN_YEAR, car.getYear());
        values.put(CarDatabaseHelper.COLUMN_MILES, car.getMiles());
        values.put(CarDatabaseHelper.COLUMN_PICTURE_PATH, car.getPicturePath());
        long insertId = car.getId();
        mDatabase.update(CarDatabaseHelper.TABLE_CARS, values, CarDatabaseHelper.COLUMN_ID + " = " +
        insertId, null);
//        Cursor cursor = mDatabase.query(CarDatabaseHelper.TABLE_CARS, allColumns, CarDatabaseHelper.COLUMN_ID +
//        " = " + insertId, null, null, null, null);
//        cursor.moveToFirst();

    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();

        Cursor cursor = mDatabase.query(CarDatabaseHelper.TABLE_CARS, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Car car = cursorToCar(cursor);
            cars.add(car);
            cursor.moveToNext();
        }
        cursor.close();
        return cars;
    }

    public void setPicture(long carId, Bitmap bitmap) {
        Log.d(DBG_TAG, "setPicturePath with carID: " + carId);
        Cursor cursor = mDatabase.query(CarDatabaseHelper.TABLE_CARS, allColumns,
                CarDatabaseHelper.COLUMN_ID + " = " + carId, null, null, null, null);
        cursor.moveToFirst();
        Car car = cursorToCar(cursor);
        cursor.close();
        BitMapUtils.saveVehicleBitmap(mContext, bitmap, carId);
        car.setPicturePath(mContext.getFilesDir().getPath() + "/" + carId + ".png");
        updateCar(car);
    }

    private Car cursorToCar(Cursor cursor) {
        Car car = new Car();
        car.setId(cursor.getLong(0));
        car.setNickName(cursor.getString(1));
        car.setMake(cursor.getString(2));
        car.setModel(cursor.getString(3));
        car.setYear(cursor.getString(4));
        car.setMiles(cursor.getInt(5));
        car.setPicturePath(cursor.getString(6));
        return car;
    }

}
