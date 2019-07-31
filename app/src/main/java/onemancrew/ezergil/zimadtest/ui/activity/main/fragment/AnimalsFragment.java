package onemancrew.ezergil.zimadtest.ui.activity.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import onemancrew.ezergil.zimadtest.R;
import onemancrew.ezergil.zimadtest.data.model.AnimalModel;
import onemancrew.ezergil.zimadtest.data.network.retrofit.RESTClient;
import onemancrew.ezergil.zimadtest.data.network.retrofit.RetrofitServerManager;
import onemancrew.ezergil.zimadtest.interfaces.OnItemClickListener;
import onemancrew.ezergil.zimadtest.ui.adapter.animals.AnimalsAdapter;
import onemancrew.ezergil.zimadtest.ui.base.BaseFragment;

import static onemancrew.ezergil.zimadtest.ui.activity.main.fragment.AnimalsPresenter.KEY_ANIMAL_TYPE;

public class AnimalsFragment extends BaseFragment implements AnimalsView {

    private RecyclerView animalList;
    private AnimalsAdapter adapter;
    private AnimalsPresenter presenter;

    public static AnimalsFragment newInstance(ArgsBuilder builder) {
        AnimalsFragment fragment = new AnimalsFragment();
        fragment.setArguments(builder.build());
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_animals;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = savedInstanceState != null ? savedInstanceState : getArguments();
        presenter = new AnimalsPresenter(new RESTClient(RetrofitServerManager.getInstance()), args, this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putAll(presenter.saveInstanceState());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initUi(View parent) {
        initViews(parent);
    }

    private void initViews(View parent) {
        animalList = parent.findViewById(R.id.animalList);
        setupAnimalsList();
        presenter.loadData();
    }

    private void setupAnimalsList() {
        adapter = new AnimalsAdapter(new OnItemClickListener<AnimalModel>() {
            @Override
            public void onItemCLick(AnimalModel item, int position) {
                presenter.onAnimalItemClicked(item, position, getContext());
            }
        });
        animalList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        animalList.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onAttach(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onDetach();
    }

    @Override
    public void onDataLoaded(List<AnimalModel> animals) {
        adapter.setData(animals);
    }

    @Override
    public void openAnimalDetails(Intent intent) {
        startActivity(intent);
    }

    @Override
    public ArrayList<AnimalModel> getLoadedData() {
        return adapter.getData();
    }

    @Override
    public int getScrollPosition() {
        return animalList.getScrollY();
    }

    @Override
    public void restoreScrollPosition(int scrollY) {
        animalList.scrollTo(0, scrollY);
    }

    public static class ArgsBuilder {

        private String animalType;

        public ArgsBuilder putAnimalType(String animalType) {
            this.animalType = animalType;
            return this;
        }

        public Bundle build() {
            Bundle args = new Bundle();
            args.putString(KEY_ANIMAL_TYPE, animalType);
            return args;
        }
    }
}