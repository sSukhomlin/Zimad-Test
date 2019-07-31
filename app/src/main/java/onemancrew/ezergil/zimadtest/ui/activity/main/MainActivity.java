package onemancrew.ezergil.zimadtest.ui.activity.main;

import com.google.android.material.tabs.TabLayout;

import onemancrew.ezergil.zimadtest.R;
import onemancrew.ezergil.zimadtest.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainView {

    private TabLayout tabs;

    private MainPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUi() {
        presenter = new MainPresenter(this);
        initViews();
        initListeners();
        initData();
    }

    private void initViews() {
        tabs = findViewById(R.id.tabs);
    }

    private void initListeners() {
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                presenter.onTabClick(getSupportFragmentManager(), tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                /*Do nothing*/
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                /*Do nothing*/
            }
        });
    }

    private void initData() {
        presenter.initData(getSupportFragmentManager(), R.id.container);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}