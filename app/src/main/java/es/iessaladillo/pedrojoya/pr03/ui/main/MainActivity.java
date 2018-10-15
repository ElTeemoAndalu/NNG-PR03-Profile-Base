package es.iessaladillo.pedrojoya.pr03.ui.main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr03.R;
import es.iessaladillo.pedrojoya.pr03.business.CustomTextWatcher;
import es.iessaladillo.pedrojoya.pr03.data.local.Database;
import es.iessaladillo.pedrojoya.pr03.data.local.model.Avatar;

public class MainActivity extends AppCompatActivity {

    private ImageView imgAvatar;
    private TextView lblAvatar;
    private final int EDITTEXTQUANTITY = 5;
    private EditText txtFields[] = new EditText[EDITTEXTQUANTITY];
    private TextView lblFields[] = new TextView[EDITTEXTQUANTITY];
    private String errorMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        // TODO
    }

    private void initViews() {
        errorMsg = getString(R.string.main_invalid_data);
        imgAvatar = ActivityCompat.requireViewById(this, R.id.imgAvatar);
        lblAvatar = ActivityCompat.requireViewById(this, R.id.lblAvatar);


        txtFields[0] = ActivityCompat.requireViewById(this, R.id.txtName);
        txtFields[1] = ActivityCompat.requireViewById(this, R.id.txtEmail);
        txtFields[2] = ActivityCompat.requireViewById(this, R.id.txtPhonenumber);
        txtFields[3] = ActivityCompat.requireViewById(this, R.id.txtAddress);
        txtFields[4] = ActivityCompat.requireViewById(this, R.id.txtWeb);

        lblFields[0] = ActivityCompat.requireViewById(this, R.id.lblName);
        lblFields[1] = ActivityCompat.requireViewById(this, R.id.lblEmail);
        lblFields[2] = ActivityCompat.requireViewById(this, R.id.lblPhonenumber);
        lblFields[3] = ActivityCompat.requireViewById(this, R.id.lblAddress);
        lblFields[4] = ActivityCompat.requireViewById(this, R.id.lblWeb);


        Database database = Database.getInstance();

        imgAvatar.setTag(database.getDefaultAvatar().getImageResId());
        imgAvatar.setOnClickListener(v -> changeAvatarImg(database));

        for (EditText txt : txtFields) {
            txt.addTextChangedListener(new CustomTextWatcher(txt,this, errorMsg));

            txt.setOnFocusChangeListener(this::changeLblStyle);
        }
    }


    private void changeLblStyle(View v, boolean hasFocus) {

        if (hasFocus) {
            for (int i = 0; i < txtFields.length; i++) {
                if (txtFields[i].getId() == v.getId()) {
                    lblFields[i].setTypeface(Typeface.DEFAULT_BOLD);
                }
            }
        } else {
            for (int i = 0; i < txtFields.length; i++) {
                if (txtFields[i].getId() == v.getId()) {
                    lblFields[i].setTypeface(Typeface.DEFAULT);
                }
            }
        }
    }

    private void changeAvatarImg(Database database) {
        Avatar idNewAvatar = database.getRandomAvatar();
        imgAvatar.setTag(idNewAvatar.getImageResId());
        imgAvatar.setImageResource(idNewAvatar.getImageResId());
        lblAvatar.setText(idNewAvatar.getName());

    }

    // DO NOT TOUCH
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // DO NOT TOUCH
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Checks if form is valid or not and shows a Snackbar accordingly
     **/
    private void save() {
        // TODO

    }

    private void checkNameAddressError(EditText txt,ImageView imgTxt,TextView lbl){
        if(txt.getText().toString().isEmpty()){
            txt.setError(errorMsg);
            imgTxt.setEnabled(false);
            lbl.setEnabled(false);
        }else{
            txt.setError(null);
            imgTxt.setEnabled(true);
            lbl.setEnabled(true);
        }
    }

}
