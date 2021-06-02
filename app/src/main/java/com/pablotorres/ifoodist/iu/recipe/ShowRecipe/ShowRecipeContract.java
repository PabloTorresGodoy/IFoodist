package com.pablotorres.ifoodist.iu.recipe.ShowRecipe;

import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.iu.base.BaseListView;
import com.pablotorres.ifoodist.iu.base.BasePresenter;

public interface ShowRecipeContract {
    interface View{
        void onSuccessDeleted();
    }

    interface Presenter{
        void delete(Recipe recipe);
    }
}
