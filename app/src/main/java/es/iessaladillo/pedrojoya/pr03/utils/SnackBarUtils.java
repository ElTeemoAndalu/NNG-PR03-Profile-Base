package es.iessaladillo.pedrojoya.pr03.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class SnackBarUtils {

    public void showSnackBar(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
