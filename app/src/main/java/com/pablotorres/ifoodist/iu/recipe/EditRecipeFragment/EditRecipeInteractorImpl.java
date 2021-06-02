package com.pablotorres.ifoodist.iu.recipe.EditRecipeFragment;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.data.repository.Account;
import com.pablotorres.ifoodist.iu.recipe.AddRecipeFragment.AddRecipeInteractorImpl;

import java.util.Map;

public class EditRecipeInteractorImpl {
    private EditRecipeInteractorImpl.EditRecetaInteractor callback;
    private FirebaseFirestore db;

    public EditRecipeInteractorImpl(EditRecipeInteractorImpl.EditRecetaInteractor callback){
        this.callback = callback;
        db = FirebaseFirestore.getInstance();
    }

    public interface EditRecetaInteractor{
        void OnSuccessUpdate();
    }

    public void save(Map<String, Object> recetaUpdate, Recipe recipe){
        db.collection(Account.getInstance().getUser()).document(recipe.getId()).update(recetaUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callback.OnSuccessUpdate();
            }
        });
    }
}
