package com.pablotorres.ifoodist.iu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.data.model.Recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    private List<Recipe> list;
    private  OnManageRecipeListener recipeListener;


    public RecipeAdapter(List<Recipe> list, OnManageRecipeListener recipeListener) {
        this.list = list;
        this.recipeListener = recipeListener;
    }

    public void ordenar() {
        Collections.sort(list);
        notifyDataSetChanged();
    }

    public void delete(Recipe deleted){
        this.list.remove(deleted);
        notifyDataSetChanged();
    }

    public void update(List<Recipe> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void filtrar(List<Recipe> listReal, String newText) {
        List<Recipe> listTmp = new ArrayList<>();
        for(Recipe receta : listReal){
            if(receta.getNombre().contains(newText))
                listTmp.add(receta);
        }
        update(listTmp);
    }

    public interface OnManageRecipeListener{
        void onClickRecipe(Recipe recipe);
    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe,parent,false);
        return new ViewHolder(view, recipeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        holder.tvNombre.setText(list.get(position).getNombre());
        holder.tvCategoria.setText(list.get(position).getCategoria());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

       TextView tvNombre;
       TextView tvCategoria;
       ImageView imgCorazon;

       public ViewHolder(@NonNull View itemView, final OnManageRecipeListener listener) {
           super(itemView);
           tvNombre = itemView.findViewById(R.id.tvTitle);
           tvCategoria = itemView.findViewById(R.id.tvCategoria);
           imgCorazon = itemView.findViewById(R.id.imgCorazon);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   listener.onClickRecipe(list.get(getAdapterPosition()));
               }
           });

       }
   }
}
