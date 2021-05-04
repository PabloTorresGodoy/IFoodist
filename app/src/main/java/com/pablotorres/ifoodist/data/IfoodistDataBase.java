package com.pablotorres.ifoodist.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pablotorres.ifoodist.data.dao.RecipeDao;
import com.pablotorres.ifoodist.data.model.Recipe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Recipe.class}, version = 2)
public abstract class IfoodistDataBase extends RoomDatabase {


    public abstract RecipeDao recipeDao();

    private static volatile IfoodistDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static void create(final Context context){
        if (INSTANCE == null) {
            synchronized (IfoodistDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IfoodistDataBase.class, "IFoodist")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
    }

    public  static IfoodistDataBase getDatabase(){
        return INSTANCE;
    }
}


/*
    static IfoodistDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (IfoodistDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IfoodistDataBase.class, "IFoodist")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
*/