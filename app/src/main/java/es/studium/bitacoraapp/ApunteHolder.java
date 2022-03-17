package es.studium.bitacoraapp;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.studium.bitacoraapp.Modelo.Apunte;

public class ApunteHolder extends RecyclerView.ViewHolder {
    private final TextView texto;
    private final TextView fecha;
    public ApunteHolder(@NonNull View itemView) {
        super(itemView);
        this.texto =itemView.findViewById(R.id.txtComentario);
       this.fecha =itemView.findViewById(R.id.txtFecha);
    }
    public void bindRow(@NonNull Apunte apunte){
        texto.setText(apunte.getTextoApunte());
        fecha.setText(apunte.getFechaApunte());

    }
}
