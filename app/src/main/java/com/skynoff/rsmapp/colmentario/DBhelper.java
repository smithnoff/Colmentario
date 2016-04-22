package com.skynoff.rsmapp.colmentario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Equipo Usuario SAINT on 27/10/2015.
 */
public class DBhelper extends SQLiteOpenHelper {
    private static final String name="Libro_inventario_2016";
    private static final int version=1;
    public DBhelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Manejador.crear_tabla);
        db.execSQL(Manejador.crear_tabla2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXIST SAPROD");
            db.execSQL(Manejador.crear_tabla);
            db.execSQL(Manejador.crear_tabla2);
        }
    }
}
