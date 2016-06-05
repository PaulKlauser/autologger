package paulklauser.gastracker.database;

import java.util.Calendar;

/**
 * Created by Paul on 6/4/16.
 */
public class MileageEntry {

    private long mId;
    private long mDateInMillis;
    private int mOdometer;
    private int mMiles;
    private double mGallons;

    public MileageEntry() {

    }

    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mDateInMillis);
        return calendar.get(Calendar.MONTH) + "/" +
                calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                calendar.get(Calendar.YEAR);
    }

    public String getOdometer() {
        return String.valueOf(mOdometer);
    }

    public String getGallons() {
        return String.valueOf(mGallons);
    }

    public String getMileage() {
        return String.valueOf(mMiles/mGallons);
    }

    public void setDateInMillis(long dateInMillis) {
        mDateInMillis = dateInMillis;
    }

    public void setOdometer(int odometer) {
        mOdometer = odometer;
    }

    public void setMiles(int miles) {
        mMiles = miles;
    }

    public void setGallons(double gallons) {
        mGallons = gallons;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String toString() {
        return getDate() + " " + mOdometer + " " + mGallons;
    }
}
