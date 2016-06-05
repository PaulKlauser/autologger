package paulklauser.gastracker.dagger;

import javax.inject.Singleton;

import dagger.Component;
import paulklauser.gastracker.ui.addcar.AddCarActivity;
import paulklauser.gastracker.ui.cardetails.CarDetailsActivity;
import paulklauser.gastracker.ui.cardetails.CarStatsFragment;
import paulklauser.gastracker.ui.carlist.CarListActivity;

/**
 * Created by Paul on 6/4/16.
 */
@Singleton
@Component(modules={AppModule.class, DatabaseModule.class})
public interface IDatabaseComponent {
    void inject(CarListActivity activity);
    void inject(AddCarActivity activity);
    void inject(CarDetailsActivity activity);
    void inject(CarStatsFragment fragment);
}
