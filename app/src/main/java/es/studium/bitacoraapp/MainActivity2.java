package es.studium.bitacoraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import es.studium.bitacoraapp.Modelo.Apunte;
import es.studium.bitacoraapp.Modelo.Cuaderno;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener, DialogoEliminarCuaderno,DialogoAltaApunte {
    int idCuadernoFK;
    Button borrarCuaderno;
    FloatingActionButton boton;
    RecyclerView recyclerView2;
    ApunteAdapter apunteAdapter;
    LinearLayoutManager lManager;
    ConsultaApunteRemota consultaApunteRemota;
    BajaCuadernoRemota bajaCuadernoRemota;
    AltaApunteRemota altaApunteRemota;
    List<Apunte> listaApunte;
    Boolean seguro;
    EliminarCuaderno eliminarCuaderno;
    AgregarApunte agregarApunte;
    int id;
    String textoApunte;
    String fechaApunte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle parametros = this.getIntent().getExtras();
        idCuadernoFK = parametros.getInt("idCuadernoFK");
        Toast.makeText(this, "" + idCuadernoFK, Toast.LENGTH_SHORT).show();
        borrarCuaderno = findViewById(R.id.buttonBorrarCuaderno);
        boton = findViewById(R.id.floatingActionButtonMain2);
        recyclerView2 = findViewById(R.id.recyclerViewApuntes);
        recyclerView2.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(lManager);
        consultaApunteRemota = new ConsultaApunteRemota(idCuadernoFK);
        consultaApunteRemota.execute();
        listaApunte = consultaApunteRemota.getLista();
        Toast.makeText(this, "apuntes" + consultaApunteRemota.getLista().size(), Toast.LENGTH_SHORT).show();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apunteAdapter = new ApunteAdapter(consultaApunteRemota.getLista());
        recyclerView2.setAdapter(apunteAdapter);


        borrarCuaderno.setOnClickListener(this);
        boton.setOnClickListener(this);
        recyclerView2.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView2, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                // Pasar a la actividad Main2.java
                id = listaApunte.get(position).getIdApunte();
                Toast.makeText(MainActivity2.this, "toque corto"+id, Toast.LENGTH_SHORT).show();


            }
            @Override // Un toque largo
            public void onLongClick(View view, int position) {
                id = listaApunte.get(position).getIdApunte();

                Toast.makeText(MainActivity2.this, "toque largo"+id, Toast.LENGTH_SHORT).show();
            }
        }));
    }



    @Override
    public void onClick(View view) {
        if (view.equals(borrarCuaderno)) {
            eliminarCuaderno=new EliminarCuaderno();
            eliminarCuaderno.setCancelable(false);
            eliminarCuaderno.show(getSupportFragmentManager(),"Eliminar");
        }
        else if (view.equals(boton))
        {
            agregarApunte=new AgregarApunte();
            agregarApunte.setCancelable(false);
            agregarApunte.show(getSupportFragmentManager(),"Agregar");
        }
    }

    @Override
    public void onDialogoGuardarListener() {

    }

    @Override
    public void onDialogoCancelarListener() {

    }

    @Override
    public void onDataSetAltaApunte(String comentarioApunte, String fechaApunte) {
        textoApunte=comentarioApunte;
        this.fechaApunte=fechaApunte;
        String idFK=""+idCuadernoFK;
        altaApunteRemota=new AltaApunteRemota(fechaApunte,textoApunte,idFK);
        altaApunteRemota.execute();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consultaApunteRemota = new ConsultaApunteRemota(idCuadernoFK);
        consultaApunteRemota.execute();
        listaApunte = consultaApunteRemota.getLista();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        apunteAdapter = new ApunteAdapter(consultaApunteRemota.getLista());
        recyclerView2.setAdapter(apunteAdapter);



    }

    @Override
    public void onDataSetEliminar(boolean seguro) {
        this.seguro=seguro;
        if(seguro)
        {
            if (listaApunte.size() == 0) {
                String id = "" + idCuadernoFK;
                bajaCuadernoRemota = new BajaCuadernoRemota(id);
                bajaCuadernoRemota.execute();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "Borrado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity2.this,
                        MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Cuaderno LLeno", Toast.LENGTH_SHORT).show();
            }
        }
        }

    }

