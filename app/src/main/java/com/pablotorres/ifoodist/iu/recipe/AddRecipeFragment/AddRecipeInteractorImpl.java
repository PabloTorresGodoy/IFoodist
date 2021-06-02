package com.pablotorres.ifoodist.iu.recipe.AddRecipeFragment;

import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.data.repository.Account;
import com.pablotorres.ifoodist.iu.recipe.ShowRecipe.ShowRecipeInteractorImpl;

import java.util.HashMap;
import java.util.Map;

public class AddRecipeInteractorImpl {
    private AddRecipeInteractorImpl.AddRecetaInteractor callback;
    private FirebaseFirestore db;

    public AddRecipeInteractorImpl(AddRecipeInteractorImpl.AddRecetaInteractor callback){
        this.callback = callback;
        db = FirebaseFirestore.getInstance();
    }

    public interface AddRecetaInteractor{
        void OnSuccessSave();
    }

    public void save(Map<String, Object> recipe){
        db.collection(Account.getInstance().getUser()).document().set(recipe).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                callback.OnSuccessSave();
            }
        });
    }
}
