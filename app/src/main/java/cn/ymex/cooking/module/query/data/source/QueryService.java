package cn.ymex.cooking.module.query.data.source;

import cn.ymex.cooking.module.query.data.ResultRecipe;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ymex on 2017/8/24.
 */

public interface QueryService {
    ///menu/search?key=51c39ddd603b&cid=0010001007&page=1&size=20
    @GET("/menu/search")
    Observable<ResultRecipe> getQueryRecipe(@Query("key") String key,@Query("cid") String cid,@Query("page") int page, @Query("size") int size);
}
