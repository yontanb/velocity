package com.example.velocity4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        levelname.setText(level.levelname);
        bestTime.setText(bestTime.getText() + "20.00");
        lastTime.setText(lastTime.getText() + "25.00");
        Button play = view.findViewById(R.id.startBtn);
        Button edit = view.findViewById(R.id.editbtn);
        edit.setOnClickListener(v -> EditLVL(position));
        play.setOnClickListener(v -> openLVL(position));

        return view;
    }
    public void openLVL(int position) {
        Intent intent = new Intent(context, gameActivity.class);
        intent.putExtra("level",position);
        context.startActivity(intent);
    }
    public void EditLVL(int position) {
        Intent intent = new Intent(context, editLevelsActivity.class);
        intent.putExtra("level",position);
        context.startActivity(intent);
    }

}
