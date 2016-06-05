package paulklauser.gastracker;

import paulklauser.gastracker.dagger.AppModule;
import paulklauser.gastracker.dagger.DaggerIDatabaseComponent;
import paulklauser.gastracker.dagger.DatabaseModule;
import paulklauser.gastracker.dagger.IDatabaseComponent;
import paulklauser.gastracker.database.CarDataSource;

/**
 * Created by Paul on 6/4/16.
 */
public class MainApplication extends android.app.Application {

    private IDatabaseComponent mDatabaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabaseComponent = DaggerIDatabaseComponent.builder()
                .appModule(new AppModule(this))
                .databaseModule(new DatabaseModule())
                .build();
    }

    public IDatabaseComponent getDatabaseComponent() {
        return mDatabaseComponent;
    }
}
