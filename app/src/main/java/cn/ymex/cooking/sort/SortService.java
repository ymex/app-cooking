package cn.ymex.cooking.sort;

import cn.ymex.cooking.data.ResultCategory;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ymex on 2017/8/16.
 */

public interface SortService {
    @GET("category/query")
    Call<ResultCategory> getCategory(@Query("key") String key);
    @GET("category/query")
    Observable<ResultCategory> getRxCategory(@Query("key") String key);
}
