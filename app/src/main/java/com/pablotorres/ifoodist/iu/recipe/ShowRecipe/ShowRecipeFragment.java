package com.pablotorres.ifoodist.iu.recipe.ShowRecipe;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
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
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Header;
import com.lowagie.text.List;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.data.model.Recipe;
import com.pablotorres.ifoodist.iu.adapter.IngredienteShowAdapter;
import com.pablotorres.ifoodist.iu.adapter.PasoShowAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
                if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},1000);
                }else
                    compartir();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void compartir(){
        Uri uriResponse = crearPDF();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/pdf"); //Tipo para compartir
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_STREAM, uriResponse);
        getContext().startActivity(Intent.createChooser(intent, "Compartir con..."));
    }

    private Uri crearPDF(){
        Uri uri = null;
        try {
            File file = File.createTempFile(recipe.getNombre().trim(), ".pdf", getRuta());
            file.deleteOnExit();
            FileOutputStream fout = new FileOutputStream(file);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, fout);

            document.open();

            Paragraph Titulo = new Paragraph();
            Titulo.add(new Paragraph(""));
            Titulo.add(new Paragraph("      "+recipe.getNombre().toUpperCase()));
            Titulo.add(new Paragraph(""));
            document.add(Titulo);

            Paragraph descripcion = new Paragraph();
            descripcion.add(new Paragraph(""));
            descripcion.add(new Paragraph("Categoria: "+recipe.getCategoria()));
            descripcion.add(new Paragraph("Cantidad: "+recipe.getCantidad()));
            descripcion.add(new Paragraph("Duracion: "+recipe.getDuracion()));
            document.add(descripcion);

            Paragraph ingredientes = new Paragraph();
            ingredientes.add(new Paragraph(""));
            ingredientes.add(new Paragraph(""));
            ingredientes.add(new Paragraph("        INGREDIENTES"));
            ingredientes.add(new Paragraph(""));
            for (String ingrediente : recipe.getIngredientes()){
                ingredientes.add(new Paragraph(" - "+ingrediente));
            }
            document.add(ingredientes);

            Paragraph pasos = new Paragraph();
            pasos.add(new Paragraph(""));
            pasos.add(new Paragraph(""));
            pasos.add(new Paragraph("       PASOS"));
            pasos.add(new Paragraph(""));
            int cont = 0;
            for (String paso : recipe.getPasos()){
                cont++;
                pasos.add(new Paragraph(" "+cont+".- "+paso));
                pasos.add(new Paragraph(""));
            }
            document.add(pasos);

            document.close();
            fout.close();

            uri = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", file);
        }catch (DocumentException e){
        }catch (IOException e){
        }finally {
            return uri;
        }
    }

    public File crearFichero(String nombreFichero){
        File ruta = getRuta();
        File fichero = null;
        if(ruta != null){
            fichero = new File(ruta, nombreFichero);
        }

        return fichero;
    }

    public File getRuta(){
        File ruta = null;

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            ruta = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "MisPDFs");
            if(ruta != null){
                if(!ruta.mkdirs()){
                    if(!ruta.exists()){
                        return null;
                    }
                }
            }
        }
        return  ruta;
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