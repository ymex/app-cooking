package cn.ymex.cooking.app.http;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by ymex on 2017/8/19.
 */

public class SRLNoticeViewWarp implements NoticeViewable{
    private SwipeRefreshLayout noticeView;

    public SRLNoticeViewWarp(SwipeRefreshLayout noticeView) {
        this.noticeView = noticeView;
    }

    @Override
    public boolean isShow() {
        if (noticeView == null) {
            return false;
        }
        return noticeView.isRefreshing();
    }

    @Override
    public void showNotice() {
        if (noticeView == null) {
            return;
        }
        //这样只有动画效果，并不会调用onRefresh
        noticeView.post(new Runnable() {
            @Override
            public void run() {
                noticeView.setRefreshing(true);
            }
        });
    }
    @Override
    public void dismissNotice() {
        if (noticeView == null) {
            return;
        }
        noticeView.setRefreshing(false);
    }
}
