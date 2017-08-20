package cn.ymex.cooking.app.http;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class T<T> {
    public static AtomicInteger GK = new AtomicInteger(0);
    private WeakReference<Noticeable> noticeable;

    public T() {
        this(null);
    }

    public T(Noticeable noticeable) {
        this.noticeable = new WeakReference<Noticeable>(noticeable);
    }

    /**
     * 尽可能的在链端插入
     *
     * @return
     */
    public ObservableTransformer<T, T> transformer() {
        return new ObservableTransformer<T, T>() {


            @Override
            public ObservableSource apply(@NonNull Observable upstream) {

                return upstream
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (noticeable.get() != null && !noticeable.get().isShow() && noticeable.get().isVisibleToUser()) {
                                    noticeable.get().showNotice();
                                }
                                if (noticeable.get() != null) {
                                    noticeable.get().setDisposable(GK.addAndGet(1), disposable);
                                }
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
