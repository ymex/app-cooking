package cn.ymex.cooking.app.http;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class T<T> {

    public ObservableTransformer<T, T> transformer() {
        return transformer(null);
    }

    public ObservableTransformer<T, T> transformer(final Consumer<Disposable> onStartCallBack) {
        return new ObservableTransformer<T, T>() {
            Consumer<Disposable> start = onStartCallBack;

            @Override
            public ObservableSource apply(@NonNull Observable upstream) {
                if (start == null) {
                    start = new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                        }
                    };
                }
                return upstream.subscribeOn(Schedulers.io())
                        .doOnSubscribe(start)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
