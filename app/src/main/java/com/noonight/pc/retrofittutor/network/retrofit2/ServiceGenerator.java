package com.noonight.pc.retrofittutor.network.retrofit2;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PC on 6/28/2017.
 */

public class ServiceGenerator {
    public static String apiBaseUrl = "http://10.0.2.2/";
    private static Retrofit retrofit;

    private static Class serviceClas;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiBaseUrl);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private ServiceGenerator() {
    }

    public static void changeApiBaseUrl(String newApiBaseUrl/*, Class classd*/) {
        apiBaseUrl = newApiBaseUrl;
        //serviceClas = classd;
        builder = (Retrofit.Builder) new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(apiBaseUrl);
                //.build()
                //.create(serviceClas);
    }

    public static <S> S createService(Class<S> serviceClass/*, AccessToken toke*/) {
        //serviceClas = serviceClass;
        /*String authToken = token.getTokenType().concat(token.getAccessToken());*/
        return builder.build().create(serviceClass);
        //return createService(serviceClass/*, authToken*/);
    }

    // more methods
    // ...
}
