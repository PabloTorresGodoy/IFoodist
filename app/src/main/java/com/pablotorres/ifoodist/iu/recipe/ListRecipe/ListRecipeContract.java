package com.pablotorres.ifoodist.iu.recipe.ListRecipe;

import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.iu.base.BaseListView;
import com.pablotorres.ifoodist.iu.base.BasePresenter;

public interface ListRecipeContract {
    interface View extends BaseListView<Recipe> {
        void setNoData();
        void onSuccessDeleted(Recipe deleted);
    }

    interface Presenter extends BasePresenter {
        void load();
    }
}
