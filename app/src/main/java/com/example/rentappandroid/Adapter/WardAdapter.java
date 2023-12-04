package com.example.rentappandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rentappandroid.Dto.Ward;

import java.util.List;

public class WardAdapter extends ArrayAdapter<Ward> {

    public WardAdapter(Context context, List<Ward> wardList) {
        super(context, 0, wardList);
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
        Ward ward = getItem(position);

        if (ward != null) {
            textView.setText(ward.getName());
        }

        return convertView;
    }
}
