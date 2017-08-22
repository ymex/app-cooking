package cn.ymex.cooking.module.sort;

import android.widget.Toast;

import javax.inject.Inject;

import cn.ymex.cooking.AppContext;
import cn.ymex.cooking.app.http.ResultObserver;
import cn.ymex.cooking.module.sort.data.ResultCategory;
import cn.ymex.cooking.module.sort.data.souce.SortRepository;
import cn.ymex.kits.AppContent;
import cn.ymex.kits.log.L;
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
        L.d("tag.presenter", "SortPresenter");
    }

    @Inject
    void setOnInject() {
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        L.d("tag.presenter", "start");
    }

    static int a = 0;

    @Override
    public void requestCookingMenu() {
        a++;
        repository.getCookingMenu(view, new ResultObserver<ResultCategory>(view) {
            @Override
            public void onResult(@NonNull ResultCategory resultCategory) {
                super.onResult(resultCategory);

                String name = resultCategory.getResult().getCategoryInfo().getName();
                Toast.makeText(AppContext.getAppComponent().getAppcontext(), name, Toast.LENGTH_SHORT).show();
                view.setTextContent(name);
            }
        });
    }
}
