package cn.ymex.cooking;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ymex.cooking.app.base.BaseActivity;
import cn.ymex.cooking.kits.UtilsComponent;
import cn.ymex.cooking.module.home.HomeFragment;
import cn.ymex.cooking.module.person.PersonFragment;
import cn.ymex.cooking.module.sort.DaggerSortPresenterComponent;
import cn.ymex.cooking.module.sort.SortFragment;
import cn.ymex.cooking.module.sort.SortPresenter;
import cn.ymex.cooking.module.sort.SortPresenterModule;
import cn.ymex.kits.FragmentManagerWrap;

public class MainActivity extends BaseActivity implements HomeFragment.OnHomeFragmentListener,
        SortFragment.OnSortFragmentListener, PersonFragment.OnPersonFragmentListener,
        BottomNavigationView.OnNavigationItemSelectedListener {
    //@Inject
    FragmentManagerWrap fragmentManagerWrap;

    @Inject
    SortPresenter sortPresenter;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    UtilsComponent utilsComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fragmentManagerWrap = FragmentManagerWrap.build(getSupportFragmentManager());
//        utilsComponent = DaggerUtilsComponent.builder()
//                .utilsMoudel(new UtilsMoudel(getSupportFragmentManager()))
//                .build();
//        utilsComponent.inject(this);


        navigation.setOnNavigationItemSelectedListener(this);


        if (!fragmentManagerWrap.attached()) {
            fragmentManagerWrap.setContainerViewId(R.id.contentFragment)
                    .setLazyInit(false)
                    .add(HomeFragment.newInstance(),
                            SortFragment.newInstance(),
                            PersonFragment.newInstance())
                    .attach();

        } else {
            fragmentManagerWrap.restore();
        }

        DaggerSortPresenterComponent.builder()
                .sortPresenterModule(new SortPresenterModule((SortFragment) fragmentManagerWrap.getFragment(1)))
                .build().inject(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragmentManagerWrap.showFragment(0);
                return true;
            case R.id.navigation_sort:
                fragmentManagerWrap.showFragment(1);
                return true;
            case R.id.navigation_person:
                fragmentManagerWrap.showFragment(2);
                return true;
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
