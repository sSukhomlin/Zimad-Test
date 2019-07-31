package onemancrew.ezergil.zimadtest.ui.base;

public abstract class BasePresenter<T extends BaseView> {

    private T view;

    public void onAttach(T view) {
        this.view = view;
    }

    public void onDetach() {
        view = null;
    }

    protected T getView() {
        return view;
    }
}
