package com.pablotorres.ifoodist.iu.recipe;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.data.repository.RecipeRepository;
import com.pablotorres.ifoodist.iu.IFoodistApplication;

import java.util.Random;

public class AddRecetaFragment extends Fragment {

    private Spinner spinner;
    private FloatingActionButton fab;
    private TextInputEditText tieNombre;
    private TextInputEditText tieDuracion;
    private TextInputEditText tieCantidad;
    private EditText edIngredientes;
    private EditText edPasos;
    private Recipe recipe;


    public AddRecetaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_receta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tieNombre=view.findViewById(R.id.tieShowNombre);
        tieCantidad=view.findViewById(R.id.tieShowCantidad);
        tieDuracion=view.findViewById(R.id.tieShowDuracion);
        edIngredientes = view.findViewById(R.id.edShowIngredientes);
        edPasos = view.findViewById(R.id.edShowPasos);

        spinner = view.findViewById(R.id.spShowCategoria);
        fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setImageDrawable(getResources().getDrawable(R.drawable.tic));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recipe= new Recipe(tieNombre.getText().toString(),spinner.getSelectedItem().toString(),tieDuracion.getText().toString(),tieCantidad.getText().toString(),edIngredientes.getText().toString(),edPasos.getText().toString(), false);
                RecipeRepository.getInstance().add(recipe);
                showNotification();
                NavHostFragment.findNavController(AddRecetaFragment.this).navigateUp();
            }
        });
    }

    private void showNotification(){
        //Una PendingIntent tienen un objeto intent en su interior que define lo que
        //se quiere ejecutar cuando se pulse sobre la notificacion
        //INICIAR UNA ACTIVITY
        //Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("recipe",recipe);
        //intent.putExtras(bundle);

        //Se construye el PendingIntent
        //PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), new Random().nextInt(1000),intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.addRecetaFragment)
                .setArguments(bundle)
                .createPendingIntent();


        Notification.Builder builder = new Notification.Builder(getActivity(), IFoodistApplication.CHANNEL_ID)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_logo)
                .setContentTitle(getResources().getString(R.string.notification_title_recipe))
                .setContentText(String.format(getString(R.string.text_add_recipe)+ recipe.getNombre()))
                .setContentIntent(pendingIntent);

        //Se añade la notificacion al gestior de notificaciones
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(1000),builder.build());
    }
}