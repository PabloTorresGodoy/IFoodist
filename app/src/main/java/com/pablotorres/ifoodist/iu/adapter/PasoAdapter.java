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

public class PasoAdapter extends RecyclerView.Adapter<PasoAdapter.ViewHolder>{

   private List<String> list;


    public PasoAdapter(List<String> list) {
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
    public PasoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paso,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasoAdapter.ViewHolder holder, int position) {
        holder.tvPasoItem.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

       TextView tvPasoItem;
       ImageButton btDelete;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           tvPasoItem = itemView.findViewById(R.id.tvPasoItem);
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
