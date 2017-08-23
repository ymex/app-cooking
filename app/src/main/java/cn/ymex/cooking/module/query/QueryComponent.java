package cn.ymex.cooking.module.query;

import cn.ymex.cooking.app.scoped.FragmentScoped;
import dagger.Component;

@FragmentScoped
@Component(modules = QueryModule.class)
public interface QueryComponent {
    void inject(QueryActivity activity);
}
