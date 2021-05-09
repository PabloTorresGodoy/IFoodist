//package com.pablotorres.ifoodist.data.dao;
//
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.OnConflictStrategy;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import com.pablotorres.ifoodist.data.model.Recipe;
//
//import java.util.List;
//
//@Dao
//public interface RecipeDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    long insert(Recipe recipe);
//
//    @Delete
//    void delete(Recipe recipe);
//
//    @Update
//    void update(Recipe recipe);
//
//    @Query("SELECT * from recipe ORDER BY nombre ASC")
//    List<Recipe> get();
//
//}
