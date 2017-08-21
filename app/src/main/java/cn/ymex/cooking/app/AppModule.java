package cn.ymex.cooking.app;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import cn.ymex.cooking.AppContext;
import cn.ymex.cooking.config.Constant;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ymexc on 2017/8/17.
 */

@Module
public class AppModule {

    private AppContext appContext;

    public AppModule(AppContext context) {
        this.appContext = context;
    }


    @Singleton
    @Provides
    public AppContext provideContext() {
        return appContext;
    }


    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(Constant.APIURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
