package paulklauser.gastracker.ui.addcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import paulklauser.gastracker.R;
import paulklauser.gastracker.database.Car;
import paulklauser.gastracker.database.CarDataSource;

public class AddCarActivity extends AppCompatActivity {

    private CarDataSource mCarDataSource;
    Car mCurrentCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        getFragmentManager()
            .beginTransaction()
            .add(R.id.add_car_frame, new EnterInfoFragment())
            .commit();

        mCarDataSource = new CarDataSource(this);
        mCarDataSource.open();
    }

    public void enterInfoDone(String make, String model, String year) {
        mCurrentCar = mCarDataSource.createCar(null, make, model, year, 0);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.add_car_frame, new SelectPictureFragment())
                .commit();
    }

    public void selectPictureDone() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
