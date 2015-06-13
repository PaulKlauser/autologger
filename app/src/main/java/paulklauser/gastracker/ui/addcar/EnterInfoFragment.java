package paulklauser.gastracker.ui.addcar;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;

import paulklauser.gastracker.R;
import paulklauser.gastracker.network.EdmundsHelper;

/**
 * Created by Paul on 6/3/2015.
 */
public class EnterInfoFragment extends Fragment implements EdmundsHelper.LoadMakesListener,
        EdmundsHelper.LoadModelsListener, EdmundsHelper.LoadYearsListener{

    private AutoCompleteTextView mMake;
    private AutoCompleteTextView mModel;
    private AutoCompleteTextView mYear;
    private AddCarActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_car_info, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMake = (AutoCompleteTextView) view.findViewById(R.id.make);
        mModel = (AutoCompleteTextView) view.findViewById(R.id.model);
        mYear = (AutoCompleteTextView) view.findViewById(R.id.year);

        final EdmundsHelper helper = new EdmundsHelper((AddCarActivity) getActivity());

        mMake.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    helper.loadMakesFromWeb(EnterInfoFragment.this);
                }
            }
        });

        mModel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    helper.loadModelsFromWeb(mMake.getText().toString(), EnterInfoFragment.this);
                }
            }
        });

        mYear.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    helper.loadYearsFromWeb(mMake.getText().toString(), mModel.getText().toString(), EnterInfoFragment.this);
                }
            }
        });

        Button next = (Button) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.enterInfoDone(mMake.getText().toString(), mModel.getText().toString(), mYear.getText().toString());
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (AddCarActivity) getActivity();
    }

    @Override
    public void onMakesLoaded(ArrayList<String> carMakes) {
        mMake.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line,
                carMakes));
    }

    @Override
    public void onModelsLoaded(ArrayList<String> carModels) {
        mModel.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line,
                carModels));
    }

    @Override
    public void onYearsLoaded(ArrayList<String> carYears) {
        mModel.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line,
                carYears));
    }
}