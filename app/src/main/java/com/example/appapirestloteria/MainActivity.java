package com.example.appapirestloteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvDecimos;
    EditText etDecimo;

    Button btnAñadir;

    ArrayList<String> listadoDecimos;

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

                Toast.makeText(view.getContext(), "Numero añadido: "+numero, Toast.LENGTH_SHORT).show();
                listadoDecimos.add(numero);
                adaptador.notifyDataSetChanged();
            }
        });


    }
}