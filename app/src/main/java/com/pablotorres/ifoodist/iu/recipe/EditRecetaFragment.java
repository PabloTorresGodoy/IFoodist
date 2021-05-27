package com.pablotorres.ifoodist.iu.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.data.repository.Account;
import com.pablotorres.ifoodist.iu.adapter.IngredienteAdapter;
import com.pablotorres.ifoodist.iu.adapter.IngredienteShowAdapter;
import com.pablotorres.ifoodist.iu.adapter.PasoAdapter;
import com.pablotorres.ifoodist.iu.adapter.PasoShowAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditRecetaFragment extends Fragment {

    private TextInputEditText tieEditNombre;
    private TextInputEditText tieEditDuracion;
    private TextInputEditText tieEditCantidad;
    private RecyclerView rvEditIngredientes;
    private RecyclerView rvEditPasos;
    private Button btActualizar;
    private Spinner spEditCategoria;
    private IngredienteAdapter ingredienteAdapter;
    private PasoAdapter pasoAdapter;
    private ImageButton btEditIngrediente;
    private ImageButton btEditPaso;
    private EditText edEditPasos;
    private EditText edEditIngredientes;
    private FirebaseFirestore db;
    private Recipe recipe;
    private String[] arrayCategorias = {"Plato Principal", "Entrante", "Postre", "Bebida","Salsa","Otro" };
    int cont = 0;

    public static ShowRecetaFragment newInstance(Bundle bundle) {
        ShowRecetaFragment fragment = new ShowRecetaFragment();
        if(bundle != null)
            fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_edit_receta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipe = (Recipe) getArguments().getSerializable("recipe");

        tieEditNombre = view.findViewById(R.id.tieEditNombre);
        tieEditDuracion = view.findViewById(R.id.tieEditDuracion);
        tieEditCantidad = view.findViewById(R.id.tieEditCantidad);
        rvEditIngredientes = view.findViewById(R.id.rvEditIngredientes);
        rvEditPasos = view.findViewById(R.id.rvEditPasos);
        spEditCategoria = view.findViewById(R.id.spEditCategoria);
        btActualizar = view.findViewById(R.id.btActualizar);
        btEditPaso = view.findViewById(R.id.btEditPaso);
        btEditIngrediente = view.findViewById(R.id.btEditIngrediente);
        edEditPasos = view.findViewById(R.id.edEditPasos);
        edEditIngredientes = view.findViewById(R.id.edEditIngredientes);

        cargarReceta();

        btEditPaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasoAdapter.insertar(edEditPasos.getText().toString());
                edEditPasos.setText("");
            }
        });

        btEditIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredienteAdapter.insertar(edEditIngredientes.getText().toString());
                edEditIngredientes.setText("");
            }
        });

        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe recipeActualizada = recogerReceta();

                Map<String, Object> receta = new HashMap<>();
                receta.put("nombre", recipeActualizada.getNombre());
                receta.put("categoria", recipeActualizada.getCategoria());
                receta.put("duracion", recipeActualizada.getDuracion());
                receta.put("cantidad", recipeActualizada.getCantidad());
                receta.put("ingredientes", recipeActualizada.getIngredientes());
                receta.put("pasos", recipeActualizada.getPasos());
                receta.put("favorito", false);

                db.collection(Account.getInstance().getUser()).document(recipe.getId()).update(receta).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        NavHostFragment.findNavController(EditRecetaFragment.this).navigate(R.id.back_to_list);
                    }
                });
            }
        });
    }

    private void cargarReceta(){
        tieEditNombre.setText(recipe.getNombre());
        tieEditCantidad.setText(recipe.getCantidad());
        tieEditDuracion.setText(recipe.getCantidad());
        for(String categoria : arrayCategorias){
            if (categoria.equals(recipe.getCategoria()))
                spEditCategoria.setSelection(cont);
            cont++;
        }

        ingredienteAdapter = new IngredienteAdapter(recipe.getIngredientes());
        RecyclerView.LayoutManager layoutManagerIngrediente = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false );
        rvEditIngredientes.setLayoutManager(layoutManagerIngrediente);
        rvEditIngredientes.setAdapter(ingredienteAdapter);

        pasoAdapter = new PasoAdapter(recipe.getPasos());
        RecyclerView.LayoutManager layoutManagerPaso = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false );
        rvEditPasos.setLayoutManager(layoutManagerPaso);
        rvEditPasos.setAdapter(pasoAdapter);
    }

    private Recipe recogerReceta(){
        return new Recipe(tieEditNombre.getText().toString(), spEditCategoria.getSelectedItem().toString(), tieEditDuracion.getText().toString(), tieEditCantidad.getText().toString(), ingredienteAdapter.getList(), pasoAdapter.getList(), false);
    }

}
