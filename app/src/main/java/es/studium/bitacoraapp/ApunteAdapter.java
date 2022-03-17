package es.studium.bitacoraapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.studium.bitacoraapp.Modelo.Apunte;

public class ApunteAdapter extends RecyclerView.Adapter<ApunteHolder> {

    private final List<Apunte> lista;



    public ApunteAdapter(@NonNull List<Apunte> lista){
        this.lista = lista;

    }

    @NonNull
    @Override
    public ApunteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_apunte,parent,false);
        return new ApunteHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ApunteHolder holder, int position) {
        holder.bindRow(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
