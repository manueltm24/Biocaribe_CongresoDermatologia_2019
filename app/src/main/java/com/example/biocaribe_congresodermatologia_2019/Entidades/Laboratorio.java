package com.example.biocaribe_congresodermatologia_2019.Entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Laboratorio {

    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name = "nombre")
    String nombre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Ignore
    public Laboratorio() {
    }

    public Laboratorio(String nombre) {
        this.nombre = nombre;
    }
}
