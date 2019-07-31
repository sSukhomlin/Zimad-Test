package onemancrew.ezergil.zimadtest.ui.adapter.animals;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import onemancrew.ezergil.zimadtest.R;
import onemancrew.ezergil.zimadtest.data.model.AnimalModel;
import onemancrew.ezergil.zimadtest.databinding.ItemAnimalBinding;
import onemancrew.ezergil.zimadtest.interfaces.OnItemClickListener;

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalViewHolder> {

    private ArrayList<AnimalModel> animalsList = new ArrayList<>();

    private OnItemClickListener<AnimalModel> listener;

    public AnimalsAdapter(OnItemClickListener<AnimalModel> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAnimalBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_animal, parent, false);
        return new AnimalViewHolder(dataBinding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        holder.bind(animalsList.get(position));
    }

    @Override
    public int getItemCount() {
        return animalsList.size();
    }

    public void setData(List<AnimalModel> newAnimals) {
        animalsList.clear();
        animalsList.addAll(newAnimals);
        notifyDataSetChanged();
    }

    public ArrayList<AnimalModel> getData() {
        return animalsList;
    }
}
