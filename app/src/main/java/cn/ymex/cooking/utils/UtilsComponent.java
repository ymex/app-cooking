package cn.ymex.cooking.utils;

import cn.ymex.cooking.MainActivity;
import dagger.Component;

/**
 * Created by ymex on 2017/8/16.
 */

@Component(modules = UtilsMoudel.class)
public interface UtilsComponent {
    void inject(MainActivity mainActivity);
}
