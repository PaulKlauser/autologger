package paulklauser.gastracker.ui.cardetails;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import paulklauser.gastracker.R;
import paulklauser.gastracker.database.Car;

/**
 * Created by Paul on 6/4/16.
 */
public class LogMilesFragment extends Fragment {

    private Car mCar; //Probs don't need this

    public static LogMilesFragment newInstance(Car car) {

        Bundle args = new Bundle();
        args.putParcelable("car", car);
        
        LogMilesFragment fragment = new LogMilesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_input_mileage, container, false);
        mCar = getArguments().getParcelable("car");
        Button done = (Button)rootView.findViewById(R.id.done);
        final EditText gallons = (EditText) rootView.findViewById(R.id.gallons);
        final EditText odometer = (EditText) rootView.findViewById(R.id.odometer);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LogMilesListener)getActivity()).milesLoggingDone(Integer.valueOf(odometer.getText().toString()), Double.valueOf(gallons.getText().toString()));
            }
        });
        
        return rootView;
    }
}
