package com.example.biocaribe_congresodermatologia_2019;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biocaribe_congresodermatologia_2019.Entidades.Respuesta;

import java.util.List;

public class RecycleViewListadoRespuestasAdapter extends RecyclerView.Adapter<RecycleViewListadoRespuestasAdapter.Holder>{
    private Context context;
    private List<Respuesta> listadoRespuestas;
    private Listener listener;

    public void setListadoRespuestas(List<Respuesta> listadoRespuestas) {
        this.listadoRespuestas = listadoRespuestas;
    }



    public RecycleViewListadoRespuestasAdapter(Context context, List<Respuesta> listadoRespuestas, Listener listener) {
        this.listadoRespuestas = listadoRespuestas;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_question, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        Log.d("CONTADOR",String.valueOf(i));

        TextView textView_respuesta = holder.itemView.findViewById(R.id.textView_respuesta);
        CardView cardView = holder.itemView.findViewById(R.id.cardview);
        textView_respuesta.setText(listadoRespuestas.get(i).getRespuesta());
        cardView.setCardBackgroundColor(Color.parseColor("#ededed"));

//
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(listadoRespuestas.get(i));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listadoRespuestas.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        public Holder(@NonNull View itemView) {
            super(itemView);
        }


    }

    public interface Listener{
        void onClick(Respuesta respuesta);
    }
}
