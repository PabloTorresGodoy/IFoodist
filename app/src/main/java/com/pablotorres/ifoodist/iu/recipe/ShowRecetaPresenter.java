package com.pablotorres.ifoodist.iu.recipe;

import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.data.repository.Account;

public class ShowRecetaPresenter implements ShowRecetaContract.Presenter, ShowRecetaInteractorImpl.ShowRecetaInteractor{

    private ShowRecetaInteractorImpl interactor;
    private ShowRecetaContract.View view;

    public ShowRecetaPresenter(ShowRecetaContract.View view){
        this.interactor = new ShowRecetaInteractorImpl(this);
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
