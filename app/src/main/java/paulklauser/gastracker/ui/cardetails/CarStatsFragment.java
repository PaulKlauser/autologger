package paulklauser.gastracker.ui.cardetails;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import paulklauser.gastracker.R;

/**
 * Created by Paul on 6/4/16.
 */
public class CarStatsFragment extends Fragment {

    public static CarStatsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CarStatsFragment fragment = new CarStatsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_car_stats, container, false);

        return rootView;
    }
}
