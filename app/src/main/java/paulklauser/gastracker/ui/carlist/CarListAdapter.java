package paulklauser.gastracker.ui.carlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import paulklauser.gastracker.R;
import paulklauser.gastracker.database.Car;

/**
 * Created by Paul on 6/13/2015.
 */
public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {

    private static final String DBG_TAG = "CarListAdapter";

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { //TODO:Needs to implement on click?
        public TextView make;
        public TextView model;
        public TextView year;
        public TextView odometer;
        public ImageView image;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            make = (TextView) itemView.findViewById(R.id.make);
            model = (TextView) itemView.findViewById(R.id.model);
            year = (TextView) itemView.findViewById(R.id.year);
            odometer = (TextView) itemView.findViewById(R.id.odometer);
            image = (ImageView) itemView.findViewById(R.id.image);
            cardView = (CardView) itemView.findViewById(R.id.car_card);
        }

        @Override
        public void onClick(View v) {
            //TODO: PK 2/12/18 - Probably don't need this
        }
    }

    interface CarListAdapterListener {
        void onCarClicked(int carIndex);
    }

    private List<Car> mCars;
    private CarListAdapterListener mListener;

    public CarListAdapter(List<Car> cars, CarListAdapterListener listener) {
        mCars = cars;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_car, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Car car = mCars.get(position);
        holder.make.setText(car.getMake());
        holder.model.setText(car.getModel());
        holder.year.setText(car.getYear());
        holder.odometer.setText(String.valueOf(car.getMiles()) + " Miles");
        Bitmap bm = BitmapFactory.decodeFile(car.getPicturePath());
        holder.image.setImageBitmap(bm);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(DBG_TAG, "Car Clicked!");
                mListener.onCarClicked(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mCars.size();
    }
}
