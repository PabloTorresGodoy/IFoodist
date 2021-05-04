package com.pablotorres.ifoodist.data.repository;

import com.pablotorres.ifoodist.data.IfoodistDataBase;
import com.pablotorres.ifoodist.data.dao.RecipeDao;
import com.pablotorres.ifoodist.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RecipeRepository {
    private List<Recipe> list;
    private static RecipeRepository repository;
    private RecipeDao recipeDao;

    static {
        repository = new RecipeRepository();
    }

    private RecipeRepository(){
        this.list = new ArrayList<>();
        //initialice();
        recipeDao= IfoodistDataBase.getDatabase().recipeDao();
    }

    private void initialice() {
        list.add(new Recipe("Ensalada", "Entrante", "15 minutos", "2 personas", "Lechuga","1. Lo cortas todo 2. Lo juntas en un bol 3. Lo aliñas", false));
        list.add(new Recipe("Pizza", "Plato Principal", "1 hora", "4 personas", "Harina" ,"1. Haces la masa 2. Le pones los ingredientes deseados 3. Lo meten el horno", false));
        list.add(new Recipe("Bocadillo", "Plato Principal", "5 minutos", "1 persona", "Pan","1. Abres el pan 2. Le pones las cosas 3. Te lo comes", false));
    }

    public static RecipeRepository getInstance(){
        return  repository;
    }

    public List<Recipe> getList(){
        try {
            list= IfoodistDataBase.databaseWriteExecutor.submit(()->recipeDao.get()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public void delete(Recipe deleted) {
        IfoodistDataBase.databaseWriteExecutor.execute(()->{
            recipeDao.delete(deleted);
        });
        //list.remove(deleted);
    }

    public void add(Recipe recipe) {
        IfoodistDataBase.databaseWriteExecutor.execute(()->{
            recipeDao.insert(recipe);
        });
        //list.add(recipe);
    }

    public void update(Recipe recipe) {
        IfoodistDataBase.databaseWriteExecutor.execute(()-> recipeDao.update(recipe));
    }
}
