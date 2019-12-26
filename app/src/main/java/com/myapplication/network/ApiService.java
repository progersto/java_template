package com.myapplication.network;

import com.myapplication.network.response.WetherResponse;

import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {
    // @FormUrlEncoded      - анотация, если запрос типа application/x-www-form-urlencoded. Пишется над @GET, @POST...

    // @Header("X-AUTH-TOKEN") token: String         - анотация для Header
    // @Field("user[password]") password: String     - анотация для BODY
    // @Query("city") String strCity                    - анотация для PARAMS
    // @Path("code") code: String   - анотация для запроса типа @GET("lookup/{code}")
//------------------------------------------------------------------------------------------------------------------------
    @GET("weather")
    Single<WetherResponse> getWeather(
//            @Header("X-AUTH-TOKEN")
//            String token,

            @Query("q") String strCity
    );

    @GET("weather")
    Completable getWeather2(
//            @Header("X-AUTH-TOKEN")
//            String token,

            @Query("q") String strCity
    );
}
