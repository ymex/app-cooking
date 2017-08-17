package cn.ymex.cooking.app.http;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ymexc on 2017/8/17.
 */

public class HttpBox<T, R> {

    private Observable<T> observable;

    private Function<T, R> mapFunction;

    public HttpBox() {
        super();
    }

    public static  HttpBox  build() {
        return new HttpBox();
    }

    public HttpBox<T,R> observable(Observable<T> observable) {
        this.observable = observable;
        return this;
    }


    public void send(final Http.CallBack<R> callBack) {
        if (this.mapFunction == null) {
            this.mapFunction = new Function<T, R>() {
                @Override
                public R apply(@NonNull T t) throws Exception {
                    return (R) t;
                }
            };
        }
        this.observable.subscribeOn(Schedulers.io())
                .map(this.mapFunction)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (callBack != null) {
                            callBack.onStart(disposable);
                        }

                    }
                }).subscribe(new Observer<R>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull R r) {
                if (callBack != null) {
                    callBack.onSuccess(r);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (callBack != null) {
                    callBack.onFail(e);
                    callBack.onFinish();
                }
            }

            @Override
            public void onComplete() {
                if (callBack != null) {
                    callBack.onFinish();
                }
            }
        });

    }

    public void send(final Http.Consumer<R> onSuccess) {
        this.send(null, onSuccess, null, null);
    }

    public void send(final Http.Consumer<R> onSuccess, final Http.Action onFinish) {
        this.send(null, onSuccess, null, onFinish);
    }

    public void send(final Http.Consumer<Disposable> onstart,
                     final Http.Consumer<R> onSuccess,
                     final Http.Consumer<Throwable> onFail,
                     final Http.Action onFinish) {
        Http.CallBack<R> callBack = new Http.CallBack<R>() {
            @Override
            public void onStart(Disposable d) {
                if (onstart != null) {
                    onstart.accept(d);
                }
            }

            @Override
            public void onSuccess(R r) {
                if (onSuccess != null) {
                    onSuccess.accept(r);
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

    //添加线程管理并订阅
    private void toSubscribe(Observable<T> observable, Observer<T> observer){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
