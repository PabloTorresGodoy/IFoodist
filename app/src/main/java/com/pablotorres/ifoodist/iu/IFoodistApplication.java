package com.pablotorres.ifoodist.iu;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.pablotorres.ifoodist.R;
//import com.pablotorres.ifoodist.data.IfoodistDataBase;
import com.pablotorres.ifoodist.iu.preferences.IFoodistPreferences;

public class IFoodistApplication extends Application {

    public static final String CHANNEL_ID="256";

    @Override
    public void onCreate() {
        super.onCreate();
//        IfoodistDataBase.create(this);
        IFoodistPreferences.newInstance(this);
        createNotificationChannel();
    }

    private void createNotificationChannel(){
        //Vamos a crear la clase NotificationChannel pero solo para API 26+ porque la clase NotificationChannel no esta
        //en la libreria de soporte
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name=getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name,importance);

            //Se registra el canal en el sistema y una vez que se ha registrado no se puede cambiar
            //la importancia o bien otra configuracion que se haya establecido
            notificationChannel.enableLights(true);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }

}