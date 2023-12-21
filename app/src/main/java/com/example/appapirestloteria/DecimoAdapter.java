package com.example.appapirestloteria;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DecimoAdapter extends ArrayAdapter<String> {

    private final Activity context;

    ArrayList<String> listaDecimos;

    LayoutInflater inflater;

    public DecimoAdapter(Activity c, ArrayList<String> l){
        super(c, R.layout.decimo_item_list,l);
        this.context = c;
        this.listaDecimos = l;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.decimo_item_list, null, true);

        String decimo = listaDecimos.get(position).toString();

        TextView tvNumDecimo, tvCheck;

        tvNumDecimo = rowView.findViewById(R.id.tvNumDecimo);
        tvCheck = rowView.findViewById(R.id.tvCheck);


        tvNumDecimo.setText(decimo);
        tvCheck.setText("OK");

        return rowView;
    }
}
