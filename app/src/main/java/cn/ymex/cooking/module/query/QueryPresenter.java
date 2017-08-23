package cn.ymex.cooking.module.query;

import javax.inject.Inject;

public class QueryPresenter implements QueryContract.Presenter {

    QueryContract.View view;

    @Inject
    QueryPresenter(QueryContract.View view) {
        this.view = view;
    }

    @Inject
    void setOnInject() {
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
    }
}
