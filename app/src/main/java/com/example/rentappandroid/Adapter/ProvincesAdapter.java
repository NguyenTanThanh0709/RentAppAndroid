package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rentappandroid.Dto.Provinces;

import java.util.List;

public class ProvincesAdapter extends ArrayAdapter<Provinces> {

    public ProvincesAdapter(Context context, List<Provinces> provincesList) {
        super(context, 0, provincesList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        Provinces province = getItem(position);

        if (province != null) {
            textView.setText(province.getName());
        }

        return convertView;
    }
}
