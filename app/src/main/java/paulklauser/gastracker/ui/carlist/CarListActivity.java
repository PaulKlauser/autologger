package paulklauser.gastracker.ui.carlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import paulklauser.gastracker.R;

/**
 * Created by Paul on 6/13/2015.
 */
public class CarListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        ListView carList = (ListView) findViewById(R.id.car_list);
    }
}
