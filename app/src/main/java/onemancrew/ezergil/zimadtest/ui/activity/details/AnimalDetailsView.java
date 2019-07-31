package onemancrew.ezergil.zimadtest.ui.activity.details;

import onemancrew.ezergil.zimadtest.data.model.AnimalModel;
import onemancrew.ezergil.zimadtest.ui.base.BaseView;

public interface AnimalDetailsView extends BaseView {

    void showDetails(AnimalModel model, int number);
}
