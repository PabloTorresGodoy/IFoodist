package com.pablotorres.ifoodist.iu.recipe.ShowRecipe;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.iu.adapter.IngredienteShowAdapter;
import com.pablotorres.ifoodist.iu.adapter.PasoShowAdapter;

import java.util.ArrayList;

public class ShowRecipeFragment extends Fragment implements ShowRecipeContract.View{

    private TextInputEditText tieShowNombre;
    private TextInputEditText tieSHowDuracion;
    private TextInputEditText tieShowCantidad;
    private RecyclerView rvShowIngredientes;
    private RecyclerView rvShowPasos;
    private FloatingActionButton fab;
    private TextView tvCategoria;
    private IngredienteShowAdapter ingredienteAdapter;
    private PasoShowAdapter pasoAdapter;
    private FirebaseFirestore db;
    private Recipe recipe;
    private ShowRecipeContract.Presenter presenter;

    public static ShowRecipeFragment newInstance(Bundle bundle) {
        ShowRecipeFragment fragment = new ShowRecipeFragment();
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
        return inflater.inflate(R.layout.fragment_show_receta, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                DialogDelete();
                break;
            case R.id.action_edit:
                Bundle bundle = new Bundle();
                bundle.putSerializable("recipe", recipe);
                NavHostFragment.findNavController(ShowRecipeFragment.this).navigate(R.id.action_showRecetaFragment_to_editRecetaFragment, bundle);
                break;
            case R.id.action_share:
                compartir();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void compartir() {

    }

    private void DialogDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("¿Seguro que desea eliminar la receta?")
                .setTitle("Eliminar")
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .setPositiveButton("Si", (dialog, which) -> delete());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tieShowNombre = view.findViewById(R.id.tieNombre);
        tieSHowDuracion = view.findViewById(R.id.tieDuracion);
        tieShowCantidad = view.findViewById(R.id.tieCantidad);
        rvShowIngredientes = view.findViewById(R.id.rvIngredientes);
        rvShowPasos = view.findViewById(R.id.rvPasos);
        tvCategoria = view.findViewById(R.id.tvCategoria);

        recipe = (Recipe) getArguments().getSerializable("recipe");

        tieShowNombre.setText(recipe.getNombre());
        tieShowCantidad.setText(recipe.getCantidad());
        tieSHowDuracion.setText(recipe.getDuracion());
        tvCategoria.setText(recipe.getCategoria());

        rvShowIngredientes = view.findViewById(R.id.rvIngredientes);
        ingredienteAdapter = new IngredienteShowAdapter(new ArrayList<>());
        RecyclerView.LayoutManager layoutManagerIngrediente = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false );
        rvShowIngredientes.setLayoutManager(layoutManagerIngrediente);
        rvShowIngredientes.setAdapter(ingredienteAdapter);
        ingredienteAdapter.update(recipe.getIngredientes());

        rvShowPasos = view.findViewById(R.id.rvPasos);
        pasoAdapter = new PasoShowAdapter(new ArrayList<>());
        RecyclerView.LayoutManager layoutManagerPaso = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false );
        rvShowPasos.setLayoutManager(layoutManagerPaso);
        rvShowPasos.setAdapter(pasoAdapter);
        pasoAdapter.update(recipe.getPasos());

        fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        presenter= new ShowRecipePresenter(this);
    }

    private void delete(){
        Recipe recipe = (Recipe) getArguments().getSerializable("recipe");
        presenter.delete(recipe);
    }

    @Override
    public void onSuccessDeleted() {
        NavHostFragment.findNavController(this).navigateUp();
    }
}