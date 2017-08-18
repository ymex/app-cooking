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
        this.onResult(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        this.onFailure(e);
        this.onFinish();
    }

    @Override
    public void onComplete() {
        this.onFinish();
    }

    abstract void onResult(@NonNull T t);

    abstract void onFailure(@NonNull Throwable e);

    /**
     * 不管成功还是失败都会最后一步都会调用onfinish
     */
    abstract void onFinish();
    
}