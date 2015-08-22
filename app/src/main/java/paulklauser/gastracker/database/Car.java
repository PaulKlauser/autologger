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
        return mPicture;
    }

    public String getPicturePath() {
        return mPicturePath;
    }

    public void setPicture(Bitmap picture, String path) {
        mPicture = picture;
        mPicturePath = path;
    }
}
