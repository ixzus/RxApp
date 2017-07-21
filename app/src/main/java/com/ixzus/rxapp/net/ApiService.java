package com.ixzus.rxapp.net;

import com.ixzus.rxapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by huan on 2017/7/19.
 */

public class ApiService {
    private static ApiService SERVICE;
    private static final int DEFAULT_TIMEOUT = 10000;

    public static ApiService getInstance() {
        return getInstance(DEFAULT_TIMEOUT);
    }

    public static ApiService getInstance(int connectTimeout) {
        if (SERVICE == null || connectTimeout > 0) {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            /**超时*/
            httpClientBuilder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
            httpClientBuilder.readTimeout(connectTimeout, TimeUnit.SECONDS);
            httpClientBuilder.writeTimeout(connectTimeout, TimeUnit.SECONDS);
            /**错误重连*/
            httpClientBuilder.retryOnConnectionFailure(false);
            /**日志*/
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG) {
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            }
            httpClientBuilder.addInterceptor(loggingInterceptor);

            httpClientBuilder.addInterceptor(chain -> {
                Request request = chain.request();
                Response response = chain.proceed(request);
                return response.newBuilder().header("key1", "value1").build();
            });
            SERVICE = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("")
                    .build().create(ApiService.class);
        }
        return SERVICE;
    }

}
