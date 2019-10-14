package com.example.biocaribe_congresodermatologia_2019.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel.LaboratorioViewmodel;
import com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel.LineaViewmodel;
import com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel.PreguntaViewmodel;
import com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel.RespuestaViewmodel;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Linea;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Pregunta;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Respuesta;
import com.example.biocaribe_congresodermatologia_2019.R;
import com.example.biocaribe_congresodermatologia_2019.RecycleViewListadoRespuestasAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class PreguntaActivity extends AppCompatActivity implements RecycleViewListadoRespuestasAdapter.Listener {


    RecycleViewListadoRespuestasAdapter recycleViewListadoRespuestasAdapter;
    List<Respuesta> listadoRespuesta = new ArrayList<>();

    private Pregunta preguntaActual;
    private TextView textView_preguntaActual;
    private List<Pregunta> preguntas = new ArrayList<>();

    LaboratorioViewmodel laboratorioViewmodel;
    LineaViewmodel lineaViewmodel;
    PreguntaViewmodel preguntaViewmodel;
    RespuestaViewmodel respuestaViewmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pregunta);


        laboratorioViewmodel = ViewModelProviders.of(this).get(LaboratorioViewmodel.class);
        lineaViewmodel = ViewModelProviders.of(this).get(LineaViewmodel.class);
        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel.class);
        respuestaViewmodel = ViewModelProviders.of(this).get(RespuestaViewmodel.class);


        textView_preguntaActual = (TextView)findViewById(R.id.textView_pregunta);


        recycleViewListadoRespuestasAdapter = new RecycleViewListadoRespuestasAdapter(getApplicationContext(),listadoRespuesta,this);
        RecyclerView rv= findViewById(R.id.recycler_view);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        rv.setAdapter(recycleViewListadoRespuestasAdapter);

        //GENERA LA PRIMERA PREGUNTA ALEATORIAMENTE
        try {
            generarPreguntaAleatoria();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void generarPreguntaAleatoria() throws ExecutionException, InterruptedException {
        List<Linea> lineasActivas = new ArrayList<>();
        for (Linea linea: lineaViewmodel.getAll()) {
            if(linea.isActiva()){
                lineasActivas.add(linea);
            }
        }
        if(lineasActivas.size()<=0){
            textView_preguntaActual.setText("DEBE ACTIVAR UNA LINEA DE PREGUNTAS!");

        }
        else{
            for (Linea linea: lineasActivas) {
                preguntas.addAll(preguntaViewmodel.getAllByLinea(linea.getId()));

            }
            if(preguntas.size()<=0){
                    textView_preguntaActual.setText("NO EXISTEN PREGUNTAS EN LAS LINEAS SELECCIONADAS!");
            }
            else{
                System.out.println("PREGUNTAS"+preguntas.size());

                Random random = new Random();
                int randomInteger = random.nextInt(preguntas.size());

                System.out.println("RAMDON"+randomInteger);

                preguntaActual = preguntas.get(randomInteger); //JALA LA PREGUNTA ALEATORIA DE LA LISTA
                textView_preguntaActual.setText(preguntaActual.getPregunta());

                ImageView marcaImagen = (ImageView)findViewById(R.id.marcaImagen);
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.contenedorPregunta);

                if(preguntaActual.getLinea() == lineaViewmodel.getLineaByNombre("Heliocare").getId()){
                    marcaImagen.setImageResource(R.drawable.cantabria);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.heliocare));
                }
                else if(preguntaActual.getLinea() == lineaViewmodel.getLineaByNombre("Uriage").getId()){
                    marcaImagen.setImageResource(R.drawable.uriage);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.uriage));
                }
                else if(preguntaActual.getLinea() == lineaViewmodel.getLineaByNombre("Neostrata").getId()){
                    marcaImagen.setImageResource(R.drawable.neostrata);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.neostrata));
                }
                else if(preguntaActual.getLinea() == lineaViewmodel.getLineaByNombre("Neoretin").getId()){
                    marcaImagen.setImageResource(R.drawable.cantabria);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.neoretin));
                }
                else if(preguntaActual.getLinea() == lineaViewmodel.getLineaByNombre("Biretix").getId()){
                    marcaImagen.setImageResource(R.drawable.cantabria);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.biretix));
                }
                else if(preguntaActual.getLinea() == lineaViewmodel.getLineaByNombre("Endocare").getId()){
                    marcaImagen.setImageResource(R.drawable.cantabria);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.endocare));
                }

                recycleViewListadoRespuestasAdapter.setListadoRespuestas((respuestaViewmodel.getAllByPregunta(preguntaActual.getId())));
            }

        }

    }

    @Override
    public void onClick(Respuesta respuesta) {
        if(respuesta.isValida()){
            Intent intent = new Intent(PreguntaActivity.this, WinnerActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(PreguntaActivity.this, LoserActivity.class);
            startActivity(intent);
        }

    }


    public void clickAnyButton(View view) {
        switch (view.getId()) {
            case R.id.btn_exit:
                confirmDialogDemo();
                break;

        }
    }

    /**
     * Confirmar si quieres salir
     */
    private void confirmDialogDemo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar salida !");
        builder.setMessage("Realmente quieres salir ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(PreguntaActivity.this, MainActivity.class));
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Se ha cancelado la salida.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

}
