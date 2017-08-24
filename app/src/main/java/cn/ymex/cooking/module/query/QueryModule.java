package cn.ymex.cooking.module.query;

import cn.ymex.cooking.app.scoped.FragmentScoped;
import cn.ymex.cooking.module.query.data.source.QueryRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class QueryModule {

    private final QueryContract.View mView;

    public QueryModule(QueryContract.View view) {
        this.mView = view;
    }

    @FragmentScoped
    @Provides
    QueryContract.View provideQueryView() {
        return this.mView;
    }

    @FragmentScoped
    @Provides
    QueryRepository proideRepository() {
        return new QueryRepository();
    }
}
