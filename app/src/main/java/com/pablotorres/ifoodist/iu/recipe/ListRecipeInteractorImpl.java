package com.pablotorres.ifoodist.iu.recipe;

import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.data.repository.RecipeRepository;

import java.util.List;

public class ListRecipeInteractorImpl {
    private ListRecipeInteractor callback;

    public ListRecipeInteractorImpl(ListRecipeInteractor callback){
        this.callback = callback;
    }

    public void delete(Recipe deleted){
        RecipeRepository.getInstance().delete(deleted);
        callback.OnSuccessDeleted(deleted);
    }

    public interface ListRecipeInteractor{
        void OnNoData();
        void OnSuccess(List<Recipe> list);
        void OnSuccessDeleted(Recipe deleted);
    }

    public void load(){
        List<Recipe> list = RecipeRepository.getInstance().getList();
        if(list.isEmpty())
            callback.OnNoData();
        else
            callback.OnSuccess(list);
    }
}
