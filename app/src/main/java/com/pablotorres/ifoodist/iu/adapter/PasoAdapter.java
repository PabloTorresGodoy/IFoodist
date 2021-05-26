package com.pablotorres.ifoodist.iu.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
        holder.edPasoItem.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

       EditText edPasoItem;
       ImageButton btDelete;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           edPasoItem = itemView.findViewById(R.id.edPasoItem);
           edPasoItem.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               }

               @Override
               public void onTextChanged(CharSequence s, int start, int before, int count) {
                   list.remove(getAdapterPosition());
                   list.add(getAdapterPosition(), s.toString());
               }

               @Override
               public void afterTextChanged(Editable s) {

               }
           });
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
