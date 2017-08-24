package cn.ymex.cooking.module.sort;

import android.content.Context;

import javax.inject.Inject;

import cn.ymex.cooking.app.http.ResultObserver;
import cn.ymex.cooking.module.query.QueryActivity;
import cn.ymex.cooking.module.sort.data.ResultCategory;
import cn.ymex.cooking.module.sort.data.souce.SortRepository;
import io.reactivex.annotations.NonNull;

/**
 * Created by ymex on 2017/8/20.
 */

public class SortPresenter implements SortContract.Presenter {


    SortContract.View view;
    SortRepository repository;


    @Inject
    SortPresenter(SortRepository repository, SortContract.View view) {
        this.view = view;
        this.repository = repository;
    }

    @Inject
    void setOnInject() {
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public void requestCookingMenu() {
        repository.getCookingMenu(view, new ResultObserver<ResultCategory>() {
            @Override
            public void onResult(@NonNull ResultCategory resultCategory) {
                super.onResult(resultCategory);
                view.fillAdapter(resultCategory.getResult());
            }
        });
    }

    @Override
    public void toQueryAct(Context context, String cid) {
        QueryActivity.startAction(context, cid);
    }
}
