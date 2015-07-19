package paulklauser.gastracker.ui.addcar;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import paulklauser.gastracker.R;

/**
 * Created by Paul on 7/19/2015.
 */
public class PictureConfirmationFragment extends Fragment {

    private ImageView mPicture;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_picture_confirmation, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPicture = (ImageView) view.findViewById(R.id.picture);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPicture.setImageBitmap(((AddCarActivity)getActivity()).mCurrentCar.getPicture());
    }
}
