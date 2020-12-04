package leavesc.hello.network.http.service;

import io.reactivex.Observable;
import leavesc.hello.network.http.basis.config.HttpConfig;
import leavesc.hello.network.http.basis.model.BaseResponseBody;
import leavesc.hello.network.model.NewsPack;
import leavesc.hello.network.model.QrCode;
import leavesc.hello.network.model.Weather;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 作者：leavesC
 * 时间：2018/10/28 13:13
 * 描述：
 * GitHub：https://github.com/leavesC
 */
public interface ApiService {

    @Headers({HttpConfig.HTTP_REQUEST_TYPE_KEY + ":" + HttpConfig.HTTP_REQUEST_WEATHER})
    @GET("onebox/weather/query")
    Observable<BaseResponseBody<Weather>> queryWeather(@Query("cityname") String cityName);

    @Headers({HttpConfig.HTTP_REQUEST_TYPE_KEY + ":" + HttpConfig.HTTP_REQUEST_QR_CODE})
    @GET("qrcode/api")
    Observable<BaseResponseBody<QrCode>> createQrCode(@Query("text") String text, @Query("w") int width);

    @Headers({HttpConfig.HTTP_REQUEST_TYPE_KEY + ":" + HttpConfig.HTTP_REQUEST_NEWS})
    @GET("toutiao/index")
    Observable<BaseResponseBody<NewsPack>> getNews();

    @GET("leavesC/test1")
    Observable<BaseResponseBody<String>> test1();

    @GET("leavesC/test2")
    Observable<BaseResponseBody<String>> test2();

}