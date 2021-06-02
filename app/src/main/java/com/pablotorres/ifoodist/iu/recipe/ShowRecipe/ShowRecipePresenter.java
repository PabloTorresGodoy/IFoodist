package com.pablotorres.ifoodist.iu.recipe.ShowRecipe;

import com.pablotorres.ifoodist.data.model.Recipe;

public class ShowRecipePresenter implements ShowRecipeContract.Presenter, ShowRecipeInteractorImpl.ShowRecetaInteractor{

    private ShowRecipeInteractorImpl interactor;
    private ShowRecipeContract.View view;

    public ShowRecipePresenter(ShowRecipeContract.View view){
        this.interactor = new ShowRecipeInteractorImpl(this);
        this.view = view;
    }

    @Override
    public void delete(Recipe recipe) {
        interactor.delete(recipe);
    }

    @Override
    public void OnSuccessDeleted() {
        view.onSuccessDeleted();
    }
}
