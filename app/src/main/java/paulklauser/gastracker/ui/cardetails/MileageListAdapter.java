package paulklauser.gastracker.ui.cardetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import paulklauser.gastracker.R;
import paulklauser.gastracker.database.MileageEntry;

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

    private List<MileageEntry> mEntryList;

    public MileageListAdapter(List<MileageEntry> entryList) {
        mEntryList = entryList;
    }

    @Override
    public MileageEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mileage, parent, false);
        return new MileageEntryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MileageEntryViewHolder holder, int position) {
        MileageEntry entry = mEntryList.get(position);
        holder.date.setText(entry.getDate());
        holder.odometer.setText(entry.getOdometer());
        holder.tripLength.setText(entry.getTripLength());
        holder.gallons.setText(entry.getGallons());
//        holder.mpg.setText(entry.getMileage());
    }

    @Override
    public int getItemCount() {
        return mEntryList.size();
    }
}
