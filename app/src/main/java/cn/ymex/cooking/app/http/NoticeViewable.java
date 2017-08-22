package cn.ymex.cooking.app.http;

/**
 * 继承重写 isShow showNotice dismissNotice。
 */

public interface NoticeViewable {
    boolean isShow();

    void showNotice();

    void dismissNotice();
}
