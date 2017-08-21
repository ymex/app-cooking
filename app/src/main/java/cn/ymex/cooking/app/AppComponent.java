package cn.ymex.cooking.app;

import javax.inject.Singleton;

import cn.ymex.cooking.AppContext;
import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by ymexc on 2017/8/17.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Retrofit getRetrofit();
    AppContext getAppcontext();
}
