package es.studium.bitacoraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.studium.bitacoraapp.Modelo.Cuaderno;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,DialogoAltaCuaderno {
    FloatingActionButton boton;
    RecyclerView recyclerView;
    CuadernoAdapter cuadernoAdapter;
    LinearLayoutManager lManager;
    ConsultaCuadernoRemota consultaCuadernoRemota;
    AltaCuadernoRemota altaCuadernoRemota;
    List<Cuaderno>listaCuaderno;
    int id;
    String nuevoCuaderno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerViewCuadernos);
        recyclerView.setHasFixedSize(true);
        boton=findViewById(R.id.floatingActionButtonMain);
        lManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lManager);
        consultaCuadernoRemota =new ConsultaCuadernoRemota();
        consultaCuadernoRemota.execute();
        listaCuaderno=consultaCuadernoRemota.getLista();
        Toast.makeText(this, "listaCuadernosize"+consultaCuadernoRemota.getLista().size(), Toast.LENGTH_SHORT).show();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cuadernoAdapter=new CuadernoAdapter(consultaCuadernoRemota.getLista());
        recyclerView.setAdapter(cuadernoAdapter);




        boton.setOnClickListener(this);
        // Listener de los clicks en la lista, o sea el RecyclerView
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                // Pasar a la actividad Main2.java
               id = listaCuaderno.get(position).getIdCuaderno();
                //Toast.makeText(MainActivity.this, ""+id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,
                        MainActivity2.class);
                intent.putExtra("idCuadernoFK", id);
                startActivity(intent);

            }
            @Override // Un toque largo
            public void onLongClick(View view, int position) {
                /*
                final FraseFamosa fraseParaEliminar = listaDeFrases.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(MainActivity.this)
                        .setPositiveButton("Sí, eliminar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        frasesController.eliminarFrase(fraseParaEliminar);
                                        refrescarListaDeFrases();
                                    }
                                })
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar la frase " +
                                fraseParaEliminar.getTexto() + "?")
                        .create();
                dialog.show();*/
                Toast.makeText(MainActivity.this, "toque largo", Toast.LENGTH_SHORT).show();
            }
        }));
            }







    @Override
    public void onClick(View view) {
        if(view.equals(boton))
        {
            AgregarCuaderno agregarCuaderno=new AgregarCuaderno();
            agregarCuaderno.setCancelable(false);
            agregarCuaderno.show(getSupportFragmentManager(),"Dime el nombre");
            Toast.makeText(getApplicationContext(), "dialogo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDialogoGuardarListener() {

    }

    @Override
    public void onDialogoCancelarListener() {

    }

    @Override
    public void onDataSet(String nombreCuaderno) {
        nuevoCuaderno=nombreCuaderno;
        altaCuadernoRemota=new AltaCuadernoRemota(nombreCuaderno);
        altaCuadernoRemota.execute();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consultaCuadernoRemota =new ConsultaCuadernoRemota();
        consultaCuadernoRemota.execute();
        listaCuaderno=consultaCuadernoRemota.getLista();
        Toast.makeText(this, "listaCuadernosize"+consultaCuadernoRemota.getLista().size(), Toast.LENGTH_SHORT).show();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cuadernoAdapter=new CuadernoAdapter(consultaCuadernoRemota.getLista());
        recyclerView.setAdapter(cuadernoAdapter);



    }
}