package paulklauser.gastracker.ui.addcar;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import paulklauser.gastracker.R;
import paulklauser.gastracker.network.EdmundsHelper;

/**
 * Created by Paul on 6/3/2015.
 */
public class EnterNameFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_car_info, container, false);
        EdmundsHelper helper = new EdmundsHelper();
        helper.loadMakesFromWeb();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AutoCompleteTextView make = (AutoCompleteTextView) view.findViewById(R.id.make);

    }
}
