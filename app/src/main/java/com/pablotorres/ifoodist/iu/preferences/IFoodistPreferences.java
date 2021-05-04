package com.pablotorres.ifoodist.iu.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.iu.IFoodistActivity;

/**
 * Clase que gestiona el acceso a las preferencias de la aplicación
 */
public class IFoodistPreferences {

    private Context context;
    private static IFoodistPreferences instance;

    private IFoodistPreferences(Context context){
        this.context = context;
    }

    static public void newInstance (Context context){
        if(instance==null)
            instance=new IFoodistPreferences(context);
    }

    static public IFoodistPreferences getInstance(){
        return instance;
    }

    /**
     * Método que escribe en el fichero preferencias la informacion del usuario
     * @param name
     * @param password
     * @return
     */

    public boolean putUser(String name, String password){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.key_user),name);
        editor.putString(context.getResources().getString(R.string.key_password),password);
        return editor.commit();
    }
}
