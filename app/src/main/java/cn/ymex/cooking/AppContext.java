package cn.ymex.cooking;

import cn.ymex.cooking.app.AppComponent;
import cn.ymex.cooking.app.AppModule;
import cn.ymex.cooking.app.DaggerAppComponent;
import cn.ymex.kits.AppContent;

/**
 * Created by ymexc on 2017/8/17.
 */

public class AppContext extends AppContent {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
