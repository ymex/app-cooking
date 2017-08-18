package cn.ymex.cooking.module.sort;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ymex.cooking.R;
import cn.ymex.cooking.app.AppContext;
import cn.ymex.cooking.app.base.BaseFragment;
import cn.ymex.cooking.app.http.NoticeViewable;
import cn.ymex.cooking.app.http.SRLNoticeViewWarp;
import cn.ymex.cooking.app.http.SmartPtrNoticeViewWarp;
import cn.ymex.cooking.app.http.T;
import cn.ymex.cooking.app.http.ResultObserver;
import cn.ymex.cooking.config.Constant;
import cn.ymex.cooking.module.sort.data.ResultCategory;
import cn.ymex.cooking.module.sort.data.souce.SortService;
import cn.ymex.kits.log.L;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnSortFragmentListener} interface
 * to handle interaction events.
 * Use the {@link SortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SortFragment extends BaseFragment {

    private OnSortFragmentListener mListener;

    //@BindView(R.id.swipe_content)
    private SmartRefreshLayout smartRefreshLayout;

    public SortFragment() {
    }


    public void requestFinal() {
        AppContext.getAppComponent()
                .getRetrofit()
                .create(SortService.class)
                .getRxCategory(Constant.APP_KEY)
                .compose(new T<ResultCategory>(this).transformer())
                .delay(15, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResultObserver<ResultCategory>(this) {
                    @Override
                    public void onResult(@NonNull ResultCategory resultCategory) {
                        super.onResult(resultCategory);
                        L.d(resultCategory.getResult().getChilds().get(4).getCategoryInfo().getName());
                    }
                });
    }


    public static SortFragment newInstance() {
        SortFragment fragment = new SortFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sort, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        smartRefreshLayout = (SmartRefreshLayout) view;
        //设置 Header 为 Material风格
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(false));
        //设置 Footer 为 球脉冲
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        smartRefreshLayout.setEnableLoadmore(true);
        smartRefreshLayout.setEnableFooterTranslationContent(false);
        smartRefreshLayout.setEnableLoadmoreWhenContentNotFull(true);
        setNoticeView(new SmartPtrNoticeViewWarp(smartRefreshLayout));

    }


    @OnClick(R.id.btn_send)
    public void send(View view) {
        requestFinal();
    }

    @OnClick(R.id.btn_cancel)
    public void cancel(View view) {
        cancelDisposables();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSortFragmentListener) {
            mListener = (OnSortFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSortFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnSortFragmentListener {

    }

}
