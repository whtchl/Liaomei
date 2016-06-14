package com.example.tchl.liaomei;

import rx.Observable;
import retrofit.http.GET;
import retrofit.http.Path;
import com.example.tchl.liaomei.data.GankData;
import com.example.tchl.liaomei.data.LiaomeiData;
import com.example.tchl.liaomei.data.entity.Liaomei;


/**
 * Created by happen on 2016/6/3.
 */
//http://gank.io/api//data/福利/10/1
public interface GankApi {

    @GET("/data/福利/" + DrakeetFactory.meizhiSize + "/{page}")
    Observable<LiaomeiData> getMeizhiData(
            @Path("page") int page);

    @GET("/day/{year}/{month}/{day}") Observable<GankData> getGankData(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);
}
