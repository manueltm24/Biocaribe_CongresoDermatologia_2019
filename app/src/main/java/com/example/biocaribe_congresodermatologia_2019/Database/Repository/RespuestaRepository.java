package com.example.biocaribe_congresodermatologia_2019.Database.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.biocaribe_congresodermatologia_2019.Database.AppDatabase;
import com.example.biocaribe_congresodermatologia_2019.Database.Dao.RespuestaDao;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Respuesta;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RespuestaRepository {
    private RespuestaDao mRespuestaDao;

    public RespuestaRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mRespuestaDao = db.respuestaDao();
    }


    //INSERT
    public Long insert (Respuesta laboratorio) throws ExecutionException, InterruptedException {
        return new insertAsyncTask(mRespuestaDao).execute(laboratorio).get();
    }
    private static class insertAsyncTask extends AsyncTask<Respuesta, Void, Long> {

        private RespuestaDao mAsyncTaskDao;

        insertAsyncTask(RespuestaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Respuesta... params) {
            return  mAsyncTaskDao.insert(params[0]);
        }
    }

    //GET ALL RESPUESTAS
    public List<Respuesta> getAll () throws ExecutionException, InterruptedException {
        return new getAllAsyncTask(mRespuestaDao).execute().get();
    }
    private static class getAllAsyncTask extends AsyncTask<Void, Void, List<Respuesta>> {

        private RespuestaDao mAsyncTaskDao;

        getAllAsyncTask(RespuestaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Respuesta> doInBackground(Void... voids) {
            return  mAsyncTaskDao.getAllRespuestas();
        }
    }

    //GET ALL RESPUESTA BY PREGUNTA
    public List<Respuesta> getAllByPregunta (long pregunta) throws ExecutionException, InterruptedException {
        return new getAllByPreguntaAsyncTask(mRespuestaDao).execute(pregunta).get();
    }
    private static class getAllByPreguntaAsyncTask extends AsyncTask<Long, Void, List<Respuesta>> {

        private RespuestaDao mAsyncTaskDao;

        getAllByPreguntaAsyncTask(RespuestaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Respuesta> doInBackground(Long... params) {
            return  mAsyncTaskDao.findAllByPregunta(params[0]);
        }
    }


}
