package paulklauser.gastracker.ui.cardetails;

import android.animation.Animator;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import paulklauser.gastracker.R;
import paulklauser.gastracker.database.Car;
import paulklauser.gastracker.ui.BaseActivity;

/**
 * Created by ert34 on 9/28/2015.
 */
public class CarDetailsActivity extends BaseActivity implements LogMilesListener {

    private Car mCar;
    private FloatingActionButton mLogMiles;
    private boolean mShowingStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShowingStats = true; //Need to take into account savedInstanceState stuff
        mCar = getIntent().getParcelableExtra("car");
        setContentView(R.layout.activity_car_details);
        ImageView carImage = (ImageView) findViewById(R.id.car_image);
        carImage.setImageBitmap(BitmapFactory.decodeFile(mCar.getPicturePath()));
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        mLogMiles = (FloatingActionButton) findViewById(R.id.log_miles);

        mLogMiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShowingStats) {
                    showLogging();
                }
            }
        });

        getFragmentManager().beginTransaction().add(R.id.content_container, CarStatsFragment.newInstance(mCar)).commit();
    }

    private void showLogging() {
        hideFab(mLogMiles);
        getFragmentManager().beginTransaction().replace(R.id.content_container, LogMilesFragment.newInstance(mCar)).commit();
        mShowingStats = false;
    }

    private void showStats() {
        showFab(mLogMiles);
        getFragmentManager().beginTransaction().replace(R.id.content_container, CarStatsFragment.newInstance(mCar)).commit();
        mShowingStats = true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(mCar.getYear() + " " + mCar.getMake() + " " + mCar.getModel());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.bringToFront();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("BACK", "BACK");
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hideFab (final FloatingActionButton fab) {
        // Cancel any animation from the default behavior
        fab.animate().cancel();
        fab.animate()
                .scaleX(0f)
                .scaleY(0f)
                .alpha(0f)
                .setDuration(150)
                .setInterpolator(new FastOutLinearInInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //coordinatorLayout.removeView(fab);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
    }

    private void showFab(FloatingActionButton fab) {
//        //int fabSize = getResources().getDimensionPixelSize(R.dimen.fab_size);
//        int margin = getResources().getDimensionPixelSize(R.dimen.fab_margin);
//        final FloatingActionButton fab = new FloatingActionButton(this);
//        fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this R.color.tint)));
//        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.play));
//        CoordinatorLayout.LayoutParams p = new CoordinatorLayout.LayoutParams(fabSize, fabSize);
//        p.rightMargin = p.leftMargin = p.bottomMargin = p.topMargin = margin;
//        p.anchorGravity = Gravity.BOTTOM | Gravity.END;
//        p.setAnchorId(R.id.appbar);
//        fab.setLayoutParams(p);
//        // Start from 1 pixel
//        fab.setAlpha(0f);
//        fab.setScaleX(0f);
//        fab.setScaleY(0f);
//        binding.coordinatorLayout.addView(fab);
        fab.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(150)
                .setInterpolator(new FastOutLinearInInterpolator());

    }

    @Override
    public void milesLoggingDone(int odometer, double gallons) {
        showFab(mLogMiles);
        showStats();

    }
}
