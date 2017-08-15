package cn.ymex.cooking.utils;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ymex on 2017/8/16.
 */

@Module
public class UtilsMoudel {
    FragmentManager fragmentManager;

    public UtilsMoudel(FragmentManager fragmentManager) {
        this.fragmentManager =fragmentManager;
    }
    @Provides
    public FragmentManagerWrap provideFragmentMangerWrap() {
        return FragmentManagerWrap.build(fragmentManager);
    }
}
