package es.iessaladillo.pedrojoya.pr03.business;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr03.R;
import es.iessaladillo.pedrojoya.pr03.ui.main.MainActivity;
import es.iessaladillo.pedrojoya.pr03.utils.ValidationUtils;

public class CustomTextWatcher implements TextWatcher {

    private final EditText txt;
    private final Activity activity;
    private final String errorMessage;

    public CustomTextWatcher(EditText txt, Activity activity,String errorMessage){
        this.txt = txt;
        this.activity = activity;
        this.errorMessage = errorMessage;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        EditText changedTxt;

        if(txt.getId() == R.id.txtName || txt.getId() == R.id.txtAddress){

        }else if(txt.getId() == R.id.txtEmail){

        }
    }

    @Override
    public void afterTextChanged(Editable s) {}
}
