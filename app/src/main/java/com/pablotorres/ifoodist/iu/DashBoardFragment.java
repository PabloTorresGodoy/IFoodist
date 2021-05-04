package com.pablotorres.ifoodist.iu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pablotorres.ifoodist.R;


public class DashBoardFragment extends Fragment {

    private Button btRecetario;
    private Button btLista;

    public DashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dash_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*btRecetario = view.findViewById(R.id.btRecetario);
        btRecetario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DashBoardFragment.this).navigate(R.id.action_dashBoardFragment_to_recetarioFragment);
            }
        });
        btLista = view.findViewById(R.id.btLista);
        btLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DashBoardFragment.this).navigate(R.id.action_dashBoardFragment_to_listShoppingFragment);
            }
        });*/
    }
}