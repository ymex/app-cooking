package cn.ymex.cooking.module.query;

import cn.ymex.cooking.app.base.BasePresenter;
import cn.ymex.cooking.app.base.BaseView;

public class QueryContract {
    public interface Presenter extends BasePresenter {
    }

    public interface View extends BaseView<Presenter> {
    }
}
