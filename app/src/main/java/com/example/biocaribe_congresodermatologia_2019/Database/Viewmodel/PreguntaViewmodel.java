package com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.biocaribe_congresodermatologia_2019.Database.AppDatabase;
import com.example.biocaribe_congresodermatologia_2019.Database.Repository.PreguntaRepository;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Pregunta;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PreguntaViewmodel extends AndroidViewModel {
    private AppDatabase appDatabase;
    private PreguntaRepository mRepository;


    public PreguntaViewmodel(@NonNull Application application) {
        super(application);
        mRepository = new PreguntaRepository(application);

        appDatabase = AppDatabase.getInstance(application);
    }

    public Long insert(Pregunta pregunta) throws ExecutionException, InterruptedException { return mRepository.insert(pregunta); }

    public List<Pregunta> getAll() throws ExecutionException, InterruptedException { return mRepository.getAll(); }

    public List<Pregunta> getAllByLinea(long linea) throws ExecutionException, InterruptedException { return mRepository.getAllByLinea(linea); }

    public Pregunta getById(long id) throws ExecutionException, InterruptedException { return mRepository.getById(id); }

}
