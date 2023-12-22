package com.example.appapirestloteria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appapirestloteria.Decimo;

import java.util.ArrayList;

public class DecimoDB extends DbHelper{
    Context context;

    SQLiteDatabase db;
    DbHelper dbHelper;

    ArrayList<Decimo> listDecimo;

    public DecimoDB(Context context) {

        super(context);
        this.context = context;
    }

    public long insertarDecimo(String numero, Boolean premiado, String cantidad){
        long id=0;

        try{
            dbHelper = new DbHelper(context);
            db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("numero",numero);
            values.put("premiado",premiado);
            values.put("cantidad",cantidad);

            id = db.insert(TABLE_ALUMNOS,null,values);

        }catch(Exception ex){
            ex.toString();
        }


        return id;

    }

    public ArrayList recuperarDecimos(){

        //Devuelve 0 si el usuario no lo encuentra
        //Devuelve 1 si el usuario esta en la BD

        try{
            dbHelper = new DbHelper(context);
            db = dbHelper.getWritableDatabase();
            String query = "select * from " +TABLE_ALUMNOS;
            Log.d("DecimosDB",query);
            Cursor cursor = db.rawQuery(query,null);

            //Creamos el arrayList a partir de cada fila que devuelve la consulta de datos.
            if(cursor != null && cursor.moveToFirst()){
                do{
                    String colNumero = cursor.getString(1);
                    String colPremiado = cursor.getString(2);
                    String colCantidad = cursor.getString(3);

                    Log.d("DecimosDB",colCantidad);

                    //Decimo d = new Decimo(colNumero,colPremiado,colCantidad);

                }while(cursor.moveToNext());
            }
            return listDecimo;
        }catch(Exception ex){
            ex.toString();
            return null;
        }

    }


}
