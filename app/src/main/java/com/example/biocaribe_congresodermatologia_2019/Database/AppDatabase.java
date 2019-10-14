package com.example.biocaribe_congresodermatologia_2019.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.biocaribe_congresodermatologia_2019.Database.Dao.LaboratorioDao;
import com.example.biocaribe_congresodermatologia_2019.Database.Dao.LineaDao;
import com.example.biocaribe_congresodermatologia_2019.Database.Dao.PreguntaDao;
import com.example.biocaribe_congresodermatologia_2019.Database.Dao.RespuestaDao;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Laboratorio;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Linea;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Pregunta;
import com.example.biocaribe_congresodermatologia_2019.Entidades.Respuesta;

@Database(entities = {Laboratorio.class, Linea.class, Pregunta.class, Respuesta.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "biocaribe_congresodermatologia_2019";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {

                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }

    public abstract LaboratorioDao laboratorioDao();
    public abstract LineaDao lineaDao();
    public abstract PreguntaDao preguntaDao();
    public abstract RespuestaDao respuestaDao();

}