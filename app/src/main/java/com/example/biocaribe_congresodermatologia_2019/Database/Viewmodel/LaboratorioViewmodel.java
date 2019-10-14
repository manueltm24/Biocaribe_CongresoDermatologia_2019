package com.example.biocaribe_congresodermatologia_2019.Database.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.biocaribe_congresodermatologia_2019.Database.AppDatabase;
import com.example.biocaribe_congresodermatologia_2019.Database.Repository.LaboratorioRepository;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Laboratorio;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LaboratorioViewmodel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private LaboratorioRepository mRepository;


    public LaboratorioViewmodel(@NonNull Application application) {
        super(application);
        mRepository = new LaboratorioRepository(application);

        appDatabase = AppDatabase.getInstance(application);
    }

    public void insert(Laboratorio laboratorio) throws ExecutionException, InterruptedException { mRepository.insert(laboratorio); }

    public List<Laboratorio> getAll() throws ExecutionException, InterruptedException { return mRepository.getAll(); }

    public Laboratorio getLaboratorioByNombre(String nombre) throws ExecutionException, InterruptedException { return mRepository.getLaboratorioByNombre(nombre); }

}
