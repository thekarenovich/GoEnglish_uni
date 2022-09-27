package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.karenovich.goenglish.databinding.ItemListBinding;

import java.util.List;

import Model.Tutorials;

public class TutorialsAdapter extends RecyclerView.Adapter<TutorialsAdapter.MyViewHolder>{

    private List<Tutorials> mTutorials;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = ItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tutorials tutorials = mTutorials.get(position);
        holder.bindView(tutorials);
    }

    @Override
    public int getItemCount() {
        return mTutorials.size();
    }

    public void setTutorials(List<Tutorials> tutorials){
        this.mTutorials = tutorials;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ItemListBinding binding;

        public MyViewHolder(ItemListBinding binding){
            super(binding.getRoot());
            this.binding = binding;


        }
        public void bindView(Tutorials tutorials){
            binding.nameTxt.setText(tutorials.getTheme());
        }
    }
}
