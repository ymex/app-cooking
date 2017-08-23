package cn.ymex.cooking.module.query;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import cn.ymex.cooking.R;
import cn.ymex.cooking.app.base.BaseActivity;

public class QueryActivity extends BaseActivity implements QueryFragment.OnQueryFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        QueryFragment fragment =
                (QueryFragment) getSupportFragmentManager().findFragmentById(R.id.contentFragment);
        if (fragment == null) {
            fragment = QueryFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.contentFragment, fragment).commit();
        }

    }

}
