package paulklauser.gastracker.ui.carlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Paul on 6/13/2015.
 */
public class CarListAdapter extends RecyclerView.Adapter {

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView model;
        private TextView make;
        private TextView year;
        private TextView odometer;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
