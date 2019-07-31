package onemancrew.ezergil.zimadtest.ui.activity.details;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import onemancrew.ezergil.zimadtest.R;
import onemancrew.ezergil.zimadtest.data.model.AnimalModel;
import onemancrew.ezergil.zimadtest.ui.base.BaseActivity;

import static onemancrew.ezergil.zimadtest.ui.activity.details.AnimalDetailsPresenter.KEY_ANIMAL_MODEL;
import static onemancrew.ezergil.zimadtest.ui.activity.details.AnimalDetailsPresenter.KEY_ANIMAL_NUMBER;

public class AnimalDetailsActivity extends BaseActivity implements AnimalDetailsView {

    private ImageView image;
    private TextView number;
    private TextView name;

    private AnimalDetailsPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_animal_details;
    }

    @Override
    protected void initUi() {
        presenter = new AnimalDetailsPresenter(this, getIntent());
        findViews();
        presenter.getData();
    }

    public static Intent newInstance(Context context, AnimalModel model, int number) {
        Intent intent = new Intent(context, AnimalDetailsActivity.class);
        intent.putExtra(KEY_ANIMAL_MODEL, model);
        intent.putExtra(KEY_ANIMAL_NUMBER, number);
        return intent;
    }

    private void findViews() {
        image = findViewById(R.id.image);
        number = findViewById(R.id.num);
        name = findViewById(R.id.name);
    }

    @Override
    public void showDetails(AnimalModel model, int number) {
        Picasso.get().load(model.getUrl()).into(image);
        this.number.setText(String.valueOf(number));
        name.setText(model.getTitle());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}