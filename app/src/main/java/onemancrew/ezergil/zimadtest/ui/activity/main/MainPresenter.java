package onemancrew.ezergil.zimadtest.ui.activity.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import onemancrew.ezergil.zimadtest.ui.activity.main.fragment.AnimalsFragment;
import onemancrew.ezergil.zimadtest.ui.base.BasePresenter;

import static onemancrew.ezergil.zimadtest.ui.activity.main.fragment.AnimalsPresenter.TYPE_ANIMAL_CAT;
import static onemancrew.ezergil.zimadtest.ui.activity.main.fragment.AnimalsPresenter.TYPE_ANIMAL_DOG;

public class MainPresenter extends BasePresenter<MainView> {

    private static final String TAG_CATS = "cats_list";
    private static final String TAG_DOGS = "dogs_list";
    private static final int POSITION_CATS = 0;
    private static final int POSITION_DOGS = 1;

    public MainPresenter(MainView view) {
        onAttach(view);
    }

    public void initData(FragmentManager fragmentManager, int containerViewId) {
        if (!isFragmentsAdded(fragmentManager)) {
            Fragment dogs = getDogsFragment();
            Fragment cats = getCatsFragment();
            fragmentManager
                    .beginTransaction()
                    .add(containerViewId, dogs, TAG_DOGS)
                    .hide(dogs)
                    .add(containerViewId, cats, TAG_CATS)
                    .commit();
        }
    }

    private Fragment getDogsFragment() {
        return AnimalsFragment.newInstance(new AnimalsFragment.ArgsBuilder().putAnimalType(TYPE_ANIMAL_DOG));
    }

    private Fragment getCatsFragment() {
        return AnimalsFragment.newInstance(new AnimalsFragment.ArgsBuilder().putAnimalType(TYPE_ANIMAL_CAT));
    }

    public void onTabClick(FragmentManager fragmentManager, int position) {
        Fragment toShow;
        Fragment toHide;
        switch (position) {
            case POSITION_CATS:
                toShow = fragmentManager.findFragmentByTag(TAG_CATS);
                toHide = fragmentManager.findFragmentByTag(TAG_DOGS);
                break;
            case POSITION_DOGS:
                toShow = fragmentManager.findFragmentByTag(TAG_DOGS);
                toHide = fragmentManager.findFragmentByTag(TAG_CATS);
                break;
            default:
                throw new IllegalArgumentException("Wrong tab position! It can be only in range 0..1");
        }
        if (toShow != null && toHide != null)
            fragmentManager
                    .beginTransaction()
                    .hide(toHide)
                    .show(toShow)
                    .commit();
    }

    private boolean isFragmentsAdded(FragmentManager fragmentManager) {
        return fragmentManager.findFragmentByTag(TAG_CATS) != null && fragmentManager.findFragmentByTag(TAG_DOGS) != null;
    }
}