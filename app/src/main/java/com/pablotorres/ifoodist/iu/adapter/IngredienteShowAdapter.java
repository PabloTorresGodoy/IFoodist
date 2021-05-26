package com.pablotorres.ifoodist.iu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablotorres.ifoodist.R;

import java.util.List;

public class IngredienteShowAdapter extends RecyclerView.Adapter<IngredienteShowAdapter.ViewHolder>{

    private List<String> list;
    private int contador = 0;

    public IngredienteShowAdapter(List<String> list) {
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

    public void insertar(String paso){
        this.list.add(paso);
        notifyDataSetChanged();
    }

    public List<String> getList(){
        return list;
    }

    @NonNull
    @Override
    public IngredienteShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_ingrediente,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredienteShowAdapter.ViewHolder holder, int position) {
        String paso = ++contador+". "+ list.get(position);
        holder.tvIngredienteItem.setText(paso);
        holder.btDelete.setVisibility(View.GONE);
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
