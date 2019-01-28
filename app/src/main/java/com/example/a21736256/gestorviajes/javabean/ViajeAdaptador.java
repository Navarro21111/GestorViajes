package com.example.a21736256.gestorviajes.javabean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a21736256.gestorviajes.R;

import java.util.ArrayList;

    public class ViajeAdaptador extends RecyclerView.Adapter<ViajeAdaptador.ViajeViewFolder> {

    private ArrayList<Viaje> lista;

    public ViajeAdaptador(ArrayList<Viaje> lista) {
        this.lista = lista;
    }

        public class ViajeViewFolder extends RecyclerView.ViewHolder {
            TextView tvNombre;
            TextView tvCiudad;
            TextView tvPais;
            TextView tvNumDias;
            TextView tvPrecio;

            public ViajeViewFolder(@NonNull View itemView) {
                super(itemView);
                tvNombre=itemView.findViewById(R.id.tvNombreViaje);
                tvCiudad=itemView.findViewById(R.id.tvNombreCiudad);
                tvPais=itemView.findViewById(R.id.tvNombrePais);
                tvNumDias=itemView.findViewById(R.id.tvNumDias);
                tvPrecio=itemView.findViewById(R.id.tvPrecio);
            }
            public void bindViaje(Viaje m){
                tvNombre.setText(m.getNombre());
                tvCiudad.setText(m.getCiudad());
                tvPais.setText(m.getPais());
                tvNumDias.setText(String.valueOf(m.getNumDias()));
                tvPrecio.setText(String.valueOf(m.getPrecio()));
            }
        }


    @NonNull
    @Override
    public ViajeViewFolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_viaje , parent, false);
        ViajeViewFolder vh=new ViajeViewFolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViajeViewFolder mensajeViewFolder, int i) {
        mensajeViewFolder.bindViaje(lista.get(i));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }



    public void clear(){
        lista.clear();
    }


}

