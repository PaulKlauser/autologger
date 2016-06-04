package paulklauser.gastracker.ui.cardetails;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import paulklauser.gastracker.R;
import paulklauser.gastracker.database.Car;
import paulklauser.gastracker.ui.BaseActivity;

/**
 * Created by ert34 on 9/28/2015.
 */
public class CarDetailsActivity extends BaseActivity {

    private Car mCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCar = getIntent().getParcelableExtra("car");
        setContentView(R.layout.activity_car_details);
        ImageView carImage = (ImageView) findViewById(R.id.car_image);
        carImage.setImageBitmap(BitmapFactory.decodeFile(mCar.getPicturePath()));
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(mCar.getYear() + " " + mCar.getMake() + " " + mCar.getModel());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.bringToFront();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("BACK", "BACK");
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
