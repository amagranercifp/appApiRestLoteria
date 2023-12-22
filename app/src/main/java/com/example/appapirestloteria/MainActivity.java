package com.example.appapirestloteria;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appapirestloteria.db.DbHelper;
import com.example.appapirestloteria.db.DecimoDB;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvDecimos;
    EditText etDecimo;

    Button btnAñadir;

    ArrayList<Decimo> listadoDecimos = new ArrayList<>();

    ArrayList<DecimoSorteo> listadoSorteo;

    DecimoAdapter adaptador;

    DecimoDB decimoDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        decimoDb = new DecimoDB(MainActivity.this);

        lvDecimos = findViewById(R.id.lvDecimos);
        etDecimo = findViewById(R.id.etDecimo);

        btnAñadir = findViewById(R.id.btnAñadir);

        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numero = etDecimo.getText().toString();
                Decimo d = new Decimo(numero,false,"0");
                //DecimoSorteo ds = new DecimoSorteo(numero,"0");

                // si el número ya ha sido insertado manualmente ya estará en BD y se habrá recuperado al cargar la lista
                if(listadoDecimos == null){
                    listadoDecimos = new ArrayList<>();
                    añadeDecimo(numero);
                }
                else{
                    // si es un número nuevo no introducido antes -> se almacenara
                    if(!listadoDecimos.contains(d)){
                        añadeDecimo(numero);
                    }
                    else{
                        Toast.makeText(view.getContext(), "El número: "+numero+" ya está en la lista", Toast.LENGTH_SHORT).show();
                    }

                }
                // Después de guardar el número, reseteamos el valor del campo para que sea rapido poner mas números
                etDecimo.setText("");

            }
        });

        new Thread( new Runnable(){

            @Override
            public void run() {
                try{
                    String URL = "https://www.loteriasyapuestas.es/es/loteria-nacional/tablas-y-alambres.provisional#Tabla=1";
                    Document doc = Jsoup.connect(URL).timeout(6000).get();

                    //Element tabla = doc.select("cuerpoRegionInf contenidoRegion div table").first();

                    //Elements newsHeadlines = doc.select("#wire11 tbody tr");

                    Elements newsHeadlines = doc.select(".cuerpoRegionInf .contenidoRegion div table");

                    listadoSorteo = new ArrayList<>();

                    if(!newsHeadlines.isEmpty()){

                        for (Element headline : newsHeadlines) {
                            String numero = headline.select("tbody td p").text();
                            String premio = headline.select("tbody td span").text();
                            String [] numeros = numero.split(" ");
                            String [] premios = premio.split(" ");
                            for(int i=0; i< numeros.length; i++){
                                Log.d("NUMEROS",numeros[i] + " " + premios[i]);
                                DecimoSorteo numSort = new DecimoSorteo(numeros[i],premios[i]);
                                listadoSorteo.add(numSort);
                            }

                        }
                    }

                }
                catch(Exception e){
                    Log.d("Errror","Error en el thread");
                }
            }
        }).start();

        // volcado de datos desde SQLite

        listadoDecimos = decimoDb.recuperarDecimos();

        if(listadoDecimos == null) {

            listadoDecimos = new ArrayList<>();
            Log.d("VOLCADO","Volcado completado");
        }

        adaptador = new DecimoAdapter(this,listadoDecimos);
        lvDecimos.setAdapter(adaptador);

    }

    public void añadeDecimo(String numero){

        Decimo d = new Decimo(numero,false,"0");
        DecimoSorteo ds = new DecimoSorteo(numero,"0");

        // Si esta vacia, se inserta el decimo

        // esta comprobacion es para saber si esta premiado...
        if(listadoSorteo.contains(ds)){
            int posicion = listadoSorteo.indexOf(ds);
            DecimoSorteo decimo = listadoSorteo.get(posicion);
            d.setPremiado(true);
            d.setCantidad(decimo.getPremio().toString());
            //d  = new Decimo(numero,true, decimo.getPremio().toString());
            decimoDb.insertarDecimo(numero,true,decimo.getPremio().toString());
        }
        else{
            d.setPremiado(false);
            d.setCantidad("0");
            //d = new Decimo(numero,false,"0");
            decimoDb.insertarDecimo(numero,false,"0");
        }

        listadoDecimos.add(d);

        Toast.makeText(this, "Número añadido: "+numero, Toast.LENGTH_SHORT).show();

        adaptador.notifyDataSetChanged();

    }
}