package paulklauser.gastracker.ui.cardetails;

import android.graphics.BitmapFactory;
import android.os.Bundle;
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
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getSupportActionBar().setTitle(mCar.getYear() + " " + mCar.getMake() + " " + mCar.getModel());
    }
}
