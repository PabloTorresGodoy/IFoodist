package com.pablotorres.ifoodist.iu.recipe;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.data.repository.Account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowRecetaInteractorImpl {
    private ShowRecetaInteractorImpl.ShowRecetaInteractor callback;
    private FirebaseFirestore db;

    public ShowRecetaInteractorImpl(ShowRecetaInteractorImpl.ShowRecetaInteractor callback){
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
