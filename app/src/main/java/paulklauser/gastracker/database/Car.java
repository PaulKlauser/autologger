package paulklauser.gastracker.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.net.Uri;

import paulklauser.gastracker.utils.BitMapUtils;

/**
 * Created by Paul on 6/13/2015.
 */
public class Car {

    private long mId;
    private String mNickName;
    private String mMake;
    private String mModel;
    private String mYear;
    private int mMiles;
    private Bitmap mPicture;

    public String getNickName() {
        return mNickName;
    }

    public void setNickName(String name) {
        mNickName = mNickName;
    }

    public String getMake() {
        return mMake;
    }

    public void setMake(String make) {
        mMake = mMake;
    }

    public String getModel() {
        return mModel;
    }

    public void setModel(String model) {
        mModel = mModel;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = mYear;
    }

    public int getMiles() {
        return mMiles;
    }

    public void setMiles(int miles) {
        mMiles = mMiles;
    }


    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public Bitmap getPicture() {
        return mPicture;
    }

    public void setPicture(Context context, Uri bitmapUri) {
        mPicture = BitMapUtils.saveVehicleBitmap(context, bitmapUri, mId);
    }
}
