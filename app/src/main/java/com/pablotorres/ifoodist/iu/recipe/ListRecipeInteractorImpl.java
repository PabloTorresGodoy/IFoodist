package com.pablotorres.ifoodist.iu.recipe;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.data.repository.Account;
import com.pablotorres.ifoodist.data.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class ListRecipeInteractorImpl {
    private ListRecipeInteractor callback;
    private FirebaseFirestore db;

    public ListRecipeInteractorImpl(ListRecipeInteractor callback){
        this.callback = callback;
        db = FirebaseFirestore.getInstance();
    }

    public void delete(Recipe deleted){
        db.collection(Account.getInstance().getUser()).document(deleted.getId()).delete();
//        RecipeRepository.getInstance().delete(deleted);
        load();
        callback.OnSuccessDeleted(deleted);
    }

    public interface ListRecipeInteractor{
        void OnNoData();
        void OnSuccess(List<Recipe> list);
        void OnSuccessDeleted(Recipe deleted);
    }

    public void load(){
        List<Recipe> list = new ArrayList<>();

        db.collection(Account.getInstance().getUser()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document: task.getResult()){
                        Recipe recipe = new Recipe(document.getId(), document.getString("nombre"), document.getString("categoria"), document.getString("duracion"), document.getString("cantidad"), (List)document.get("ingredientes"), (List)document.get("pasos"), document.getBoolean("favorito"));
                        list.add(recipe);
                    }

                    Collections.sort(list);

                    if(list.isEmpty())
                        callback.OnNoData();
                    else{}
                        callback.OnSuccess(list);
                }
            }
        });
    }
}
