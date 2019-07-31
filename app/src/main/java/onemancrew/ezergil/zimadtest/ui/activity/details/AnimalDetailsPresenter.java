package onemancrew.ezergil.zimadtest.ui.activity.details;

import android.content.Intent;
import android.os.Bundle;

import onemancrew.ezergil.zimadtest.data.model.AnimalModel;
import onemancrew.ezergil.zimadtest.ui.base.BasePresenter;

public class AnimalDetailsPresenter extends BasePresenter<AnimalDetailsView> {

    public static final String KEY_ANIMAL_MODEL = "model";
    public static final String KEY_ANIMAL_NUMBER = "number";

    private AnimalModel model;
    private int number;

    public AnimalDetailsPresenter(AnimalDetailsView view, Intent intent) {
        onAttach(view);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            model = extras.getParcelable(KEY_ANIMAL_MODEL);
            number = extras.getInt(KEY_ANIMAL_NUMBER);
        }
    }

    public void getData() {
        if (model != null) {
            getView().showDetails(model, number);
        } else {
            getView().onError("Something went wrong!");
        }
    }
}
