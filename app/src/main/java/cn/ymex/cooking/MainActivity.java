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
import cn.ymex.cooking.module.home.HomeFragment;
import cn.ymex.cooking.module.person.PersonFragment;
import cn.ymex.cooking.module.sort.SortFragment;
import cn.ymex.cooking.utils.DaggerUtilsComponent;
import cn.ymex.cooking.utils.FragmentManagerWrap;
import cn.ymex.cooking.utils.UtilsMoudel;

public class MainActivity extends BaseActivity implements HomeFragment.OnHomeFragmentListener,
        SortFragment.OnSortFragmentListener, PersonFragment.OnPersonFragmentListener,
        BottomNavigationView.OnNavigationItemSelectedListener {
    @Inject
    FragmentManagerWrap fragmentManagerWrap;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        DaggerUtilsComponent.builder().utilsMoudel(new UtilsMoudel(getSupportFragmentManager())).build().inject(this);

        setSupportActionBar(toolbar);

        navigation.setOnNavigationItemSelectedListener(this);


        if (!fragmentManagerWrap.attached()) {
            fragmentManagerWrap.add(HomeFragment.newInstance(),
                    SortFragment.newInstance(),
                    PersonFragment.newInstance())
                    .attach(R.id.contentFragment);

        } else {
            fragmentManagerWrap.restore();
        }


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
