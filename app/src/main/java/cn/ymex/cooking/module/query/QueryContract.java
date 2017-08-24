package cn.ymex.cooking.module.query;

import cn.ymex.cooking.app.base.BasePresenter;
import cn.ymex.cooking.app.base.BaseView;
import cn.ymex.cooking.module.query.data.ResultRecipe;
import io.reactivex.Observer;

public class QueryContract {
    public interface Presenter extends BasePresenter {
        void requestQuery(String cid , int page);
        int getPage();
    }

    public interface View extends BaseView<Presenter> {
        void fillAdapter(ResultRecipe resultRecipe);
        void finishLoadMore();
    }
}
