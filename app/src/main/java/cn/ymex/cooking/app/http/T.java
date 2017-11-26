package cn.ymex.cooking.app.http;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public final class T<E> {
    public static AtomicInteger GK = new AtomicInteger(0);
    private WeakReference<Noticeable> noticeable;
    private boolean isShowNotice = true;

    public T() {
        this(null, false);
    }

    public T(Noticeable noticeable) {
        this(noticeable, true);
    }

    public T(Noticeable noticeable, boolean isShowNotice) {
        this.noticeable = new WeakReference<Noticeable>(noticeable);
        this.isShowNotice = isShowNotice;
    }

    public T showNotice(boolean showNotice) {
        this.isShowNotice = showNotice;
        return this;
    }

    private boolean able() {
        return noticeable.get() != null && isShowNotice;
    }

    /**
     * 模式预处理
     *
     * @return
     */
    public ObservableTransformer<E, E> transformer() {
        return new ObservableTransformer<E, E>() {

            @Override
            public ObservableSource apply(@NonNull Observable upstream) {

                return upstream
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (able() && !noticeable.get().isShow() && noticeable.get().isVisibleToUser()) {
                                    noticeable.get().showNotice();
                                }
                                if (noticeable.get() != null) {
                                    noticeable.get().setDisposable(GK.addAndGet(1), disposable);
                                }
                            }
                        })
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                if (able()) {
                                    noticeable.get().dismissNotice();
                                }
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                                if (able()) {
                                    noticeable.get().dismissNotice();
                                }
                            }
                        })
                        .doOnDispose(new Action() {
                            @Override
                            public void run() throws Exception {
                                if (able()) {
                                    noticeable.get().dismissNotice();
                                }
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
