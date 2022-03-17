package es.studium.bitacoraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import es.studium.bitacoraapp.Modelo.Apunte;
import es.studium.bitacoraapp.Modelo.Cuaderno;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    int idCuadernoFK;
    Button borrarCuaderno;
    FloatingActionButton boton;
    RecyclerView recyclerView2;
    ApunteAdapter apunteAdapter;
    LinearLayoutManager lManager;
    ConsultaApunteRemota consultaApunteRemota;
    List<Apunte>listaApunte;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle parametros = this.getIntent().getExtras();
        idCuadernoFK = parametros.getInt("idCuadernoFK");
        Toast.makeText(this, ""+idCuadernoFK, Toast.LENGTH_SHORT).show();
        borrarCuaderno=findViewById(R.id.buttonBorrarCuaderno);
        boton=findViewById(R.id.floatingActionButtonMain2);
        recyclerView2=findViewById(R.id.recyclerViewApuntes);
        recyclerView2.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(lManager);
        consultaApunteRemota = new ConsultaApunteRemota(idCuadernoFK);
        consultaApunteRemota.execute();
        listaApunte=consultaApunteRemota.getLista();
        Toast.makeText(this, "apuntes"+consultaApunteRemota.getLista().size(), Toast.LENGTH_SHORT).show();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apunteAdapter=new ApunteAdapter(consultaApunteRemota.getLista());
        recyclerView2.setAdapter(apunteAdapter);


        borrarCuaderno.setOnClickListener(this);

        }

    @Override
    public void onClick(View view) {
        if(view.equals(borrarCuaderno))
        {
            Toast.makeText(this, "Boton de borrar", Toast.LENGTH_SHORT).show();
        }
    }
}
