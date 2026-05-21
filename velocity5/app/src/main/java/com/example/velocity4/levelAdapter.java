package com.example.velocity4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
//    int bestTime;
//    int lastTime;
    OnDeleteListener deleteListener;
    public levelAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<level> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.objects = (ArrayList<level>) objects;
    }
    public void setOnDeleteListener(OnDeleteListener listener) {
        this.deleteListener = listener;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup Parent) {
        LayoutInflater layoutInflater  = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.levelistview,Parent,false);
        TextView levelname = view.findViewById(R.id.lvlname);
        TextView bestTime = view.findViewById(R.id.best_Time);
        TextView lastTime = view.findViewById(R.id.last_Time);
        level level = objects.get(position);
        levelname.setText("level: " + level.levelName);
        levelname.setTextColor(Color.WHITE);
        bestTime.setText(bestTime.getText() + "25");
        bestTime.setTextColor(Color.WHITE);
        lastTime.setText(lastTime.getText() + "25");
        lastTime.setTextColor(Color.WHITE);
        Button play = view.findViewById(R.id.startBtn);
        Button edit = view.findViewById(R.id.editbtn);
        Button del = view.findViewById(R.id.delbtn);
        edit.setOnClickListener(v -> EditLVL(position));
        play.setOnClickListener(v -> openLVL(position));
        del.setOnClickListener(v -> deleteListener.OnDel(objects.get(position),position));
        return view;
    }
    //starts level based on position
    public void openLVL(int position) {
        Intent intent = new Intent(context, gameActivity.class);
        intent.putExtra("level_id",objects.get(position).id);
        context.startActivity(intent);
    }
    //opens level editor based on position
    public void EditLVL(int position) {
        Intent intent = new Intent(context, editLevelsActivity.class);
        intent.putExtra("level_id",objects.get(position).id);
        context.startActivity(intent);
    }
}
