package cn.ymex.cooking.module.sort;

import javax.inject.Inject;

import cn.ymex.kits.log.L;

/**
 * Created by ymex on 2017/8/20.
 */

public class SortPresenter implements SortContract.Presenter {


    SortContract.View view;


    @Inject
    SortPresenter(SortContract.View view) {
        this.view = view;
        L.d("tag.presenter","SortPresenter");
    }

    @Inject
    void setOnInject() {
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        L.d("tag.presenter","start");
    }
}
