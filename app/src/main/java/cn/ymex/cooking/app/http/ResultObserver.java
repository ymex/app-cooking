package cn.ymex.cooking.app.http;

import java.lang.ref.WeakReference;

import io.reactivex.annotations.NonNull;

public class ResultObserver<T> extends ResponseObserver<T> {

    private WeakReference<Noticeable> noticeable;

    public ResultObserver() {
        super();
    }

    public ResultObserver(Noticeable notice) {
        this();
        this.noticeable = new WeakReference<Noticeable>(notice);
    }


    @Override
    public void onResult(@NonNull T t) {
    }

    @Override
    public void onFailure(@NonNull Throwable e) {
    }

    @Override
    public void onFinish() {
        if (noticeable.get() != null) {
            noticeable.get().dismissNotice();
        }
    }

}

