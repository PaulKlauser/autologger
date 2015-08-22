package paulklauser.gastracker.ui.addcar;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

import paulklauser.gastracker.R;

/**
 * Created by Paul on 6/13/2015.
 */
public class SelectPictureFragment extends Fragment {

    private AddCarActivity mActivity;
    private static final int SELECT_PHOTO_REQUEST = 100;
    private static final int TAKE_PHOTO_REQUEST = 101;
    private File mPhoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_car_picture, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button next = (Button) view.findViewById(R.id.next);
        Button choosePicture = (Button) view.findViewById(R.id.choose_picture);
        Button takePicture = (Button) view.findViewById(R.id.take_picture);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        choosePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO_REQUEST);
            }
        });

        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
                    //try {
                    try {
                        mPhoto = File.createTempFile(String.valueOf(mActivity.mCurrentCar.getId()) + System.currentTimeMillis(), ".png");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //} catch (IOException e) {
                    //    e.printStackTrace();
                    //}
                    if (mPhoto != null) {
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhoto));
                        startActivityForResult(takePictureIntent, TAKE_PHOTO_REQUEST);
                    }
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO_REQUEST && resultCode == Activity.RESULT_OK) {
            mActivity.mCarDataSource.setPicture(mActivity.mCurrentCar.getId(), data.getData());
            mActivity.selectPictureDone();
            //mActivity.mCurrentCar.setPicture(data.getData());
        } else if (requestCode == TAKE_PHOTO_REQUEST && resultCode == Activity.RESULT_OK) {
            //mActivity.mCurrentCar.setPicture(mActivity, Uri.fromFile(mPhoto));
            mActivity.mCarDataSource.setPicture(mActivity.mCurrentCar.getId(), Uri.fromFile(mPhoto));
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (AddCarActivity) getActivity();
    }
}
