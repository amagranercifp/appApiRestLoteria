package com.example.appapirestloteria;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DecimoAdapter extends ArrayAdapter<Decimo> {

    private final Activity context;

    ArrayList<Decimo> listaDecimos;

    LayoutInflater inflater;

    public DecimoAdapter(Activity c, ArrayList<Decimo> l){
        super(c, R.layout.decimo_item_list,l);
        this.context = c;
        this.listaDecimos = l;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.decimo_item_list, null, true);

        String decimo = listaDecimos.get(position).getNumero().toString();
        Boolean premiado = listaDecimos.get(position).getPremiado();
        String cantidad = listaDecimos.get(position).getCantidad();
        String msg = "Número NO premiado";

        if(premiado){
            msg="Número premiado";
        }

        TextView tvNumDecimo, tvCheck, tvCantidad;
        ImageButton ibBorrar;

        tvNumDecimo = rowView.findViewById(R.id.tvNumDecimo);
        tvCheck = rowView.findViewById(R.id.tvCheck);
        tvCantidad = rowView.findViewById(R.id.tvCantidad);

        ibBorrar = rowView.findViewById(R.id.ibBorrar);

        ibBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaDecimos.remove(position);
                notifyDataSetChanged();
            }
        });


        tvNumDecimo.setText(decimo);
        tvCheck.setText(msg);
        tvCantidad.setText(cantidad);

        return rowView;
    }
}
