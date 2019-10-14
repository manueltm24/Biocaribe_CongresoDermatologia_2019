package com.example.biocaribe_congresodermatologia_2019.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.biocaribe_congresodermatologia_2019.Entidades.Respuesta;

import java.util.List;

@Dao
public interface RespuestaDao {

    @Insert
    long insert(Respuesta respuesta);

    @Delete
    void delete(Respuesta respuesta);

    @Query("SELECT * FROM Respuesta")
    List<Respuesta> getAllRespuestas();

    @Query("SELECT * FROM Respuesta WHERE pregunta LIKE :pregunta")
    List<Respuesta> findAllByPregunta(long pregunta);
}
