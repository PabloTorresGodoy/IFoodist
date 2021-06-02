package com.pablotorres.ifoodist.iu.recipe;

import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.iu.base.BaseListView;
import com.pablotorres.ifoodist.iu.base.BasePresenter;

public interface ShowRecetaContract {
    interface View{
        void onSuccessDeleted();
    }

    interface Presenter{
        void delete(Recipe recipe);
    }
}
