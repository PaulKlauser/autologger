package paulklauser.gastracker.ui.cardetails;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import paulklauser.gastracker.R;

/**
 * Created by Paul on 6/4/16.
 */
public class LogMilesFragment extends Fragment {

    public static LogMilesFragment newInstance() {

        Bundle args = new Bundle();
        
        LogMilesFragment fragment = new LogMilesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_input_mileage, container, false);
        Button done = (Button)rootView.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LogMilesListener)getActivity()).milesLoggingDone(0, 0);
            }
        });
        
        return rootView;
    }
}
