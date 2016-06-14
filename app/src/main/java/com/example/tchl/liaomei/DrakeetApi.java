package com.example.tchl.liaomei;

import rx.Observable;
import com.example.tchl.liaomei.data.GankData;
import com.example.tchl.liaomei.data.DGankData;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
/**
 * Created by happen on 2016/6/3.
 */
public interface DrakeetApi {
    @Headers({ "X-LC-Id: 0azfScvBLCC9tAGRAwIhcC40",
            "X-LC-Key: gAuE93qAusvP8gk1VW8DtOUb",
            "Content-Type: application/json" })
    @GET("/Gank?limit=1")
    Observable<DGankData> getDGankData(
            @Query("where") String where);// format {"tag":"2015-11-10"}
}
