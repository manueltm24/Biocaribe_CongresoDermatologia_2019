package com.example.biocaribe_congresodermatologia_2019.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.biocaribe_congresodermatologia_2019.Entidades.Laboratorio;

import java.util.List;

@Dao
public interface LaboratorioDao {

    @Insert
    long insert(Laboratorio laboratorio);

    @Delete
    void delete(Laboratorio laboratorio);

    @Query("SELECT * FROM Laboratorio")
    List<Laboratorio> getAllLaboratorios();

    @Query("SELECT * FROM Laboratorio WHERE nombre LIKE :nombre LIMIT 1")
    Laboratorio findByNombre(String nombre);

}
