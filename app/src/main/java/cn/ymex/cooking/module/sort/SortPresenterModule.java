package cn.ymex.cooking.module.sort;

import cn.ymex.cooking.app.widget.SwipeRefreshNoticeView;
import cn.ymex.cooking.module.sort.data.souce.SortRepository;
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
    SortRepository provideSortRepository() {
        return new SortRepository();
    }

    @Provides
    SwipeRefreshNoticeView provideSwipeRefreshNoticeView() {
        return new SwipeRefreshNoticeView(mView.getRefreshLayout());
    }

}
