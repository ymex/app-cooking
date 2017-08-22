package cn.ymex.cooking.module.sort;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.ui.depot.wedgit.SwipeRefreshLayout;
import android.ui.depot.wedgit.swiperefreshlayout.api.RefreshLayout;
import android.ui.depot.wedgit.swiperefreshlayout.listener.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ymex.cooking.R;
import cn.ymex.cooking.AppContext;
import cn.ymex.cooking.app.base.BaseFragment;
import cn.ymex.cooking.app.http.ResultObserver;
import cn.ymex.cooking.app.http.T;
import cn.ymex.cooking.app.widget.SwipeRefreshNoticeView;
import cn.ymex.cooking.config.Constant;
import cn.ymex.cooking.module.sort.data.ResultCategory;
import cn.ymex.cooking.module.sort.data.souce.SortService;
import cn.ymex.kits.log.L;
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
public class SortFragment extends BaseFragment implements SortContract.View{

    private OnSortFragmentListener mListener;
    private SortContract.Presenter presenter;
    private SwipeRefreshLayout refreshLayout;


    @BindView(R.id.tv_content)
    TextView tvContent;

    public SortFragment() {
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

        refreshLayout = (SwipeRefreshLayout) view;

        setNoticeView(new SwipeRefreshNoticeView(refreshLayout));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });

    }


    @OnClick(R.id.btn_send)
    public void send(View view) {
       // refreshLayout.autoRefresh();
        presenter.requestCookingMenu();
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



    //<editor-fold desc="SortContract.View 接口方法">
    @Override
    public void setPresenter(SortContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public SwipeRefreshLayout getRefreshLayout() {
        return (SwipeRefreshLayout) getView();
    }

    @Override
    public void setTextContent(String content) {
        tvContent.setText(content);
    }

    //</editor-fold>


    @Override
    public void onResume() {
        super.onResume();
        this.presenter.start();
    }

    public interface OnSortFragmentListener {

    }

}
