package com.faan.mascotass5.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by FAAN on 20/02/2017.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBD.DATABASE_NAME, null, ConstantesBD.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascotas = "CREATE TABLE " + ConstantesBD.TABLE_MASCOTA + "(" +
                ConstantesBD.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBD.TABLE_MASCOTA_FOTO + " INTEGER, " +
                ConstantesBD.TABLE_MASCOTA_NOMBRE + " TEXT, " +
                ")";

        String queryCrearTablaLikesMascotas = "CREATE TABLE " + ConstantesBD.TABLE_LIKES_MASCOTA + "(" +
                ConstantesBD.TABLE_LIKES_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBD.TABLE_LIKES_MASCOTA_ID_MASCOTA + " INTEGER, " +
                ConstantesBD.TABLE_LIKES_MASCOTA_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBD.TABLE_LIKES_MASCOTA_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBD.TABLE_MASCOTA + "(" + ConstantesBD.TABLE_MASCOTA_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaMascotas);
        db.execSQL(queryCrearTablaLikesMascotas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + ConstantesBD.TABLE_LIKES_MASCOTA + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + ConstantesBD.TABLE_MASCOTA + "'");
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerCincoMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        // Obtener las  5 mascotas
        String query = "SELECT * FROM " + ConstantesBD.TABLE_LIKES_MASCOTA +
                " ORDER BY " + ConstantesBD.TABLE_LIKES_MASCOTA_ID + " DESC" +
                " LIMIT (5)";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()) {
            Mascota mascota = new Mascota();

            String queryMascota = "SELECT * FROM " + ConstantesBD.TABLE_MASCOTA +
                    " WHERE " + ConstantesBD.TABLE_MASCOTA_ID + "=" + registros.getInt(1);
            Cursor reguistrosMascota =  db.rawQuery(queryMascota, null);

            if (reguistrosMascota.moveToNext()) {
                mascota.setId(reguistrosMascota.getInt(0));
                mascota.setFoto(reguistrosMascota.getInt(1));
                mascota.setNombre(reguistrosMascota.getString(2));
            }
            mascota.setLikes(registros.getInt(2));
            mascotas.add(mascota);
        }

        db.close();
        return mascotas;
    }

    public ArrayList<Mascota> obtenerTodasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBD.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null); // 2do parametro filtro

        while (registros.moveToNext()) {
            Mascota mascota = new Mascota();
            mascota.setId(registros.getInt(0));
            mascota.setFoto(registros.getInt(1));
            mascota.setNombre(registros.getString(2));
            String queryLikes = "SELECT COUNT(" + ConstantesBD.TABLE_LIKES_MASCOTA_LIKES + ") as likes" +
                    " FROM " + ConstantesBD.TABLE_LIKES_MASCOTA +
                    " WHERE " + ConstantesBD.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + mascota.getId();
            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()) {
                mascota.setLikes(registrosLikes.getInt(0));
            } else {
                mascota.setLikes(0);
            }
            mascotas.add(mascota);
        }

        db.close();
        return mascotas;
    }

    public void insertarMascotas(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_MASCOTA, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_LIKES_MASCOTA, null, contentValues);
        db.close();
    }

    public int getLikesMascotas(Mascota mascota) {
        int likes = 0;
        String query = "SELECT COUNT(" + ConstantesBD.TABLE_LIKES_MASCOTA_LIKES + ") " +
                "FROM " + ConstantesBD.TABLE_LIKES_MASCOTA +
                " WHERE " + ConstantesBD.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            likes = registros.getInt(0);
        }

        db.close();
        return likes;
    }
}
