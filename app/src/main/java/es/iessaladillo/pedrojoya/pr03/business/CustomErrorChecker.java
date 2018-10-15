package es.iessaladillo.pedrojoya.pr03.business;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import es.iessaladillo.pedrojoya.pr03.R;
import es.iessaladillo.pedrojoya.pr03.utils.ValidationUtils;

public class CustomErrorChecker {

    private CustomErrorChecker() {
    }

    //Separate methods for each EditText to check for errors
    public static void checkNameError(EditText txt, TextView lbl, String errorMsg) {

        if (TextUtils.isEmpty(txt.getText().toString())) {
            setFieldState(txt, null, lbl, false, errorMsg);
        } else {
            setFieldState(txt, null, lbl, true, errorMsg);
        }
    }


    public static void checkEmailError(EditText txt, ImageView imgTxt, TextView lbl, String errorMsg) {
        if (!ValidationUtils.isValidEmail(txt.getText().toString())) {
            setFieldState(txt, imgTxt, lbl, false, errorMsg);
        } else {
            setFieldState(txt, imgTxt, lbl, true, errorMsg);
        }
    }

    public static void checkPhonenumberError(EditText txt, ImageView imgTxt, TextView lbl, String errorMsg) {
        if (!ValidationUtils.isValidPhone(txt.getText().toString())) {
            setFieldState(txt, imgTxt, lbl, false, errorMsg);
        } else {
            setFieldState(txt, imgTxt, lbl, true, errorMsg);
        }
    }

    public static void checkAddressError(EditText txt, ImageView imgTxt, TextView lbl, String errorMsg) {

        if (TextUtils.isEmpty(txt.getText().toString())) {
            setFieldState(txt, imgTxt, lbl, false, errorMsg);
        } else {
            setFieldState(txt, imgTxt, lbl, true, errorMsg);
        }
    }

    public static void checkWebError(EditText txt, ImageView imgTxt, TextView lbl, String errorMsg) {
        if (!ValidationUtils.isValidUrl(txt.getText().toString())) {
            setFieldState(txt, imgTxt, lbl, false, errorMsg);
        } else {
            setFieldState(txt, imgTxt, lbl, true, errorMsg);
        }
    }

    private static void setFieldState(EditText txt, ImageView imgTxt, TextView lbl, Boolean enable, String errorMsg) {
        if (!enable) {
            txt.setError(errorMsg);
        } else {
            txt.setError(null);
        }
        if (txt.getId() != R.id.txtName) {
            imgTxt.setEnabled(enable);
        }
        lbl.setEnabled(enable);
    }
}
