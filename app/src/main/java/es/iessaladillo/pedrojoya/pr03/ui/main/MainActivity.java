package es.iessaladillo.pedrojoya.pr03.ui.main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr03.R;
import es.iessaladillo.pedrojoya.pr03.business.CustomTextWatcher;
import es.iessaladillo.pedrojoya.pr03.data.local.Database;
import es.iessaladillo.pedrojoya.pr03.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr03.utils.ValidationUtils;

public class MainActivity extends AppCompatActivity {

    private ImageView imgAvatar;
    private TextView lblAvatar;
    private final int EDITTEXTQUANTITY = 5;
    private EditText txtFields[] = new EditText[EDITTEXTQUANTITY];
    private TextView lblFields[] = new TextView[EDITTEXTQUANTITY];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        // TODO
    }

    private void initViews() {
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
            txt.addTextChangedListener(new CustomTextWatcher(txt,this));

            txt.setOnFocusChangeListener(this::changeLblStyle);
        }
    }

    private void validate(String textWritten, boolean validateAll) {
        if(!ValidationUtils.isValidPhone(textWritten)){

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

}
