package com.example.biocaribe_congresodermatologia_2019.Database.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.biocaribe_congresodermatologia_2019.Database.AppDatabase;
import com.example.biocaribe_congresodermatologia_2019.Database.Dao.LaboratorioDao;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Laboratorio;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LaboratorioRepository {

    private LaboratorioDao mLaboratorioDao;

    public LaboratorioRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mLaboratorioDao = db.laboratorioDao();
    }


    //INSERT
    public Long insert (Laboratorio laboratorio) throws ExecutionException, InterruptedException {
        return new insertAsyncTask(mLaboratorioDao).execute(laboratorio).get();
    }
    private static class insertAsyncTask extends AsyncTask<Laboratorio, Void, Long> {

        private LaboratorioDao mAsyncTaskDao;

        insertAsyncTask(LaboratorioDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Laboratorio... params) {
            return  mAsyncTaskDao.insert(params[0]);
        }
    }

    //GET ALL LABORATORIOS
    public List<Laboratorio> getAll () throws ExecutionException, InterruptedException {
        return new getAllAsyncTask(mLaboratorioDao).execute().get();
    }
    private static class getAllAsyncTask extends AsyncTask<Void, Void, List<Laboratorio>> {

        private LaboratorioDao mAsyncTaskDao;

        getAllAsyncTask(LaboratorioDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Laboratorio> doInBackground(Void... voids) {
            return  mAsyncTaskDao.getAllLaboratorios();
        }
    }

    //GET LABORATORIO BY NOMBRE
    public Laboratorio getLaboratorioByNombre (String nombre) throws ExecutionException, InterruptedException {
        return new getLaborarioAsyncTask(mLaboratorioDao).execute(nombre).get();
    }
    private static class getLaborarioAsyncTask extends AsyncTask<String, Void, Laboratorio >{

        private LaboratorioDao mAsyncTaskDao;

        getLaborarioAsyncTask(LaboratorioDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Laboratorio doInBackground(String... params) {
            return mAsyncTaskDao.findByNombre(params[0]);
        }
    }


}
