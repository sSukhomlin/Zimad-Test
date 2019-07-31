package onemancrew.ezergil.zimadtest.ui.activity.main.fragment;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import onemancrew.ezergil.zimadtest.data.model.AnimalModel;
import onemancrew.ezergil.zimadtest.ui.base.BaseFragmentView;

public interface AnimalsView extends BaseFragmentView {

    void onDataLoaded(List<AnimalModel> models);

    void openAnimalDetails(Intent intent);

    ArrayList<AnimalModel> getLoadedData();

    int getScrollPosition();

    void restoreScrollPosition(int scrollY);
}
