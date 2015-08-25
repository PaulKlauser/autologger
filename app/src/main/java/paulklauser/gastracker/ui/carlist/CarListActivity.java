package paulklauser.gastracker.ui.carlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import paulklauser.gastracker.R;
import paulklauser.gastracker.database.CarDataSource;

/**
 * Created by Paul on 6/13/2015.
 */
public class CarListActivity extends AppCompatActivity {

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
}
