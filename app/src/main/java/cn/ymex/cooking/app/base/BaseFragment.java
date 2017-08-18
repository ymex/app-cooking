package cn.ymex.cooking.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import java.util.HashMap;
import java.util.Map;

import cn.ymex.cooking.app.http.Noticeable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ymex on 2017/8/15.
 */

public class BaseFragment extends Fragment implements Noticeable {

    //加载框
    protected AlertDialog mProgressView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressView = new AlertDialog.Builder(getActivity()).setMessage("加载中").create();
    }

    @Override
    public boolean isShow() {
        if (mProgressView == null) {
            return false;
        }
        return mProgressView.isShowing();
    }


    @Override
    public void showNotice() {
        if (mProgressView != null) {
            mProgressView.show();
        }
    }

    @Override
    public void dismissNotice() {
        if (mProgressView != null) {
            mProgressView.dismiss();
        }
    }


    /**
     * fragment 是否对用户可见
     */
    protected boolean mIsVisibleToUser;
    protected HashMap<Integer, Disposable> mDisposableMap;

    public void onHiddenChanged(boolean hidden) {
        this.mIsVisibleToUser = !hidden;
        if (hidden && mProgressView != null && mProgressView.isShowing()) {
            mProgressView.dismiss();
        }
    }


    @Override
    public boolean isVisibleToUser() {
        return mIsVisibleToUser;
    }

    @Override
    public void setDisposable(int key, Disposable disposable) {
        getDisposableMap().put(key, disposable);
    }

    public HashMap<Integer, Disposable> getDisposableMap() {
        return mDisposableMap == null ? new HashMap<Integer, Disposable>() : mDisposableMap;

    }

    /**
     * 取消所有的订阅
     */
    public void cancelDisposables() {
        for (Map.Entry<Integer, Disposable> item : getDisposableMap().entrySet()) {
            Disposable disposable = item.getValue();
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
        }
        dismissNotice();
    }

}
