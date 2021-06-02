package com.pablotorres.ifoodist.iu.recipe.ShowRecipe;

import com.google.firebase.firestore.FirebaseFirestore;
import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.data.repository.Account;

public class ShowRecipeInteractorImpl {
    private ShowRecipeInteractorImpl.ShowRecetaInteractor callback;
    private FirebaseFirestore db;

    public ShowRecipeInteractorImpl(ShowRecipeInteractorImpl.ShowRecetaInteractor callback){
        this.callback = callback;
        db = FirebaseFirestore.getInstance();
    }

    public interface ShowRecetaInteractor{
        void OnSuccessDeleted();
    }

    public void delete(Recipe recipe){
        db.collection(Account.getInstance().getUser()).document(recipe.getId()).delete();
        callback.OnSuccessDeleted();
    }
}
