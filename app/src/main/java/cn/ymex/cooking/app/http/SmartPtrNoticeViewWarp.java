package cn.ymex.cooking.app.http;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by ymex on 2017/8/19.
 */

public class SmartPtrNoticeViewWarp implements NoticeViewable {
    private SmartRefreshLayout smartRefreshLayout;

    public SmartPtrNoticeViewWarp(SmartRefreshLayout layout) {
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
