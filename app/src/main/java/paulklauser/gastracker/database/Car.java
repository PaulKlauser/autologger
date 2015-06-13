package paulklauser.gastracker.database;

/**
 * Created by Paul on 6/13/2015.
 */
public class Car {


    private long mId;
    private String mName;
    private String mMake;
    private String mModel;
    private String mYear;
    private int mMiles;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = mName;
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
}
