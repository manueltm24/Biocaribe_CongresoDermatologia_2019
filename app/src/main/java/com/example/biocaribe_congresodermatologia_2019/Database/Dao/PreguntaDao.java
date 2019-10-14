package com.example.biocaribe_congresodermatologia_2019.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.biocaribe_congresodermatologia_2019.Entidades.Pregunta;

import java.util.List;

@Dao
public interface PreguntaDao {

    @Insert
    long insert(Pregunta pregunta);

    @Delete
    void delete(Pregunta pregunta);

    @Query("SELECT * FROM Pregunta")
    List<Pregunta> getAllPreguntas();

    @Query("SELECT * FROM Pregunta WHERE linea LIKE :linea")
    List<Pregunta> findAllByLinea(long linea);

    @Query("SELECT * FROM Pregunta WHERE id LIKE :id LIMIT 1")
    Pregunta findById(long id);
}
