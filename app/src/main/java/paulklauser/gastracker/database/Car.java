package paulklauser.gastracker.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.net.Uri;
import android.util.Log;

import paulklauser.gastracker.utils.BitMapUtils;

/**
 * Created by Paul on 6/13/2015.
 */
public class Car {

    private static final String DBG_TAG = "Car";

    private long mId;
    private String mNickName;
    private String mMake;
    private String mModel;
    private String mYear;
    private int mMiles;
//    private Bitmap mPicture;
    private String mPicturePath;

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

    public Bitmap getPicture() {
        Bitmap bm = BitmapFactory.decodeFile(mPicturePath);
        return BitmapFactory.decodeFile(mPicturePath);
    }

    public String getPicturePath() {
        Log.d(DBG_TAG, "getPicturePath: " + mPicturePath);
        return mPicturePath;
    }

    public void setPicture(String path) {
        //mPicture = picture;
        Log.d(DBG_TAG, "Setting picture path to: " + path);
        mPicturePath = path;
    }
}
