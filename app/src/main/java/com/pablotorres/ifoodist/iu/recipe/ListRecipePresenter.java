package com.pablotorres.ifoodist.iu.recipe;

import com.pablotorres.ifoodist.data.model.Recipe;

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
    public void delete(Recipe deleted) {
        interactor.delete(deleted);
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
