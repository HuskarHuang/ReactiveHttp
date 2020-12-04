package leavesc.hello.network.http.basis.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;

import leavesc.hello.network.http.basis.config.HttpConfig;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：leavesC
 * 时间：2018/10/28 9:31
 * 描述：
 * GitHub：https://github.com/leavesC
 */
public class FilterInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl.Builder httpBuilder = originalRequest.url().newBuilder();
        Headers headers = originalRequest.headers();
        if (headers.size() > 0) {
            String requestType = headers.get(HttpConfig.HTTP_REQUEST_TYPE_KEY);
            if (requestType != null && requestType.length() > 0) {
                switch (requestType) {
                    case HttpConfig.HTTP_REQUEST_WEATHER: {
                        httpBuilder.addQueryParameter(HttpConfig.KEY, HttpConfig.KEY_WEATHER);
                        break;
                    }
                    case HttpConfig.HTTP_REQUEST_QR_CODE: {
                        httpBuilder.addQueryParameter(HttpConfig.KEY, HttpConfig.KEY_QR_CODE);
                        break;
                    }
                    case HttpConfig.HTTP_REQUEST_NEWS: {
                        httpBuilder.addQueryParameter(HttpConfig.KEY, HttpConfig.KEY_NEWS);
                        break;
                    }
                }
            }
        }
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .removeHeader(HttpConfig.HTTP_REQUEST_TYPE_KEY)
                .url(httpBuilder.build());
        return chain.proceed(requestBuilder.build());
    }

}
