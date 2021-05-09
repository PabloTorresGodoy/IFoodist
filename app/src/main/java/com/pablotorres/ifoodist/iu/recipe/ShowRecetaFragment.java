package com.pablotorres.ifoodist.iu.recipe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.iu.adapter.IngredienteAdapter;
import com.pablotorres.ifoodist.iu.adapter.IngredienteShowAdapter;
import com.pablotorres.ifoodist.iu.adapter.PasoAdapter;
import com.pablotorres.ifoodist.iu.adapter.PasoShowAdapter;

import java.util.ArrayList;

public class ShowRecetaFragment extends Fragment {

    private TextInputEditText tieShowNombre;
    private TextInputEditText tieSHowDuracion;
    private TextInputEditText tieShowCantidad;
    private RecyclerView rvShowIngredientes;
    private RecyclerView rvShowPasos;
    private FloatingActionButton fab;
    private TextView tvCategoria;
    private IngredienteShowAdapter ingredienteAdapter;
    private PasoShowAdapter pasoAdapter;

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

        tieShowNombre = view.findViewById(R.id.tieNombre);
        tieSHowDuracion = view.findViewById(R.id.tieDuracion);
        tieShowCantidad = view.findViewById(R.id.tieCantidad);
        rvShowIngredientes = view.findViewById(R.id.rvIngredientes);
        rvShowPasos = view.findViewById(R.id.rvPasos);
        tvCategoria = view.findViewById(R.id.tvCategoria);

        Recipe recipe = (Recipe) getArguments().getSerializable("recipe");

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
    }
}