package es.studium.bitacoraapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import es.studium.bitacoraapp.Modelo.Cuaderno;

class CuadernoAdapter extends RecyclerView.Adapter<CuadernoAdapter.MyViewHolder>{
    //Necesitamos una lista de objetos cuaderno
    private List<Cuaderno> listaDeCuadernos;


    //El adaptador se contruye con esa lista
    public CuadernoAdapter(List<Cuaderno> cuadernos) {
        this.listaDeCuadernos = cuadernos;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //holder corresponde con cada fila. Enlazamos los componentes de la fila.
        TextView nombreCuaderno;
        MyViewHolder(View itemView) {
            super(itemView);
            this.nombreCuaderno = itemView.findViewById(R.id.txtNombreCuaderno);

        }

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflamos con las filas
        View filaCuaderno =
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_cuaderno,
                        viewGroup, false);

        return new MyViewHolder(filaCuaderno);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //Por cada uno de la fila sacamos el nombre y lo metemos en el holder
// Obtener la frase de nuestra lista gracias al índice i
        Cuaderno cuaderno = listaDeCuadernos.get(i);
// Obtener los datos de la lista
        String nombreCuaderno = cuaderno.getNombreCuaderno();
        Log.i("adapter", ""+nombreCuaderno);

// Y poner a los TextView los datos con setText
        myViewHolder.nombreCuaderno.setText(nombreCuaderno);
    }
    @Override
    public int getItemCount() {
        return listaDeCuadernos.size();
    }


}