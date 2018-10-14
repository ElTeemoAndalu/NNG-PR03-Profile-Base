package es.iessaladillo.pedrojoya.pr03.business;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr03.R;
import es.iessaladillo.pedrojoya.pr03.utils.ValidationUtils;

public class CustomTextWatcher implements TextWatcher {

    private final View view;
    private final Activity activity;
    private final String errorMessage;

    public CustomTextWatcher(View view, Activity activity){
        this.view = view;
        this.activity = activity;
        errorMessage = null;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        EditText changedTxt;

        if(view.getId() == R.id.txtName || view.getId() == R.id.txtAddress){

        }else if(view.getId() == R.id.txtName){
            changedTxt = ActivityCompat.requireViewById(activity, R.id.txtName);
            if(!ValidationUtils.isValidEmail(s.toString())){
                changedTxt.setError();
            }

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
