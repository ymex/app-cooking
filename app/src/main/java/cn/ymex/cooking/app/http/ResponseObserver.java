package cn.ymex.cooking.app.http;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;



public abstract class ResponseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        this.onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        this.onMistake(e);
        this.onFinish();
    }

    @Override
    public void onComplete() {
        this.onFinish();
    }

    abstract void onSuccess(@NonNull T t);

    abstract void onMistake(@NonNull Throwable e);

    abstract void onFinish();
}