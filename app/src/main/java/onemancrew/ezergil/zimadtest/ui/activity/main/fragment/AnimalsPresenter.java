package onemancrew.ezergil.zimadtest.ui.activity.main.fragment;

import android.content.Context;
import android.os.Bundle;

import onemancrew.ezergil.zimadtest.data.model.AnimalModel;
import onemancrew.ezergil.zimadtest.data.network.response.AnimalsResponse;
import onemancrew.ezergil.zimadtest.data.network.retrofit.RESTClient;
import onemancrew.ezergil.zimadtest.ui.activity.details.AnimalDetailsActivity;
import onemancrew.ezergil.zimadtest.ui.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimalsPresenter extends BasePresenter<AnimalsView> {

    public static final String KEY_ANIMAL_TYPE = "animal_type";
    public static final String TYPE_ANIMAL_CAT = "cat";
    public static final String TYPE_ANIMAL_DOG = "dog";

    private static final String KEY_IS_RESTORED = "is_restored";
    private static final String KEY_ANIMALS_LIST = "animals_list";
    private static final String KEY_SCROLL_POSITION = "scroll_position";

    private String animalType;
    private boolean isRestored = false;
    private Bundle argsToRestore;

    private RESTClient restClient;

    public AnimalsPresenter(RESTClient restClient, Bundle args, AnimalsView view) {
        this.restClient = restClient;
        if (args != null) {
            animalType = args.getString(KEY_ANIMAL_TYPE);
            isRestored = args.getBoolean(KEY_IS_RESTORED, false);
            if (isRestored) argsToRestore = args;
        }
        onAttach(view);
    }

    public void loadData() {
        if (isRestored) {
            restoreState();
        } else {
            getDataFromServer();
        }
    }

    private void restoreState() {
        getView().onDataLoaded(argsToRestore.<AnimalModel>getParcelableArrayList(KEY_ANIMALS_LIST));
        getView().restoreScrollPosition(argsToRestore.getInt(KEY_SCROLL_POSITION));
        argsToRestore.clear();
        argsToRestore = null;
    }

    private void getDataFromServer() {
        if (animalType.isEmpty()) {
            getView().onError("Can't get data for \'empty\' animal");
        } else {
            getView().showProgress();
            switch (animalType) {
                case TYPE_ANIMAL_CAT:
                    restClient.getCats(getCallback());
                    break;
                case TYPE_ANIMAL_DOG:
                    restClient.getDogs(getCallback());
                    break;
                default:
                    throw new IllegalArgumentException("Wrong animal type!");
            }
        }
    }

    public void onAnimalItemClicked(AnimalModel item, int position, Context context) {
        getView().openAnimalDetails(AnimalDetailsActivity.newInstance(context, item, position + 1));
    }

    private Callback<AnimalsResponse> getCallback() {
        return new Callback<AnimalsResponse>() {
            @Override
            public void onResponse(Call<AnimalsResponse> call, Response<AnimalsResponse> response) {
                if (response.isSuccessful()) {
                    AnimalsResponse animalsResponse = response.body();
                    if (animalsResponse != null) {
                        getView().onDataLoaded(animalsResponse.getData());
                    }
                } else {
                    getView().onError("Something went wrong");
                }
                getView().hideProgress();
            }

            @Override
            public void onFailure(Call<AnimalsResponse> call, Throwable t) {
                getView().onError(t.getMessage());
                getView().hideProgress();
            }
        };
    }

    public Bundle saveInstanceState() {
        Bundle args = new Bundle();
        args.putString(KEY_ANIMAL_TYPE, animalType);
        args.putBoolean(KEY_IS_RESTORED, true);
        args.putParcelableArrayList(KEY_ANIMALS_LIST, getView().getLoadedData());
        args.putInt(KEY_SCROLL_POSITION, getView().getScrollPosition());
        return args;
    }
}
