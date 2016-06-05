package paulklauser.gastracker.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import paulklauser.gastracker.database.CarDataSource;

/**
 * Created by Paul on 6/4/16.
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    CarDataSource provideDataSource(Application application) {
        return new CarDataSource(application);
    }

}
