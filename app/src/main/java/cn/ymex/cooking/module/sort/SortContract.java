package cn.ymex.cooking.module.sort;

import cn.ymex.cooking.app.base.BasePresenter;
import cn.ymex.cooking.app.base.BaseView;
import cn.ymex.cooking.module.sort.data.Category;

/**
 * Created by ymex on 2017/8/20.
 */

public class SortContract {
    public interface Presenter extends BasePresenter{
        void requestCookingMenu();
    }

    public interface View extends BaseView<Presenter> {
        void fillAdapter(Category category);
    }
}
