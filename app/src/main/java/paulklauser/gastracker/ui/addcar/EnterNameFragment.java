package paulklauser.gastracker.ui.addcar;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final AutoCompleteTextView make = (AutoCompleteTextView) view.findViewById(R.id.make);

        EdmundsHelper helper = new EdmundsHelper();
        helper.loadMakesFromWeb((AddCarActivity) getActivity(), new EdmundsHelper.EdmundsHelperListener() {
            @Override
            public void onWebRequestComplete(ArrayList<String> carMakes) {
                make.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line,
                        carMakes));
            }
        });
    }

}