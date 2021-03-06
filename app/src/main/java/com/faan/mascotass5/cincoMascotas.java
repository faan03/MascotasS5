package com.faan.mascotass5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

//import com.faan.mascotass5.MascotaAdaptador;


import java.util.ArrayList;

public class cincoMascotas extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    private RecyclerView lista5Mascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinco_mascotas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionBarcinco);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lista5Mascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lista5Mascotas.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializarAdaptador();
    }

    public void inicializarAdaptador() {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas,this, getApplicationContext());
        lista5Mascotas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas() {
        mascotas = new ArrayList<Mascota>();
        ContructorMascotas constructor = new ContructorMascotas(getApplicationContext());
        BaseDeDatos db = new BaseDeDatos(getApplicationContext());
        constructor.insertarMascotas(db);
//        mascotas = constructor.obtenerCincoMascotas();
        mascotas = constructor.obtenerMascotas();

    }
}