package com.example.biocaribe_congresodermatologia_2019.Database.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.biocaribe_congresodermatologia_2019.Database.AppDatabase;
import com.example.biocaribe_congresodermatologia_2019.Database.Dao.PreguntaDao;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Pregunta;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PreguntaRepository {
    private PreguntaDao mPreguntaDao;

    public PreguntaRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mPreguntaDao = db.preguntaDao();
    }


    //INSERT
    public Long insert (Pregunta pregunta) throws ExecutionException, InterruptedException {
        return new insertAsyncTask(mPreguntaDao).execute(pregunta).get();
    }
    private static class insertAsyncTask extends AsyncTask<Pregunta, Void, Long> {

        private PreguntaDao mAsyncTaskDao;

        insertAsyncTask(PreguntaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Pregunta... params) {
            return  mAsyncTaskDao.insert(params[0]);
        }
    }


    //GET PREGUNTA BY ID
    public Pregunta getById (long id) throws ExecutionException, InterruptedException {
        return new getByIdAsyncTask(mPreguntaDao).execute(id).get();
    }
    private static class getByIdAsyncTask extends AsyncTask<Long, Void, Pregunta>{

        private PreguntaDao mAsyncTaskDao;

        getByIdAsyncTask(PreguntaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Pregunta doInBackground(Long... params) {
            return  mAsyncTaskDao.findById(params[0]);
        }
    }

    //GET ALL PREGUNTAS
    public List<Pregunta> getAll () throws ExecutionException, InterruptedException {
        return new getAllAsyncTask(mPreguntaDao).execute().get();
    }
    private static class getAllAsyncTask extends AsyncTask<Void, Void, List<Pregunta>> {

        private PreguntaDao mAsyncTaskDao;

        getAllAsyncTask(PreguntaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Pregunta> doInBackground(Void... voids) {
            return  mAsyncTaskDao.getAllPreguntas();
        }
    }

    //GET ALL PREGUNTAS BY LINEA
    public  List<Pregunta> getAllByLinea (long linea) throws ExecutionException, InterruptedException {
        return new getAllByLineaAsyncTask(mPreguntaDao).execute(linea).get();
    }
    private static class getAllByLineaAsyncTask extends AsyncTask<Long, Void,  List<Pregunta> >{

        private PreguntaDao mAsyncTaskDao;

        getAllByLineaAsyncTask(PreguntaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected  List<Pregunta> doInBackground(Long... params) {
            return mAsyncTaskDao.findAllByLinea(params[0]);
        }
    }
}
