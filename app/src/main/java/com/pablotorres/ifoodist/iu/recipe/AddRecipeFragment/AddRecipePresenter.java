package com.pablotorres.ifoodist.iu.recipe.AddRecipeFragment;

import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.iu.recipe.ShowRecipe.ShowRecipeContract;
import com.pablotorres.ifoodist.iu.recipe.ShowRecipe.ShowRecipeInteractorImpl;

import java.util.HashMap;
import java.util.Map;

public class AddRecipePresenter implements AddRecipeContract.Presenter, AddRecipeInteractorImpl.AddRecetaInteractor{
    private AddRecipeInteractorImpl interactor;
    private AddRecipeContract.View view;

    public AddRecipePresenter(AddRecipeContract.View view){
        this.interactor = new AddRecipeInteractorImpl(this);
        this.view = view;
    }

    @Override
    public void save(Map<String, Object> recipe) {
        interactor.save(recipe);
    }

    @Override
    public void OnSuccessSave() {
        view.onSuccessSave();
    }
}
