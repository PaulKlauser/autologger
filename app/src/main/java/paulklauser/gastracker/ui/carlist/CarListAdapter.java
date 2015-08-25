package paulklauser.gastracker.ui.carlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
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

    private List<Car> mCars;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView make;
        public TextView model;
        public TextView year;
        public TextView odometer;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            make = (TextView) itemView.findViewById(R.id.make);
            model = (TextView) itemView.findViewById(R.id.model);
            year = (TextView) itemView.findViewById(R.id.year);
            odometer = (TextView) itemView.findViewById(R.id.odometer);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public CarListAdapter(List<Car> cars) {
        mCars = cars;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_car, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Car car = mCars.get(position);
        holder.make.setText(car.getMake());
        holder.model.setText(car.getModel());
        holder.year.setText(car.getYear());
        holder.odometer.setText(String.valueOf(car.getMiles()));
        Bitmap bm = BitmapFactory.decodeFile(car.getPicturePath());
        holder.image.setImageBitmap(bm);
    }


    @Override
    public int getItemCount() {
        return mCars.size();
    }
}
