package com.example.appapirestloteria.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Decimos.db";

    public static final String TABLE_ALUMNOS ="t_decimos";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Comandos SQL de creación de nuestra tabla
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ALUMNOS + " ("
                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "numero" + " TEXT NOT NULL,"
                + "premiado" + " BOOLEAN NOT NULL,"
                + "cantidad" + " TEXT NOT NULL,"
                + "UNIQUE (" + "id" + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
        db.execSQL("DROP TABLE " + TABLE_ALUMNOS);
        onCreate(db);
    }
}
