package cn.ymex.cooking.app.http;

import io.reactivex.annotations.NonNull;

public class ResultObserver<T> extends ResponseObserver<T> {


    @Override
    public void onSuccess(@NonNull T t) {

    }

    @Override
    public void onMistake(@NonNull Throwable e) {

    }

    @Override
    public void onFinish() {

    }
}

