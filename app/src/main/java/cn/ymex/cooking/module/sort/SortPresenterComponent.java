package cn.ymex.cooking.module.sort;

import cn.ymex.cooking.MainActivity;
import cn.ymex.cooking.app.scoped.FragmentScoped;
import dagger.Component;

/**
 * Created by ymexc on 2017/8/21.
 */

@FragmentScoped
@Component(modules = SortPresenterModule.class)
public interface SortPresenterComponent {
    void inject(MainActivity mainActivity);
}
