package com.example.velocity4;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;

import androidx.appcompat.app.AlertDialog;

public class obstacle extends shape {
    public obstacle(int x, int y, int width, int height, Bitmap bitmap) {
        super(x, y, width, height, bitmap);
    }
    int damage;

}
