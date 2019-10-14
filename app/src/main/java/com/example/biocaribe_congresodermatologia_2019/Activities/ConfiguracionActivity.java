package com.example.biocaribe_congresodermatologia_2019.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel.LineaViewmodel;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Linea;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Respuesta;
import com.example.biocaribe_congresodermatologia_2019.R;
import com.example.biocaribe_congresodermatologia_2019.RecycleViewListadoLineasAdapter;
import com.example.biocaribe_congresodermatologia_2019.RecycleViewListadoRespuestasAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ConfiguracionActivity extends AppCompatActivity implements RecycleViewListadoLineasAdapter.Listener{

    RecycleViewListadoLineasAdapter recycleViewListadoLineasAdapter;

    List<Linea> listadoLineas = new ArrayList<>();

    LineaViewmodel lineaViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_configuracion);

        lineaViewmodel = ViewModelProviders.of(this).get(LineaViewmodel.class);

        recycleViewListadoLineasAdapter = new RecycleViewListadoLineasAdapter(getApplicationContext(),listadoLineas,this);
        RecyclerView rv= findViewById(R.id.recycler_view);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        rv.setAdapter(recycleViewListadoLineasAdapter);

        try {
            recycleViewListadoLineasAdapter.setListadoLineas(lineaViewmodel.getAll());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(Linea linea) {

        try {
            if(linea.isActiva()){
                linea.setActiva(false);

            }
            else {
                linea.setActiva(true);
            }
            lineaViewmodel.update(linea);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(linea.isActiva());

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
                startActivity(new Intent(ConfiguracionActivity.this, MainActivity.class));
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
