package com.example.biocaribe_congresodermatologia_2019.Entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Pregunta {

    @PrimaryKey(autoGenerate = true)
    long id;
    @ColumnInfo(name = "pregunta")
    String pregunta;
    @ColumnInfo(name = "linea")
    long linea;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public long getLinea() {
        return linea;
    }

    public void setLinea(long linea) {
        this.linea = linea;
    }

    @Ignore
    public Pregunta() {
    }

    public Pregunta(String pregunta, long linea) {
        this.pregunta = pregunta;
        this.linea = linea;
    }
}
