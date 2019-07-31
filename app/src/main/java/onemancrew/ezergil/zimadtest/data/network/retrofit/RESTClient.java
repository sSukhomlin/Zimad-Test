package onemancrew.ezergil.zimadtest.data.network.retrofit;

import onemancrew.ezergil.zimadtest.data.network.response.AnimalsResponse;
import retrofit2.Callback;

import static onemancrew.ezergil.zimadtest.data.network.retrofit.ApiConstants.QUERY_CATS;
import static onemancrew.ezergil.zimadtest.data.network.retrofit.ApiConstants.QUERY_DOGS;

public class RESTClient {

    private RetrofitServerManager serverManager;

    public RESTClient(RetrofitServerManager serverManager) {
        this.serverManager = serverManager;
    }

    public void getCats(Callback<AnimalsResponse> callback) {
        serverManager.getService().getAnimals(QUERY_CATS).enqueue(callback);
    }

    public void getDogs(Callback<AnimalsResponse> callback) {
        serverManager.getService().getAnimals(QUERY_DOGS).enqueue(callback);
    }
}
