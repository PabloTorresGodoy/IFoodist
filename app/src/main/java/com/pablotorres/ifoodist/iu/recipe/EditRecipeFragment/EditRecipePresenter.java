package com.pablotorres.ifoodist.iu.recipe.EditRecipeFragment;

import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.iu.recipe.AddRecipeFragment.AddRecipeContract;
import com.pablotorres.ifoodist.iu.recipe.AddRecipeFragment.AddRecipeInteractorImpl;

import java.util.Map;

public class EditRecipePresenter implements EditRecipeContract.Presenter, EditRecipeInteractorImpl.EditRecetaInteractor{
    private EditRecipeInteractorImpl interactor;
    private EditRecipeContract.View view;

    public EditRecipePresenter(EditRecipeContract.View view){
        this.interactor = new EditRecipeInteractorImpl(this);
        this.view = view;
    }

    @Override
    public void update(Map<String, Object> recetaUpdate, Recipe recipe) {
        interactor.save(recetaUpdate, recipe);
    }

    @Override
    public void OnSuccessUpdate() {
        view.onSuccessUpdate();
    }
}
