package cn.ymex.cooking.module.sort.data.souce;

import cn.ymex.cooking.module.sort.data.ResultCategory;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ymex on 2017/8/16.
 */

public interface SortService {
    @GET("category/query")
    Observable<ResultCategory> getRxCategory(@Query("key") String key);
}
