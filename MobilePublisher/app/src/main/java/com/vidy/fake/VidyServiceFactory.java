package com.vidy.fake;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jake on 3/8/2018.
 */

public class VidyServiceFactory {

    private static VidyService vidyService = null;

    public static VidyService getVidyService() {
        if (vidyService == null) {
            synchronized (VidyServiceFactory.class) {
                if (vidyService == null) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.vidy.sh/")
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    vidyService = retrofit.create(VidyService.class);
                }
            }
        }

        return vidyService;
    }

}
