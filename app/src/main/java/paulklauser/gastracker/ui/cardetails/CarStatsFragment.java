package paulklauser.gastracker.ui.cardetails;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import javax.inject.Inject;

import paulklauser.gastracker.MainApplication;
import paulklauser.gastracker.R;
import paulklauser.gastracker.database.Car;
import paulklauser.gastracker.database.CarDataSource;
import paulklauser.gastracker.database.MileageEntry;

/**
 * Created by Paul on 6/4/16.
 */
public class CarStatsFragment extends Fragment {

    private RecyclerView mMileageList;
    private Car mCar;

    @Inject
    CarDataSource mCarDataSource;

    public static CarStatsFragment newInstance(Car car) {
        
        Bundle args = new Bundle();
        args.putParcelable("car", car);
        
        CarStatsFragment fragment = new CarStatsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainApplication)getActivity().getApplication()).getDatabaseComponent().inject(this);
        View rootView = inflater.inflate(R.layout.fragment_car_stats, container, false);
        mCar = getArguments().getParcelable("car");
        mMileageList = rootView.findViewById(R.id.mileage_list);
        mMileageList.setAdapter(new MileageListAdapter(mCarDataSource.getMileageEntries(mCar.getId())));
        mMileageList.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        return rootView;
    }
}
