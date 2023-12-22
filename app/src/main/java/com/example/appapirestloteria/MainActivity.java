package com.example.appapirestloteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvDecimos;
    EditText etDecimo;

    Button btnAñadir;

    ArrayList<Decimo> listadoDecimos;

    ArrayList<DecimoSorteo> listadoSorteo;

    DecimoAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lvDecimos = findViewById(R.id.lvDecimos);
        etDecimo = findViewById(R.id.etDecimo);

        btnAñadir = findViewById(R.id.btnAñadir);

        listadoDecimos = new ArrayList<>();
        adaptador = new DecimoAdapter(this,listadoDecimos);
        lvDecimos.setAdapter(adaptador);

        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numero = etDecimo.getText().toString();
                Decimo d;
                DecimoSorteo ds = new DecimoSorteo(numero,"0");


                if(listadoSorteo.contains(ds)){
                    int posicion = listadoSorteo.indexOf(ds);
                    DecimoSorteo decimo = listadoSorteo.get(posicion);
                    d  = new Decimo(numero,true, decimo.getPremio().toString());
                }
                else{
                    d = new Decimo(numero,false,"0");
                }

                listadoDecimos.add(d);
                Toast.makeText(view.getContext(), "Numero añadido: "+numero, Toast.LENGTH_SHORT).show();

                adaptador.notifyDataSetChanged();
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


    }
}