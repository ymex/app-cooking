package cn.ymex.cooking.app.http;

import io.reactivex.disposables.Disposable;

/**
 * Created by ymexc on 2017/8/17.
 */

public interface Http {


    interface CallBack<T> {
        void onStart(Disposable d);

        void onSuccess(T t);

        void onFail(Throwable t);

        void onFinish();
    }


    interface Consumer<T> {
        void accept(T t);
    }


    interface Action {
        void run();
    }
}
