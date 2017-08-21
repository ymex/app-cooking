package cn.ymex.cooking.module.sort;

import cn.ymex.cooking.app.widget.SwipeRefreshNoticeView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ymexc on 2017/8/21.
 */

@Module
public class SortPresenterModule {

    private final SortContract.View mView;


    public SortPresenterModule(SortContract.View view) {
        this.mView = view;
    }

    @Provides
    SortContract.View provideSortContractView() {
        return this.mView;
    }

    @Provides
    SwipeRefreshNoticeView provideSwipeRefreshNoticeView() {
        return new SwipeRefreshNoticeView(mView.getRefreshLayout());
    }

}
