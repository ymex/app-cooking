package cn.ymex.cooking.module.query;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import android.support.annotation.Nullable;

import cn.ymex.cooking.R;
import cn.ymex.cooking.app.base.BaseFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class QueryFragment extends BaseFragment implements QueryContract.View {

    private QueryContract.Presenter mPresenter;
    private OnQueryFragmentListener mListener;

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

    public interface OnQueryFragmentListener {

    }
}
