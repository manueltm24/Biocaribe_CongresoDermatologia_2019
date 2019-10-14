package com.example.biocaribe_congresodermatologia_2019.Entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Respuesta {

    @PrimaryKey(autoGenerate = true)
    long id;
    @ColumnInfo(name = "respuesta")
    String respuesta;
    @ColumnInfo(name = "pregunta")
    long pregunta;
    @ColumnInfo(name = "valida")
    boolean valida;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public long getPregunta() {
        return pregunta;
    }

    public void setPregunta(long pregunta) {
        this.pregunta = pregunta;
    }

    public boolean isValida() {
        return valida;
    }

    public void setValida(boolean valida) {
        this.valida = valida;
    }

    @Ignore
    public Respuesta() {
    }

    public Respuesta(String respuesta, long pregunta, boolean valida) {
        this.respuesta = respuesta;
        this.pregunta = pregunta;
        this.valida = valida;
    }
}
