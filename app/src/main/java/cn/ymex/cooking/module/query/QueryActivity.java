package cn.ymex.cooking.module.query;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import cn.ymex.cooking.R;
import cn.ymex.cooking.app.base.BaseActivity;

public class QueryActivity extends BaseActivity implements QueryFragment.OnQueryFragmentListener {

    @Inject
    QueryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String cid = getIntent().getStringExtra("cid");

        QueryFragment fragment =
                (QueryFragment) getSupportFragmentManager().findFragmentById(R.id.contentFragment);



        if (fragment == null) {
            fragment = QueryFragment.newInstance();

            Bundle bundle = new Bundle();
            bundle.putString("cid",cid);
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().add(R.id.contentFragment, fragment).commit();
        }



        DaggerQueryComponent.builder().queryModule(new QueryModule(fragment)).build().inject(this);

    }


    public static void startAction(Context context,String cid) {
        Intent view = new Intent(context,QueryActivity.class);
        view.putExtra("cid", cid);
        context.startActivity(view);
    }

}
