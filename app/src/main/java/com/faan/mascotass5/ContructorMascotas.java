package com.faan.mascotass5;


import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

public class ContructorMascotas {
    private static final int LIKE = 1;
    private Context context;

    public ContructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerCincoMascotas() {
        BaseDeDatos db = new BaseDeDatos(context);
        return db.obtenerCincoMascotas();
    }

    public ArrayList<Mascota> obtenerMascotas() {
        BaseDeDatos db = new BaseDeDatos(context);

        if (db.obtenerTodasMascotas().isEmpty()) {
            insertarMascotas(db);
        }
        return db.obtenerTodasMascotas();
    }

    public void insertarMascotas(BaseDeDatos db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TABLE_MASCOTA_FOTO, R.drawable.micho);
        contentValues.put(ConstantesBD.TABLE_MASCOTA_NOMBRE, "Micho I");
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBD.TABLE_MASCOTA_FOTO, R.drawable.micho2);
        contentValues.put(ConstantesBD.TABLE_MASCOTA_NOMBRE, "Micho II");
        db.insertarMascotas(contentValues);


        contentValues.put(ConstantesBD.TABLE_MASCOTA_FOTO, R.drawable.micho3);
        contentValues.put(ConstantesBD.TABLE_MASCOTA_NOMBRE, "Micho III");
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBD.TABLE_MASCOTA_FOTO, R.drawable.micho);
        contentValues.put(ConstantesBD.TABLE_MASCOTA_NOMBRE, "Micho IV");
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBD.TABLE_MASCOTA_FOTO, R.drawable.micho2);
        contentValues.put(ConstantesBD.TABLE_MASCOTA_NOMBRE, "Micho V");
        db.insertarMascotas(contentValues);

    }

    public void darLikeMascota(Mascota mascota) {
        BaseDeDatos db = new BaseDeDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getIdMascota());
        contentValues.put(ConstantesBD.TABLE_LIKES_MASCOTA_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota) {
        BaseDeDatos db = new BaseDeDatos(context);
        return db.getLikesMascotas(mascota);
    }
}
