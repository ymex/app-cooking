package cn.ymex.cooking.app.http;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * 继承重写 isShow showNotice dismissNotice。 你就能实现自己的加载效果。
 */

public interface NoticeViewable {
    boolean isShow();

    void showNotice();

    void dismissNotice();
}
