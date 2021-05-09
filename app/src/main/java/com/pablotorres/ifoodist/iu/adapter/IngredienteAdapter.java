package com.pablotorres.ifoodist.iu.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.data.model.Recipe;

import java.util.Collections;
import java.util.List;

public class IngredienteAdapter extends RecyclerView.Adapter<IngredienteAdapter.ViewHolder>{

   private List<String> list;


    public IngredienteAdapter(List<String> list) {
        this.list = list;
    }

    public void delete(String deleted){
        this.list.remove(deleted);
        notifyDataSetChanged();
    }

    public void update(List<String> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void insertar(String ingrediente){
        this.list.add(ingrediente);
        notifyDataSetChanged();
    }

    public List<String> getList(){
        return list;
    }

    @NonNull
    @Override
    public IngredienteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingrediente,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredienteAdapter.ViewHolder holder, int position) {
        holder.tvIngredienteItem.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

       TextView tvIngredienteItem;
       ImageButton btDelete;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           tvIngredienteItem = itemView.findViewById(R.id.tvIngredienteItem);
           btDelete = itemView.findViewById(R.id.btDelete);
           btDelete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   delete(list.get(getAdapterPosition()));
               }
           });
       }
   }
}
