package com.example.velocity4;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class levelAdapter extends ArrayAdapter<level> {

    public levelAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<level> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
