package cn.ymex.cooking.app.http;

import cn.ymex.cooking.app.AppContext;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by ymexc on 2017/8/17.
 */

public class HttpBox<T, R> {

    private Observable<T> observable;

    private Function<T, R> mapFunction;

    private Retrofit retrofit = AppContext.getAppComponent().getRetrofit();

    private HttpBox(Observable<T> observable) {
        this.observable = observable;
    }

    public static HttpBox build(Observable o) {
        return new HttpBox(o);

    }

    public HttpBox create(final Class<T> service){
        retrofit.create()
        return this;
    }


    public void send(final Http.CallBack<T> callBack) {

        if (this.mapFunction == null) {
            this.mapFunction = new Function<T, R>() {
                @Override
                public R apply(@NonNull T t) throws Exception {
                    return (R) t;
                }
            };
        }
        observable.subscribeOn(Schedulers.io())
                .map(mapFunction)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (callBack != null) {
                            callBack.onStart(disposable);
                        }

                    }
                })
                .subscribe(new Observer<R>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull R r) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void send(final Http.Consumer<T> onSuccess) {
        this.send(null, onSuccess, null, null);
    }

    public void send(final Http.Consumer<T> onSuccess, final Http.Action onFinish) {
        this.send(null, onSuccess, null, onFinish);
    }

    public void send(final Http.Consumer<Disposable> onstart,
                     final Http.Consumer<T> onSuccess,
                     final Http.Consumer<Throwable> onFail,
                     final Http.Action onFinish) {
        Http.CallBack<T> callBack = new Http.CallBack<T>() {
            @Override
            public void onStart(Disposable d) {
                if (onstart != null) {
                    onstart.accept(d);
                }
            }

            @Override
            public void onSuccess(T t) {
                if (onSuccess != null) {
                    onSuccess.accept(t);
                }
            }

            @Override
            public void onFail(Throwable e) {
                if (onFail != null) {
                    onFail.accept(e);
                }
            }

            @Override
            public void onFinish() {
                if (onFinish != null) {
                    onFinish.run();
                }
            }
        };
        this.send(callBack);
    }
}
