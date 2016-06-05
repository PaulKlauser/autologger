package paulklauser.gastracker.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import paulklauser.gastracker.utils.BitMapUtils;

/**
 * Created by Paul on 6/13/2015.
 */
public class Car implements Parcelable {

    private static final String DBG_TAG = "Car";

    private long mId;
    private String mNickName;
    private String mMake;
    private String mModel;
    private String mYear;
    private int mMiles;
//    private Bitmap mPicture;
    private String mPicturePath;
    private List<MileageEntry> mMileageEntries;

    public Car() {

    }

    public String getNickName() {
        return mNickName;
    }

    public void setNickName(String nickName) {
        mNickName = nickName;
    }

    public String getMake() {
        return mMake;
    }

    public void setMake(String make) {
        mMake = make;
    }

    public String getModel() {
        return mModel;
    }

    public void setModel(String model) {
        mModel = model;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public int getMiles() {
        return mMiles;
    }

    public void setMiles(int miles) {
        mMiles = miles;
    }


    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

//    public Bitmap getPicture() {
//        Bitmap bm = BitmapFactory.decodeFile(mPicturePath);
//        return BitmapFactory.decodeFile(mPicturePath);
//    }

    public String getPicturePath() {
        Log.d(DBG_TAG, "getPicturePath: " + mPicturePath);
        return mPicturePath;
    }

    public void setPicturePath(String path) {
        //mPicture = picture;
        Log.d(DBG_TAG, "Setting picture path to: " + path);
        mPicturePath = path;
    }

    public void setEntryList(List<MileageEntry> entries) {
        mMileageEntries = entries;
    }

    public List<MileageEntry> getMileageEntries() {
        return mMileageEntries;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeStringArray(new String[]{mNickName, mMake, mModel, mYear, mPicturePath});
        dest.writeInt(mMiles);
    }

    protected Car(Parcel in) {
        mId = in.readLong();
        String[] strings = new String[5];
        in.readStringArray(strings);
        mNickName = strings[0];
        mMake = strings[1];
        mModel = strings[2];
        mYear = strings[3];
        mMiles = in.readInt();
        mPicturePath = strings[4];
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}
