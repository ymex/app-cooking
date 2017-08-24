package cn.ymex.cooking.module.sort;

import android.content.Context;

import cn.ymex.cooking.app.base.BasePresenter;
import cn.ymex.cooking.app.base.BaseView;
import cn.ymex.cooking.module.sort.data.Category;

/**
 * Created by ymex on 2017/8/20.
 */

public class SortContract {
    public interface Presenter extends BasePresenter{
        void requestCookingMenu();

        void toQueryAct(Context context,String cid);
    }

    public interface View extends BaseView<Presenter> {
        void fillAdapter(Category category);
    }
}
