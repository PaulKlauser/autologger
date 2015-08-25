package paulklauser.gastracker.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Paul on 7/19/2015.
 */
public class BitMapUtils {

    private static final String DBG_TAG = "BitMapUtils";

    public static Bitmap getScaledBitmap(Context context, Uri bitmapUri) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(bitmapUri), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 140;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(bitmapUri), null, o2);
    }

    public static Bitmap saveVehicleBitmap(Context context, Uri bitmapUri, long vehicleId) {
        OutputStream outputStream = null;
        Bitmap bitmap= null;
        try {
            //InputStream inputStream = context.getContentResolver().openInputStream(bitmapUri);
            bitmap = getScaledBitmap(context, bitmapUri);
            //InputStream inputStream = bitmap.get

            outputStream = context.openFileOutput(String.valueOf(vehicleId) + ".png", Context.MODE_PRIVATE);

            Log.d(DBG_TAG, String.valueOf(bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)));
//            byte buffer[] = new byte[1024];
//            int length = 0;
//
//            while ((length = inputStream.read(buffer)) > 0) {
//                outputStream.write(buffer, 0, length);
//            }
//
//            outputStream.close();
//            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}
