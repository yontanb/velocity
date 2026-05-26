package com.example.velocity4;

import android.annotation.SuppressLint;
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
    OnDeleteListener deleteListener;

    /**
     * creates a new levelAdapter
     * @param context the level context
     * @param resource not used
     * @param textViewResourceId not used
     * @param objects the list of objects to display
     */
    public levelAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<level> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.objects = (ArrayList<level>) objects;
    }

    /**
     * sets to trigger a event when deleting a level to handle it
     * @param listener the listener to the deletion
     */
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
        bestTime.setText(String.format("%s %s",bestTime.getText(),formatTime(level.besttime)));
        bestTime.setTextColor(Color.WHITE);
        lastTime.setText(String.format("%s %s",lastTime.getText(),formatTime(level.lasttime)));
        lastTime.setTextColor(Color.WHITE);
        Button play = view.findViewById(R.id.startBtn);
        Button edit = view.findViewById(R.id.editbtn);
        Button del = view.findViewById(R.id.delbtn);
        edit.setOnClickListener(v -> EditLVL(position));
        play.setOnClickListener(v -> openLVL(position));
        del.setOnClickListener(v -> deleteListener.OnDel(objects.get(position),position));
        return view;
    }

    /**
     * starts level based on position
     * @param position the level position in objects
     */
    public void openLVL(int position) {
        Intent intent = new Intent(context, gameActivity.class);
        intent.putExtra("level_id",objects.get(position).id);
        context.startActivity(intent);
    }
    /**
     * opens level editor with level based on position
     * @param position the level position in objects
     */
    public void EditLVL(int position) {
        Intent intent = new Intent(context, editLevelsActivity.class);
        intent.putExtra("level_id",objects.get(position).id);
        context.startActivity(intent);
    }

    /**
     * formats the time into a string
     * @param time the time in millis
     * @return the time formatted into string
     */
    @SuppressLint("DefaultLocale")
    public String formatTime(long time) {
        return String.format("%d:%02d:%03d",time/60000,(time/1000)%60,time%1000);
    }
}
