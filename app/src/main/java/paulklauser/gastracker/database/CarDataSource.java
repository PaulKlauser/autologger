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
    private String[] allColumns = { CarDatabaseHelper.COLUMN_ID, CarDatabaseHelper.COLUMN_MAKE,
        CarDatabaseHelper.COLUMN_MODEL, CarDatabaseHelper.COLUMN_YEAR };

    public CarDataSource(Context context) {
        mDbHelper = new CarDatabaseHelper(context);
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Car createCar(String make, String model, String year) {
        ContentValues values = new ContentValues();
        values.put(CarDatabaseHelper.COLUMN_MAKE, make);
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
        car.setName(cursor.getString(1));
        car.setMake(cursor.getString(2));
        car.setModel(cursor.getString(3));
        car.setYear(cursor.getString(4));
        return car;
    }

}
