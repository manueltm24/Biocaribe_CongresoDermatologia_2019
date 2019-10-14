package com.example.biocaribe_congresodermatologia_2019.Database.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.biocaribe_congresodermatologia_2019.Database.AppDatabase;
import com.example.biocaribe_congresodermatologia_2019.Database.Dao.LineaDao;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Linea;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LineaRepository {
    private LineaDao mLineaDao;

    public LineaRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mLineaDao = db.lineaDao();
    }


    //INSERT
    public Long insert (Linea linea) throws ExecutionException, InterruptedException {
        return new insertAsyncTask(mLineaDao).execute(linea).get();
    }
    private static class insertAsyncTask extends AsyncTask<Linea, Void, Long> {

        private LineaDao mAsyncTaskDao;

        insertAsyncTask(LineaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Linea... params) {
            return  mAsyncTaskDao.insert(params[0]);
        }
    }

    //UPDATE
    public void update (Linea linea) throws ExecutionException, InterruptedException {
        new updateAsyncTask(mLineaDao).execute(linea).get();
    }
    private static class updateAsyncTask extends AsyncTask<Linea, Void, Void> {

        private LineaDao mAsyncTaskDao;

        updateAsyncTask(LineaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Linea... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    //GET ALL LINEAS
    public List<Linea> getAll () throws ExecutionException, InterruptedException {
        return new getAllAsyncTask(mLineaDao).execute().get();
    }
    private static class getAllAsyncTask extends AsyncTask<Void, Void, List<Linea>> {

        private LineaDao mAsyncTaskDao;

        getAllAsyncTask(LineaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Linea> doInBackground(Void... voids) {
            return  mAsyncTaskDao.getAllLineas();
        }
    }

    //GET LINEA BY NOMBRE
    public Linea getLineaByNombre (String nombre) throws ExecutionException, InterruptedException {
            return new getByNombreAsyncTask(mLineaDao).execute(nombre).get();
    }
    private static class getByNombreAsyncTask extends AsyncTask<String, Void, Linea> {

        private LineaDao mAsyncTaskDao;

        getByNombreAsyncTask(LineaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Linea doInBackground(String... params) {
            return mAsyncTaskDao.findByNombre(params[0]);
        }
    }

    //GET LINEA BY ID
    public Linea getLineaById (long id) throws ExecutionException, InterruptedException {
        return new getByIdAsyncTask(mLineaDao).execute(id).get();
    }
    private static class getByIdAsyncTask extends AsyncTask<Long, Void, Linea> {

        private LineaDao mAsyncTaskDao;

        getByIdAsyncTask(LineaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Linea doInBackground(Long... params) {
            return mAsyncTaskDao.findById(params[0]);
        }
    }

    //GET ALL LINEAS BY LABORATORIO
    public List<Linea> getAllByLaborario (long laboratorio) throws ExecutionException, InterruptedException {
        return new getAllByLaborarioAsyncTask(mLineaDao).execute(laboratorio).get();
    }
    private static class getAllByLaborarioAsyncTask extends AsyncTask<Long, Void, List<Linea>> {

        private LineaDao mAsyncTaskDao;

        getAllByLaborarioAsyncTask(LineaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Linea> doInBackground(Long... params) {
            return  mAsyncTaskDao.findAllByLaborario(params[0]);
        }
    }
}
