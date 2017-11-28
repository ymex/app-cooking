package cn.ymex.cooking.module.sort.data.souce;

import cn.ymex.cooking.AppContext;
import cn.ymex.cooking.app.http.Noticeable;
import cn.ymex.cooking.app.http.T;
import cn.ymex.cooking.config.Constant;
import cn.ymex.cooking.module.sort.data.ResultCategory;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Created by ymexc on 2017/8/22.
 */

public class SortRepository {

    /**
     * 网络请求菜单分类
     * @param noticeable
     * @param observer
     */
    public void getCookingMenu(Noticeable noticeable,Observer<ResultCategory> observer) {
        AppContext.getAppComponent()
                .getRetrofit()
                .create(SortService.class)
                .getRxCategory(Constant.APP_KEY)//app key
                .compose( T.create(noticeable).<ResultCategory>transformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
