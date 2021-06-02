package com.pablotorres.ifoodist.iu.recipe.EditRecipeFragment;

import com.pablotorres.ifoodist.data.model.Recipe;

import java.util.Map;

public interface EditRecipeContract {
    interface View{
        void onSuccessUpdate();
    }

    interface Presenter{
        void update(Map<String, Object> recetaUpdate, Recipe recipe);
    }
}
