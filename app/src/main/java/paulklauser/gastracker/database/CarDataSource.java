package paulklauser.gastracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 6/13/2015.
 */
public class CarDataSource {

    private SQLiteDatabase mDatabase;
    private CarDatabaseHelper mDbHelper;
    private String[] allColumns = {
            CarDatabaseHelper.COLUMN_ID,
            CarDatabaseHelper.COLUMN_NICK_NAME,
            CarDatabaseHelper.COLUMN_MAKE,
            CarDatabaseHelper.COLUMN_MODEL,
            CarDatabaseHelper.COLUMN_YEAR,
            CarDatabaseHelper.COLUMN_MILES };

    public CarDataSource(Context context) {
        mDbHelper = new CarDatabaseHelper(context);
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
        long insertId = mDatabase.insert(CarDatabaseHelper.TABLE_CARS, null, values);
        Cursor cursor = mDatabase.query(CarDatabaseHelper.TABLE_CARS, allColumns, CarDatabaseHelper.COLUMN_ID +
        " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Car newCar = cursorToCar(cursor);
        cursor.close();
        return newCar;
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

    private Car cursorToCar(Cursor cursor) {
        Car car = new Car();
        car.setId(cursor.getLong(0));
        car.setNickName(cursor.getString(1));
        car.setMake(cursor.getString(2));
        car.setModel(cursor.getString(3));
        car.setYear(cursor.getString(4));
        car.setMiles(cursor.getInt(5));
        return car;
    }

}
