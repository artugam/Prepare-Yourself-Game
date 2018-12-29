package com.example.artur.prepareyourself.Helpers;

import android.content.Context;

public class Toast {

    public static void showMessage(Context context, String message)
    {
        android.widget.Toast.makeText(context,
                message,
                android.widget.Toast.LENGTH_LONG).show();
    }
}
