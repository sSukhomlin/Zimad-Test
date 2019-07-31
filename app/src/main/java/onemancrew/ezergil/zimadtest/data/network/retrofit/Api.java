package onemancrew.ezergil.zimadtest.data.network.retrofit;

import onemancrew.ezergil.zimadtest.data.network.response.AnimalsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static onemancrew.ezergil.zimadtest.data.network.retrofit.ApiConstants.API;
import static onemancrew.ezergil.zimadtest.data.network.retrofit.ApiConstants.QUERY;

public interface Api {

    @GET(API)
    Call<AnimalsResponse> getAnimals(@Query(QUERY) String query);
}