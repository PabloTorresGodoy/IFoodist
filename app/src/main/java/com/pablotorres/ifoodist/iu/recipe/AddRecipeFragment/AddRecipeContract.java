package com.pablotorres.ifoodist.iu.recipe.AddRecipeFragment;

import com.pablotorres.ifoodist.data.model.Recipe;

import java.util.HashMap;
import java.util.Map;

public interface AddRecipeContract {
    interface View{
        void onSuccessSave();
    }

    interface Presenter{
        void save(Map<String, Object> recipe);
    }
}
