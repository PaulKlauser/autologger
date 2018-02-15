package paulklauser.gastracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import paulklauser.gastracker.utils.BitMapUtils;

/**
 * Created by Paul on 6/13/2015.
 */
public class CarDataSource {

    private static final String DBG_TAG = "CarDataSource";

    private SQLiteDatabase mDatabase;
    private CarDatabaseHelper mDbHelper;
    private String[] allCarColumns = {
            CarDatabaseHelper.COLUMN_ID,
            CarDatabaseHelper.COLUMN_NICK_NAME,
            CarDatabaseHelper.COLUMN_MAKE,
            CarDatabaseHelper.COLUMN_MODEL,
            CarDatabaseHelper.COLUMN_YEAR,
            CarDatabaseHelper.COLUMN_MILES,
            CarDatabaseHelper.COLUMN_PICTURE_PATH };
    private String[] allMileageColumns = {
            CarDatabaseHelper.COLUMN_ID,
            CarDatabaseHelper.COLUMN_DATE,
            CarDatabaseHelper.COLUMN_ODOMETER,
            CarDatabaseHelper.COLUMN_DIFFERENCE,
            CarDatabaseHelper.COLUMN_GALLONS,
            CarDatabaseHelper.COLUMN_CAR_ID };
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

    public List<MileageEntry> getMileageEntries(long carId) {
        List<MileageEntry> entries = new ArrayList<>();

        Cursor cursor = mDatabase.query(CarDatabaseHelper.TABLE_MILEAGE, allMileageColumns, CarDatabaseHelper.COLUMN_CAR_ID
         + " = " + carId, null, null, null, CarDatabaseHelper.COLUMN_DATE + " DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MileageEntry entry = cursorToEntry(cursor);
            entries.add(entry);
            cursor.moveToNext();
        }
        cursor.close();
        return entries;
    }

    public MileageEntry createMileageEntry(long date, int odometer, int miles, double gallons, long carId) {
        ContentValues values = new ContentValues();
        values.put(CarDatabaseHelper.COLUMN_DATE, date);
        values.put(CarDatabaseHelper.COLUMN_ODOMETER, odometer);
        values.put(CarDatabaseHelper.COLUMN_MILES, miles);
        values.put(CarDatabaseHelper.COLUMN_GALLONS, gallons);
        values.put(CarDatabaseHelper.COLUMN_CAR_ID, carId);
        long insertId = mDatabase.insert(CarDatabaseHelper.TABLE_MILEAGE, null, values);
        Cursor cursor = mDatabase.query(CarDatabaseHelper.TABLE_MILEAGE, allMileageColumns, CarDatabaseHelper.COLUMN_ID +
        " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        MileageEntry newEntry = cursorToEntry(cursor);
        cursor.close();
        Log.d(DBG_TAG, "Created mileageEntry with ID: " + insertId);
        return newEntry;
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
        Cursor cursor = mDatabase.query(CarDatabaseHelper.TABLE_CARS, allCarColumns, CarDatabaseHelper.COLUMN_ID +
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
//        Cursor cursor = mDatabase.query(CarDatabaseHelper.TABLE_CARS, allCarColumns, CarDatabaseHelper.COLUMN_ID +
//        " = " + insertId, null, null, null, null);
//        cursor.moveToFirst();

    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();

        Cursor cursor = mDatabase.query(CarDatabaseHelper.TABLE_CARS, allCarColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Car car = cursorToCar(cursor);
            cars.add(car);
            cursor.moveToNext();
        }
        cursor.close();
        return cars;
    }

    public void setPicture(long carId, Uri imageUri) {
        Log.d(DBG_TAG, "setPicturePath with carID: " + carId);
        Cursor cursor = mDatabase.query(CarDatabaseHelper.TABLE_CARS, allCarColumns,
                CarDatabaseHelper.COLUMN_ID + " = " + carId, null, null, null, null);
        cursor.moveToFirst();
        Car car = cursorToCar(cursor);
        cursor.close();
        BitMapUtils.saveVehicleBitmap(mContext, imageUri, carId);
        car.setPicturePath(mContext.getFilesDir().getPath() + "/" + carId + ".png");
        updateCar(car);
    }

    private MileageEntry cursorToEntry(Cursor cursor) {
        MileageEntry entry = new MileageEntry();
        entry.setId(cursor.getLong(0));
        entry.setDateInMillis(cursor.getLong(1));
        entry.setOdometer(cursor.getInt(2));
        entry.setMiles(cursor.getInt(3));
        entry.setGallons(cursor.getInt(4));
        return entry;
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
        //car.setEntryList(getMileageEntries(car.getId()));
        return car;
    }

}
