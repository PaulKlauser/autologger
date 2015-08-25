package paulklauser.gastracker.ui.addcar;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import paulklauser.gastracker.R;

/**
 * Created by Paul on 7/19/2015.
 */
public class PictureConfirmationFragment extends Fragment {

    private ImageView mPicture;
    private Button mYes;
    private Button mNo;
    private AddCarActivity mActivity;
    private Uri mImageUri;

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
        mYes = (Button) view.findViewById(R.id.yes);
        mNo = (Button) view.findViewById(R.id.no);

        mYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.selectPictureConfirmed(mImageUri);
            }
        });

        mNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (AddCarActivity)getActivity();
        Log.d("PictureFrag", "Setting dat imageView");
        Intent intent = (Intent) getArguments().get("Intent");
        mImageUri = intent.getData();
        mPicture.setImageURI(mImageUri);
    }
}
