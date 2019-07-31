package onemancrew.ezergil.zimadtest.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment implements BaseFragmentView {

    private BaseView activityCallback;

    protected abstract void initUi(View parent);

    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activityCallback = (BaseView) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityCallback = null;
    }

    @Override
    public void showProgress() {
        if (activityCallback != null) {
            activityCallback.showProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (activityCallback != null) {
            activityCallback.hideProgress();
        }
    }

    @Override
    public void onError(String message) {
        if (activityCallback != null) {
            activityCallback.onError(message);
        }
    }
}
