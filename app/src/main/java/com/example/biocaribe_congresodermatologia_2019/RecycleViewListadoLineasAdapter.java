package com.example.biocaribe_congresodermatologia_2019;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biocaribe_congresodermatologia_2019.Entidades.Linea;

import java.util.List;

public class RecycleViewListadoLineasAdapter extends RecyclerView.Adapter<RecycleViewListadoLineasAdapter.Holder> {

    private Context context;

    public void setListadoLineas(List<Linea> listadoLineas) {
        this.listadoLineas = listadoLineas;
    }

    private List<Linea> listadoLineas;
    private Listener listener;

    public RecycleViewListadoLineasAdapter(Context context, List<Linea> listadoLineas, Listener listener) {
        this.listadoLineas = listadoLineas;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_linea, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {

        CardView cardView = holder.itemView.findViewById(R.id.cardview);
        TextView textView_nombre = holder.itemView.findViewById(R.id.textView_nombre);
        textView_nombre.setText(listadoLineas.get(i).getNombre());
        if(listadoLineas.get(i).isActiva()){
            cardView.setCardBackgroundColor(Color.GREEN);

        }
        else {
            cardView.setCardBackgroundColor(Color.RED);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(listadoLineas.get(i));
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listadoLineas.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        public Holder(@NonNull View itemView) {
            super(itemView);
        }


    }

    public interface Listener{
        void onClick(Linea linea);
    }
}
