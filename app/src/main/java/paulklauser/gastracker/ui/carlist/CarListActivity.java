package paulklauser.gastracker.ui.carlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import paulklauser.gastracker.R;
import paulklauser.gastracker.database.CarDataSource;
import paulklauser.gastracker.ui.BaseActivity;
import paulklauser.gastracker.ui.addcar.AddCarActivity;

/**
 * Created by Paul on 6/13/2015.
 */
public class CarListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        CarDataSource carDataSource = new CarDataSource(this);
        carDataSource.open();
        RecyclerView carList = (RecyclerView) findViewById(R.id.car_list);
        CarListAdapter adapter = new CarListAdapter(carDataSource.getAllCars());
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
}
