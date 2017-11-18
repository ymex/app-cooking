package cn.ymex.cooking.module.sort;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ymex.cooking.R;
import cn.ymex.cooking.app.base.BaseFragment;
import cn.ymex.cooking.app.widget.SwipeRefreshNoticeView;
import cn.ymex.cooking.module.sort.data.Category;
import cn.ymex.cooking.module.sort.data.CategoryInfo;
import cn.ymex.cooking.module.sort.data.CategoryInfoItem;
import cn.ymex.cooking.module.sort.data.CategoryInfoItemWarp;
import cn.ymex.kits.Finder;
import cn.ymex.widget.swipe.SwipeRefreshLayout;
import cn.ymex.widget.swipe.api.RefreshLayout;
import cn.ymex.widget.swipe.listener.OnRefreshListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnSortFragmentListener} interface
 * to handle interaction events.
 * Use the {@link SortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SortFragment extends BaseFragment implements SortContract.View {

    private OnSortFragmentListener mListener;
    private SortContract.Presenter presenter;
    private SwipeRefreshLayout refreshLayout;
    private SortMenuAdapter adapter;


    @BindView(R.id.rlv_content)
    RecyclerView recyclerView;

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
                presenter.requestCookingMenu();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter = new SortMenuAdapter());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        refreshLayout.autoRefresh();
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


    //</editor-fold>


    @Override
    public void onResume() {
        super.onResume();
        this.presenter.start();
    }

    public interface OnSortFragmentListener {

    }


    @Override
    public void fillAdapter(Category category) {

        List<CategoryInfoItem> items = new ArrayList<>();

        for (CategoryInfo categoryInfo : category.getChilds()) {

            items.add(categoryInfo.getCategoryInfo());
            System.out.println(categoryInfo.getChilds().size() + "   ---  ");
            for (CategoryInfoItemWarp info : categoryInfo.getChilds()) {
                info.getCategoryInfo().setTag("item");
                items.add(info.getCategoryInfo());
            }
        }

        adapter.setCategory(items);
        adapter.notifyDataSetChanged();
    }

    /**
     * 建议adapter view 内部类中,这样方便管理。
     */

    class SortMenuAdapter extends RecyclerView.Adapter<ViewHoder> {

        List<CategoryInfoItem> items;

        public void setCategory(List<CategoryInfoItem> items) {
            this.items = items;
        }

        @Override
        public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHoder(Finder.inflate(parent.getContext(), R.layout.item_sort_layout));
        }

        @Override
        public void onBindViewHolder(ViewHoder holder, int position) {
            final CategoryInfoItem item = items.get(position);
            holder.tvTitle.setText(item.getName());
            if (!"item".equals(item.getTag())) {
                holder.ivArray.setImageDrawable(new ColorDrawable());
                holder.itemView.setOnClickListener(null);
            }else {

                holder.ivArray.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.toQueryAct(getActivity() ,item.getCtgId());
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            return items == null ? 0 : items.size();
        }
    }

    class ViewHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_array)
        ImageView ivArray;
        public ViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
