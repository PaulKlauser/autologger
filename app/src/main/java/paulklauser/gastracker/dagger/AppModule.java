package paulklauser.gastracker.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Paul on 6/4/16.
 */
@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication  = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

}
