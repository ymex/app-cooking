package cn.ymex.cooking.utils;

import android.support.v4.app.FragmentManager;

import cn.ymex.cooking.app.scoped.ActivityScoped;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ymex on 2017/8/16.
 */

@Module
public class UtilsMoudel {
    private final FragmentManager fragmentManager;

    public UtilsMoudel(FragmentManager fragmentManager) {
        this.fragmentManager =fragmentManager;
    }

    @Provides
    @ActivityScoped
    public FragmentManagerWrap provideFragmentMangerWrap() {
        return FragmentManagerWrap.build(fragmentManager);
    }
}
