package com.pablotorres.ifoodist.iu.recipe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class ShowRecetaFragment extends Fragment {

    private TextInputEditText tieShowNombre;
    private TextInputEditText tieSHowDuracion;
    private TextInputEditText tieShowCantidad;
    private EditText edShowIngredientes;
    private EditText edShowPasos;
    private FloatingActionButton fab;
    private Spinner spShowCategoria;

    public static ShowRecetaFragment newInstance(Bundle bundle) {
        ShowRecetaFragment fragment = new ShowRecetaFragment();
        if(bundle != null)
            fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_receta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tieShowNombre = view.findViewById(R.id.tieShowNombre);
        tieSHowDuracion = view.findViewById(R.id.tieShowDuracion);
        tieShowCantidad = view.findViewById(R.id.tieShowCantidad);
        edShowIngredientes = view.findViewById(R.id.edShowIngredientes);
        edShowPasos = view.findViewById(R.id.edShowPasos);
        spShowCategoria = view.findViewById(R.id.spShowCategoria);

        Recipe recipe = (Recipe) getArguments().getSerializable("recipe");

        tieShowNombre.setText(recipe.getNombre());
        tieShowCantidad.setText(recipe.getCantidad());
        tieSHowDuracion.setText(recipe.getDuracion());
        edShowIngredientes.setText(recipe.getIngredientes());
        edShowPasos.setText(recipe.getPasos());

        fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        fab.setImageDrawable(getResources().getDrawable(R.drawable.tic));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe recipeUpdate = new Recipe(tieShowNombre.getText().toString(), spShowCategoria.getSelectedItem().toString(), tieSHowDuracion.getText().toString(), tieShowCantidad.getText().toString(), edShowIngredientes.getText().toString(), edShowPasos.getText().toString(), false);
                RecipeRepository.getInstance().update(recipeUpdate);
                NavHostFragment.findNavController(ShowRecetaFragment.this).navigateUp();
            }
        });
    }
}