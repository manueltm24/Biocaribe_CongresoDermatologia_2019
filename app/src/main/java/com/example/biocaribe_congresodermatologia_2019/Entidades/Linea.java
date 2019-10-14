package com.example.biocaribe_congresodermatologia_2019.Entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Linea {
    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name = "laboratorio")
    long laboratorio;

    @ColumnInfo(name = "nombre")
    String nombre;

    @ColumnInfo(name = "activa")
    boolean activa;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(long laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Ignore
    public Linea() {
    }

    public Linea(long laboratorio, String nombre) {
        this.laboratorio = laboratorio;
        this.nombre = nombre;
        this.activa = true;
    }
}
