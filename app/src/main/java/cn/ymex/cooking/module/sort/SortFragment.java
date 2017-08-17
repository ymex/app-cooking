package cn.ymex.cooking.module.sort;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.ymex.cooking.R;
import cn.ymex.cooking.app.AppContext;
import cn.ymex.cooking.app.http.T;
import cn.ymex.cooking.app.http.ResultObserver;
import cn.ymex.cooking.config.Constant;
import cn.ymex.cooking.module.sort.data.Category;
import cn.ymex.cooking.module.sort.data.ResultCategory;
import cn.ymex.cooking.module.sort.data.souce.SortService;
import cn.ymex.kits.log.L;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnSortFragmentListener} interface
 * to handle interaction events.
 * Use the {@link SortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SortFragment extends Fragment {

    private OnSortFragmentListener mListener;

    public SortFragment() {
        // Required empty public constructor
    }


    AlertDialog alertDialog;

    <T> T service(final Class<T> service) {
        Retrofit retrofit = AppContext.getAppComponent().getRetrofit();
        return retrofit.create(service);
    }


    public void requestFinal() {
        AppContext.getAppComponent()
                .getRetrofit()
                .create(SortService.class)
                .getRxCategory(Constant.APP_KEY)
                .compose(new T<ResultCategory>().transformer())
                .subscribe(new ResultObserver<ResultCategory>(){
                    @Override
                    public void onSuccess(@NonNull ResultCategory resultCategory) {
                        super.onSuccess(resultCategory);
                        L.d(resultCategory.getResult().getChilds().get(3).getCategoryInfo().getName());
                    }
                });
        // TODO: 2017/8/18 网络请求进度框如何 显示的最佳实践。
    }


    public void requestRx() {

        Retrofit retrofit = AppContext.getAppComponent().getRetrofit();
        SortService sortService = retrofit.create(SortService.class);
        Observable<ResultCategory> categoryObservable = sortService.getRxCategory(Constant.APP_KEY);
        categoryObservable.subscribeOn(Schedulers.io())
                .map(new Function<ResultCategory, Category>() {
                    @Override
                    public Category apply(@NonNull ResultCategory resultCategory) throws Exception {
                        return resultCategory.getResult();
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {//在接口请求前处理
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        alertDialog = new AlertDialog.Builder(getActivity()).setMessage("加载中").create();
                        alertDialog.show();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResultObserver<Category>(){
                    @Override
                    public void onSuccess(@NonNull Category category) {
                        super.onSuccess(category);
                        L.d("---",category.getChilds().get(2).getCategoryInfo().getName());
                    }
                    @Override
                    public void onFinish() {
                        super.onFinish();
                        alertDialog.dismiss();
                    }
                });
    }


    public void requestHttpRx() {


        Retrofit retrofit = AppContext.getAppComponent().getRetrofit();

        SortService sortService = retrofit.create(SortService.class);

        Observable<ResultCategory> categoryObservable = sortService.getRxCategory(Constant.APP_KEY);

        categoryObservable.subscribeOn(Schedulers.io())
                .map(new Function<ResultCategory, Category>() {
                    @Override
                    public Category apply(@NonNull ResultCategory resultCategory) throws Exception {
                        return resultCategory.getResult();
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {//在接口请求前处理
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        alertDialog = new AlertDialog.Builder(getActivity()).setMessage("加载中").create();
                        alertDialog.show();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Category>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Category category) {
                        L.d(category.getCategoryInfo().getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        L.d(e.getLocalizedMessage());

                        if (alertDialog.isShowing()) {
                            alertDialog.dismiss();
                        }
                    }

                    @Override
                    public void onComplete() {
                        alertDialog.dismiss();
                    }
                });

    }


    public void requestHttp() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.APIURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SortService sortService = retrofit.create(SortService.class);
        Call<ResultCategory> categoryCall = sortService.getCategory(Constant.APP_KEY);
        categoryCall.enqueue(new Callback<ResultCategory>() {
            @Override
            public void onResponse(Call<ResultCategory> call, Response<ResultCategory> response) {
                ResultCategory resultCategory = response.body();
                L.d(resultCategory.getMsg());
            }

            @Override
            public void onFailure(Call<ResultCategory> call, Throwable t) {

            }
        });
    }

    public static SortFragment newInstance() {
        SortFragment fragment = new SortFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sort, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestFinal();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSortFragmentListener) {
            mListener = (OnSortFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSortFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnSortFragmentListener {

    }
}
