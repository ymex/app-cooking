package cn.ymex.cooking.module.query.data.source;

import cn.ymex.cooking.AppContext;
import cn.ymex.cooking.app.http.Noticeable;
import cn.ymex.cooking.app.http.Params;
import cn.ymex.cooking.app.http.T;
import cn.ymex.cooking.config.Constant;
import cn.ymex.cooking.module.query.data.ResultRecipe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by ymexc on 2017/8/24.
 */

public class QueryRepository {


    /**
     * 分类查询
     * @param noticeable
     * @param observer
     * @param cid 分类id
     * @param page 页数
     */
    public void getCookingMenu(String cid , int page,Noticeable noticeable, Observer<ResultRecipe> observer) {
        Params params = Params.stream()
                .with("key",Constant.APP_KEY)
                .with("cid",cid)
                .with("page",page)
                .with("size",Constant.PAGE_SIZE);

        AppContext.getAppComponent()
                .getRetrofit()
                .create(QueryService.class)
                .getQueryRecipe(params)
                .compose(new T<ResultRecipe>(noticeable).transformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
