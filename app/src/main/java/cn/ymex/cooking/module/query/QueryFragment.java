package cn.ymex.cooking.module.query;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.ui.depot.wedgit.SwipeRefreshLayout;
import android.ui.depot.wedgit.swiperefreshlayout.api.RefreshFooter;
import android.ui.depot.wedgit.swiperefreshlayout.api.RefreshLayout;
import android.ui.depot.wedgit.swiperefreshlayout.constant.SpinnerStyle;
import android.ui.depot.wedgit.swiperefreshlayout.footer.BallPulseFooter;
import android.ui.depot.wedgit.swiperefreshlayout.listener.OnLoadmoreListener;
import android.ui.depot.wedgit.swiperefreshlayout.listener.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ymex.cooking.R;
import cn.ymex.cooking.app.ImageLoader;
import cn.ymex.cooking.app.ImageLoaderModule;
import cn.ymex.cooking.app.base.BaseFragment;
import cn.ymex.cooking.app.widget.SwipeRefreshNoticeView;
import cn.ymex.cooking.module.query.data.RecipeIndex;
import cn.ymex.cooking.module.query.data.ResultRecipe;
import cn.ymex.kits.Finder;
import cn.ymex.kits.widget.recycler.RecyclerViewHorizontalDivider;

/**
 * A placeholder fragment containing a simple view.
 */
public class QueryFragment extends BaseFragment implements QueryContract.View {

    private QueryContract.Presenter mPresenter;
    private OnQueryFragmentListener mListener;
    SwipeRefreshLayout refreshLayout;

    private String cid;

    @BindView(R.id.rlv_content)
    RecyclerView recyclerView;
    private QueryAdapter adapter;

    public QueryFragment() {
    }

    public static QueryFragment newInstance() {
        return new QueryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_query, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        refreshLayout = (SwipeRefreshLayout) view;
        setNoticeView(new SwipeRefreshNoticeView(refreshLayout));
        refreshLayout.setEnableFooterTranslationContent(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter = new QueryAdapter(null));

        recyclerView.addItemDecoration(RecyclerViewHorizontalDivider.def(getActivity()));

        cid = getArguments().getString("cid");
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.requestQuery(cid, 1);
            }
        });
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.requestQuery(cid, mPresenter.getPage());
            }
        });
        refreshLayout.autoRefresh();
    }


    @Override
    public void setPresenter(QueryContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnQueryFragmentListener) {
            mListener = (OnQueryFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnQueryFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mPresenter.start();
    }

    @Override
    public void fillAdapter(ResultRecipe resultRecipe) {
        adapter.setResultRecipe(resultRecipe);
        adapter.notifyDataSetChanged();
    }

    public interface OnQueryFragmentListener {

    }

    @Override
    public void finishLoadMore() {
        refreshLayout.finishLoadmore();
    }

    class QueryAdapter extends RecyclerView.Adapter<ViewHolder> {

        ResultRecipe resultRecipe;


        public QueryAdapter(ResultRecipe resultRecipe) {
            this.resultRecipe = resultRecipe;
        }

        public void setResultRecipe(ResultRecipe resultRecipe) {
            if (this.resultRecipe == null) {
                this.resultRecipe = resultRecipe;
            }else {
                this.resultRecipe.getResult().getList().addAll(resultRecipe.getResult().getList());
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /**
             * 第一屏不显示bug,item 显示没有依赖于parent
             * http://ask.csdn.net/questions/223669
             */
            return new ViewHolder(Finder.inflate(parent.getContext(), R.layout.item_query_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            RecipeIndex item = resultRecipe.getResult().getList().get(position);
            System.out.println("----:::" + item.getName());
            holder.tvTitle.setText(item.getName());
            holder.tvTags.setText(item.getCtgTitles());
            String url = item.getThumbnail();
            if (TextUtils.isEmpty(url)) {
                url = item.getRecipe().getImg();
            }
            ImageLoader.with(holder.itemView).load(url)
                    .transition(ImageLoaderModule.drawableCrossFade())
                    .into(holder.ivImage);
        }

        @Override
        public int getItemCount() {
            return resultRecipe == null ? 0 : resultRecipe.getResult().getList().size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_tags)
        TextView tvTags;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
