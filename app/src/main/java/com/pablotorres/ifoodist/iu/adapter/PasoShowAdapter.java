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

public class PasoShowAdapter extends RecyclerView.Adapter<PasoShowAdapter.ViewHolder>{

    private List<String> list;
    private int contador = 0;

    public PasoShowAdapter(List<String> list) {
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
    public PasoShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paso,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasoShowAdapter.ViewHolder holder, int position) {
        String paso = ++contador+". "+ list.get(position);
        holder.tvPasoItem.setText(paso);
        holder.btDelete.setVisibility(View.GONE);
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
