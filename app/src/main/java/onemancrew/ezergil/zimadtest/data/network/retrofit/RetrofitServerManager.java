package onemancrew.ezergil.zimadtest.data.network.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static onemancrew.ezergil.zimadtest.data.network.retrofit.ApiConstants.BASE_URL;

public class RetrofitServerManager {

    private static RetrofitServerManager instance = null;
    private static Api api = null;

    private RetrofitServerManager() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        api = retrofit.create(Api.class);
    }

    public static RetrofitServerManager getInstance() {
        if (instance == null) {
            instance = new RetrofitServerManager();
        }
        return instance;
    }

    public Api getService() {
        return api;
    }
}
