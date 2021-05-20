package com.pablotorres.ifoodist.iu.recipe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.data.repository.RecipeRepository;
import com.pablotorres.ifoodist.iu.adapter.RecipeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListRecipeFragment extends Fragment implements ListRecipeContract.View,RecipeAdapter.OnManageRecipeListener {

    private FloatingActionButton fab;
    private RecyclerView rvRecipe;
    private RecipeAdapter adapter;
    private ListRecipeContract.Presenter presenter;
    private TextView imgNoData;

    public ListRecipeFragment() {
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
        return inflater.inflate(R.layout.fragment_list_recipe, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgNoData = view.findViewById(R.id.imgNoData);

        fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setImageDrawable(getResources().getDrawable(R.drawable.mas));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ListRecipeFragment.this).navigate(R.id.action_recetarioFragment_to_addRecetaFragment);
            }
        });
        rvRecipe=view.findViewById(R.id.rvRecipe);
        adapter = new RecipeAdapter(new ArrayList<Recipe>(), this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false );
        rvRecipe.setLayoutManager(layoutManager);
        rvRecipe.setAdapter(adapter);

        presenter= new ListRecipePresenter(this);
    }

    @Override
    public void onClickRecipe(Recipe recipe) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("recipe", recipe);
        NavHostFragment.findNavController(ListRecipeFragment.this).navigate(R.id.action_recetarioFragment_to_showRecetaFragment, bundle);
    }

    private void Ordenar() {
        adapter.ordenar();
    }

    @Override
    public void setNoData() {
        imgNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessDeleted(Recipe deleted) {
        adapter.delete(deleted);
    }

    @Override
    public void onSuccess(List<Recipe> list) {
        if(imgNoData.getVisibility()==View.VISIBLE)
            imgNoData.setVisibility(View.GONE);

        adapter.update(list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }
}