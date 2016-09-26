package paulklauser.gastracker.ui;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import paulklauser.gastracker.R;

/**
 * Created by ert34 on 8/24/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("Base", 0);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void setIsFirstLaunch(boolean isFirst) {
        mSharedPreferences.edit().putBoolean("First", isFirst).apply();
    }

    protected boolean isFirstLaunch() {
        return mSharedPreferences.getBoolean("First", true);
    }

}
