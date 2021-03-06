package com.pablotorres.ifoodist.iu.recipe.ListRecipe;

import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.iu.recipe.ListRecipe.ListRecipeContract;
import com.pablotorres.ifoodist.iu.recipe.ListRecipe.ListRecipeInteractorImpl;

import java.util.List;

public class ListRecipePresenter implements ListRecipeContract.Presenter, ListRecipeInteractorImpl.ListRecipeInteractor {

    private ListRecipeInteractorImpl interactor;
    private  ListRecipeContract.View view;

    public ListRecipePresenter(ListRecipeContract.View view){
        this.interactor = new ListRecipeInteractorImpl(this);
        this.view = view;
    }

    @Override
    public void load() {
        interactor.load();
    }

    @Override
    public void onDestroy() {
        view=null;
        interactor=null;
    }

    @Override
    public void OnNoData() {
        view.setNoData();
    }

    @Override
    public void OnSuccess(List<Recipe> list) {
        view.onSuccess(list);
    }

    @Override
    public void OnSuccessDeleted(Recipe deleted) {
        view.onSuccessDeleted(deleted);
    }
}
