package cn.ymex.cooking.app.widget;

import android.ui.depot.wedgit.SwipeRefreshLayout;

import cn.ymex.cooking.app.http.NoticeViewable;

/**
 * SwipeRefreshLayout作为加载提醒控件
 */

public class SwipeRefreshNoticeView implements NoticeViewable {
    private SwipeRefreshLayout refreshLayout;

    public SwipeRefreshNoticeView(SwipeRefreshLayout layout) {
        this.refreshLayout = layout;
    }

    @Override
    public boolean isShow() {
        return refreshLayout.isRefreshing();

    }


    @Override
    public void showNotice() {
        refreshLayout.autoRefresh(0);
        // TODO: 2017/8/22  
        //refreshLayout.setRefresh(true);
    }

    @Override
    public void dismissNotice() {
        refreshLayout.finishRefresh(800);
    }
}
