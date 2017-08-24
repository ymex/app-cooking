package cn.ymex.cooking.module.query;

import javax.inject.Inject;

import cn.ymex.cooking.app.http.ResultObserver;
import cn.ymex.cooking.module.query.data.ResultRecipe;
import cn.ymex.cooking.module.query.data.source.QueryRepository;
import io.reactivex.annotations.NonNull;

public class QueryPresenter implements QueryContract.Presenter {

    QueryContract.View view;
    QueryRepository repository;

    @Inject
    QueryPresenter(QueryContract.View view,QueryRepository repository) {
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
    public void requestQuery(String cid, int page) {
        repository.getCookingMenu(cid,page,this.view,new ResultObserver<ResultRecipe>(){
            @Override
            public void onResult(@NonNull ResultRecipe resultRecipe) {
                super.onResult(resultRecipe);
                view.fillAdapter(resultRecipe);
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                super.onFailure(e);
                System.out.println("----:::"+e.getLocalizedMessage());
            }
        });
    }
}
