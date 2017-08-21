package cn.ymex.cooking.app.widget;

import android.ui.depot.wedgit.SwipeRefreshLayout;

import cn.ymex.cooking.app.http.NoticeViewable;

/**
 * Created by ymex on 2017/8/19.
 */

public class SwipeRefreshNoticeView implements NoticeViewable {
    private SwipeRefreshLayout smartRefreshLayout;

    public SwipeRefreshNoticeView(SwipeRefreshLayout layout) {
        this.smartRefreshLayout = layout;
    }

    @Override
    public boolean isShow() {
        return smartRefreshLayout.isRefreshing();
    }

    @Override
    public void showNotice() {
        smartRefreshLayout.autoRefresh();
    }

    @Override
    public void dismissNotice() {
        smartRefreshLayout.finishRefresh();
    }
}
