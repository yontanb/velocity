package com.example.velocity4;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class levelAdapter extends ArrayAdapter<level> {
    ArrayList<level> objects;
    Context context;
    public levelAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<level> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.objects = (ArrayList<level>) objects;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup Parent) {
        LayoutInflater layoutInflater  = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.levelistview,Parent,false);
        TextView levelname = view.findViewById(R.id.lvlname);
        TextView bestTime = view.findViewById(R.id.best_Time);
        TextView lastTime = view.findViewById(R.id.last_Time);
        level level = objects.get(position);
        levelname.setText(levelname.getText() + level.levelname);
        bestTime.setText(bestTime.getText() + "20.00");
        lastTime.setText(lastTime.getText() + "25.00");
        return view;
    }
}
