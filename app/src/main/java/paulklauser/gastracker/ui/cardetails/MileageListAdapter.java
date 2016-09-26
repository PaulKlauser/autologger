package paulklauser.gastracker.ui.cardetails;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import paulklauser.gastracker.R;

/**
 * Created by ert34 on 9/26/2016.
 */
public class MileageListAdapter extends RecyclerView.Adapter<MileageListAdapter.MileageEntryViewHolder> {

    public static class MileageEntryViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView odometer;
        TextView tripLength;
        TextView gallons;
        TextView mpg;

        public MileageEntryViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            odometer = (TextView) itemView.findViewById(R.id.odometer);
            tripLength = (TextView) itemView.findViewById(R.id.trip_length);
            gallons = (TextView) itemView.findViewById(R.id.gallons);
            mpg = (TextView) itemView.findViewById(R.id.mpg);
        }
    }


    @Override
    public MileageEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MileageEntryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
