package onemancrew.ezergil.zimadtest.ui.adapter.animals;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import onemancrew.ezergil.zimadtest.data.model.AnimalModel;
import onemancrew.ezergil.zimadtest.databinding.ItemAnimalBinding;
import onemancrew.ezergil.zimadtest.interfaces.OnItemClickListener;

public class AnimalViewHolder extends RecyclerView.ViewHolder {

    private ItemAnimalBinding binding;
    private OnItemClickListener<AnimalModel> listener;

    public AnimalViewHolder(ItemAnimalBinding binding, OnItemClickListener<AnimalModel> listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bind(AnimalModel animalModel) {
        binding.setAnimal(animalModel);
        binding.setNumber(getAdapterPosition() + 1);
        binding.executePendingBindings();
        setListener();
    }

    private void setListener() {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemCLick(binding.getAnimal(), getAdapterPosition());
            }
        });
    }
}
