package onemancrew.ezergil.zimadtest.ui.adapter.binding;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class BindingsAdapter {

    @BindingAdapter({"app:url"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @BindingAdapter({"app:cell_number"})
    public static void setCellNumber(TextView view, int number) {
        view.setText(String.valueOf(number));
    }
}
