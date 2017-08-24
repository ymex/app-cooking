package cn.ymex.cooking.module.query.data.source;

import cn.ymex.cooking.app.http.Params;
import cn.ymex.cooking.module.query.data.ResultRecipe;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface QueryService {
    /**
     *menu/search?key=51c39ddd603b&cid=0010001007&page=1&size=20
     * key String key,
     * cid String cid,
     * page int page,
     * size int size
     *
     * @param options
     * @return
     */
    @GET("menu/search")
    Observable<ResultRecipe> getQueryRecipe(@QueryMap Params options );
}
