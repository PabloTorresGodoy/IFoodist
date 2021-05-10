package com.pablotorres.ifoodist.iu.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.pablotorres.ifoodist.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommonUtils {

    /**
     * Método estatico que comprueba si el password cumple el siguiente patron:
     * -Tiene minimo 8 caracteres y maximo 12
     * -Contiene una mayuscula
     * -Contiene un numero
     * -Contiene una minuscula
     * @param password
     * @return
     */
    public static boolean isPasswordValid(String password){
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,12}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     *Metodo que comprueba el formato del email
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
