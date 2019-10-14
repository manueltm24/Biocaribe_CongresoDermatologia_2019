package com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.biocaribe_congresodermatologia_2019.Database.AppDatabase;
import com.example.biocaribe_congresodermatologia_2019.Database.Repository.RespuestaRepository;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Respuesta;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RespuestaViewmodel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private RespuestaRepository mRepository;


    public RespuestaViewmodel(@NonNull Application application) {
        super(application);
        mRepository = new RespuestaRepository(application);

        appDatabase = AppDatabase.getInstance(application);
    }

    public void insert(Respuesta respuesta) throws ExecutionException, InterruptedException { mRepository.insert(respuesta); }

    public List<Respuesta> getAll() throws ExecutionException, InterruptedException { return mRepository.getAll(); }

    public List<Respuesta> getAllByPregunta(long pregunta) throws ExecutionException, InterruptedException { return mRepository.getAllByPregunta(pregunta); }


}
