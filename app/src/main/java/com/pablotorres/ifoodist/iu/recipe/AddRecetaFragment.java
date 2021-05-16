package com.pablotorres.ifoodist.iu.recipe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.data.repository.Account;
import com.pablotorres.ifoodist.data.repository.RecipeRepository;
import com.pablotorres.ifoodist.iu.adapter.IngredienteAdapter;
import com.pablotorres.ifoodist.iu.adapter.PasoAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddRecetaFragment extends Fragment {

    private Spinner spinner;
    private FloatingActionButton fab;
    private Button btGuardar;
    private RecyclerView rvIngredientes;
    private RecyclerView rvPasos;
    private TextInputEditText tieNombre;
    private TextInputLayout tilNombre;
    private TextInputEditText tieDuracion;
    private TextInputEditText tieCantidad;
    private ImageButton btAddIngrediente;
    private ImageButton btAddPaso;
    private EditText edIngredientes;
    private EditText edPasos;
    private IngredienteAdapter adapterIngrediente;
    private PasoAdapter adapterPaso;
    private ArrayList<String> prueba = new ArrayList<>();
    private Recipe recipe;
    private FirebaseFirestore db;

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

        db = FirebaseFirestore.getInstance();

        btGuardar = view.findViewById(R.id.btGuardar);
        tieNombre=view.findViewById(R.id.tieNombre);
        tieCantidad=view.findViewById(R.id.tieCantidad);
        tieDuracion=view.findViewById(R.id.tieDuracion);
        tilNombre = view.findViewById(R.id.tilNombre);
        edIngredientes = view.findViewById(R.id.edIngredientes);
        edPasos = view.findViewById(R.id.edPasos);
        spinner = view.findViewById(R.id.spCategoria);

        fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        rvIngredientes = view.findViewById(R.id.rvIngredientes);
        adapterIngrediente = new IngredienteAdapter(new ArrayList<>());
        RecyclerView.LayoutManager layoutManagerIngrediente = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false );
        rvIngredientes.setLayoutManager(layoutManagerIngrediente);
        rvIngredientes.setAdapter(adapterIngrediente);

        btAddIngrediente = view.findViewById(R.id.btAddIngrediente);
        btAddIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterIngrediente.insertar(edIngredientes.getText().toString());
                edIngredientes.setText("");
            }
        });

        rvPasos = view.findViewById(R.id.rvPasos);
        adapterPaso = new PasoAdapter(new ArrayList<>());
        RecyclerView.LayoutManager layoutManagerPaso = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false );
        rvPasos.setLayoutManager(layoutManagerPaso);
        rvPasos.setAdapter(adapterPaso);

        btAddPaso = view.findViewById(R.id.btAddPasos);
        btAddPaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterPaso.insertar(edPasos.getText().toString());
                edPasos.setText("");
            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tieNombre.getText().toString().equals("")){
                    Recipe recipe = new Recipe(tieNombre.getText().toString(), spinner.getSelectedItem().toString(), tieDuracion.getText().toString(), tieCantidad.getText().toString(), adapterIngrediente.getList(), adapterPaso.getList(), false);

                    Map<String, Object> receta = new HashMap<>();
                    receta.put("nombre", tieNombre.getText().toString());
                    receta.put("categoria", spinner.getSelectedItem().toString());
                    receta.put("duracion", tieDuracion.getText().toString());
                    receta.put("cantidad", tieCantidad.getText().toString());
                    receta.put("ingredientes", adapterIngrediente.getList());
                    receta.put("pasos", adapterPaso.getList());
                    receta.put("favorito", false);

                    db.collection(Account.getInstance().getUser()).document().set(receta).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            NavHostFragment.findNavController(AddRecetaFragment.this).navigateUp();
                        }
                    });
                }
                else
                    tilNombre.setError("El nombre no puede estar vacío");
            }
        });
    }

//    private void showNotification(){
//        //Una PendingIntent tienen un objeto intent en su interior que define lo que
//        //se quiere ejecutar cuando se pulse sobre la notificacion
//        //INICIAR UNA ACTIVITY
//        //Intent intent = new Intent();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("recipe",recipe);
//        //intent.putExtras(bundle);
//
//        //Se construye el PendingIntent
//        //PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), new Random().nextInt(1000),intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
//                .setGraph(R.navigation.nav_graph)
//                .setDestination(R.id.addRecetaFragment)
//                .setArguments(bundle)
//                .createPendingIntent();
//
//
//        Notification.Builder builder = new Notification.Builder(getActivity(), IFoodistApplication.CHANNEL_ID)
//                .setAutoCancel(true)
//                .setSmallIcon(R.drawable.ic_logo)
//                .setContentTitle(getResources().getString(R.string.notification_title_recipe))
//                .setContentText(String.format(getString(R.string.text_add_recipe)+ recipe.getNombre()))
//                .setContentIntent(pendingIntent);
//
//        //Se añade la notificacion al gestior de notificaciones
//        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(new Random().nextInt(1000),builder.build());
//    }
}