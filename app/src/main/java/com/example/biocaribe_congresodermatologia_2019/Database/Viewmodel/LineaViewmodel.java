package com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.biocaribe_congresodermatologia_2019.Database.AppDatabase;
import com.example.biocaribe_congresodermatologia_2019.Database.Repository.LineaRepository;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Linea;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LineaViewmodel extends AndroidViewModel {
    private AppDatabase appDatabase;
    private LineaRepository mRepository;


    public LineaViewmodel(@NonNull Application application) {
        super(application);
        mRepository = new LineaRepository(application);

        appDatabase = AppDatabase.getInstance(application);
    }

    public void insert(Linea linea) throws ExecutionException, InterruptedException { mRepository.insert(linea); }
    public void update(Linea linea) throws ExecutionException, InterruptedException { mRepository.update(linea); }

    public List<Linea> getAll() throws ExecutionException, InterruptedException { return mRepository.getAll(); }

    public Linea getLineaByNombre(String nombre) throws ExecutionException, InterruptedException { return mRepository.getLineaByNombre(nombre); }
    public Linea getLineaById(long id) throws ExecutionException, InterruptedException { return mRepository.getLineaById(id); }

    public List<Linea> getAllByLaborario(long laboratorio) throws ExecutionException, InterruptedException { return mRepository.getAllByLaborario(laboratorio); }

}
