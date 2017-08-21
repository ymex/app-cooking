package cn.ymex.cooking.module.sort;

import android.ui.depot.wedgit.SwipeRefreshLayout;

import cn.ymex.cooking.app.base.BasePresenter;
import cn.ymex.cooking.app.base.BaseView;

/**
 * Created by ymex on 2017/8/20.
 */

public class SortContract {
    public interface Presenter extends BasePresenter{

    }

    public interface View extends BaseView<Presenter> {
        SwipeRefreshLayout getRefreshLayout();
    }
}
