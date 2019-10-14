package com.example.biocaribe_congresodermatologia_2019.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.biocaribe_congresodermatologia_2019.Entidades.Linea;

import java.util.List;

@Dao
public interface LineaDao {

    @Insert
    long insert(Linea linea);

    @Delete
    void delete(Linea linea);

    @Update
    void update(Linea linea);

    @Query("SELECT * FROM Linea")
    List<Linea> getAllLineas();

    @Query("SELECT * FROM Linea WHERE nombre LIKE :nombre LIMIT 1")
    Linea findByNombre(String nombre);

    @Query("SELECT * FROM Linea WHERE id LIKE :id LIMIT 1")
    Linea findById(long id);

    @Query("SELECT * FROM Linea WHERE laboratorio LIKE :laboratorio")
    List<Linea> findAllByLaborario(long laboratorio);
}
