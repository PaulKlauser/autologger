package paulklauser.gastracker.ui.carlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import paulklauser.gastracker.MainApplication;
import paulklauser.gastracker.R;
import paulklauser.gastracker.dagger.DatabaseModule;
import paulklauser.gastracker.database.Car;
import paulklauser.gastracker.database.CarDataSource;
import paulklauser.gastracker.ui.BaseActivity;
import paulklauser.gastracker.ui.addcar.AddCarActivity;
import paulklauser.gastracker.ui.cardetails.CarDetailsActivity;

/**
 * Created by Paul on 6/13/2015.
 */
public class CarListActivity extends BaseActivity implements CarListAdapter.CarListAdapterListener {

    @Inject
    CarDataSource mCarDataSource;

    private List<Car> mCars;
    private static final String DBG_TAG = "CarListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication)getApplication()).getDatabaseComponent().inject(this);
        setContentView(R.layout.activity_car_list);
        //CarDataSource carDataSource = new CarDataSource(this);
        mCarDataSource.open();
        RecyclerView carList = (RecyclerView) findViewById(R.id.car_list);
        mCars = mCarDataSource.getAllCars();
        CarListAdapter adapter = new CarListAdapter(mCars, this);
        carList.setAdapter(adapter);
        carList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (getIntent().getAction().equals("Initialized")) {
            setIsFirstLaunch(false);
        } else if (isFirstLaunch()) {
            Intent intent = new Intent(this, AddCarActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_car) {
            Intent intent = new Intent(this, AddCarActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCarClicked(int carIndex) {
        Log.d(DBG_TAG, "onCarClicked");
        Intent intent = new Intent(this, CarDetailsActivity.class);
        intent.putExtra("car", mCars.get(carIndex));
        startActivity(intent);
    }
}
