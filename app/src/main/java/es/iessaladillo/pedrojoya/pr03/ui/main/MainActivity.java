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
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr03.R;
import es.iessaladillo.pedrojoya.pr03.business.CustomErrorChecker;
import es.iessaladillo.pedrojoya.pr03.data.local.Database;
import es.iessaladillo.pedrojoya.pr03.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr03.utils.SnackBarUtils;

public class MainActivity extends AppCompatActivity {

    private ImageView imgAvatar;
    private TextView lblAvatar;
    private final int EDITTEXTQUANTITY = 5;
    private final int IMAGEQUANTITY = 4;
    private final EditText[] txtFields = new EditText[EDITTEXTQUANTITY];
    private final TextView[] lblFields = new TextView[EDITTEXTQUANTITY];
    private final ImageView[] imgFields = new ImageView[IMAGEQUANTITY];

    private String errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        // TODO
    }


    private void initViews() {
        //Initializations
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

        imgFields[0] = ActivityCompat.requireViewById(this, R.id.imgEmail);
        imgFields[1] = ActivityCompat.requireViewById(this, R.id.imgPhonenumber);
        imgFields[2] = ActivityCompat.requireViewById(this, R.id.imgAddress);
        imgFields[3] = ActivityCompat.requireViewById(this, R.id.imgWeb);


        Database database = Database.getInstance();
        imgAvatar.setTag(database.getDefaultAvatar().getImageResId());

        //Listeners
        imgAvatar.setOnClickListener(v -> changeAvatarImg(database));

        txtFields[4].setOnEditorActionListener((v, actionId, event) -> {
                save();
                return false;
        });


        for (EditText txt : txtFields) {
            txt.setOnFocusChangeListener(this::changeLblStyle);
        }

        //These listeners are split due to them not working on a separate class
        txtFields[0].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CustomErrorChecker.checkNameError(txtFields[0], lblFields[0], errorMsg);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtFields[1].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CustomErrorChecker.checkEmailError(txtFields[1], imgFields[0], lblFields[1], errorMsg);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtFields[2].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CustomErrorChecker.checkPhonenumberError(txtFields[2], imgFields[1], lblFields[2], errorMsg);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtFields[3].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CustomErrorChecker.checkAddressError(txtFields[3], imgFields[2], lblFields[3], errorMsg);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtFields[4].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CustomErrorChecker.checkWebError(txtFields[4], imgFields[3], lblFields[4], errorMsg);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

    //This method changes the font style of the TextViews associated to the EditTexts, from default to bold and vice versa.
    //It depends on its current state.
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

    //It changes, including the picture and the name, the current avatar for one randomly selected from the database.
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

    //Save checks all EditText in the activity, if these fufill the patterns required, the user is shown a snackbar
    //showing a success message, if not, the views that fail the requirements are show an error and a snakbar is also shown
    //with a message of failure
    private void save() {
        // TODO
        if (validateAll()) {
            SnackBarUtils.showSnackBar(lblFields[0], getString(R.string.main_saved_succesfully));
        } else {
            SnackBarUtils.showSnackBar(lblFields[0], getString(R.string.main_error_saving));
        }


    }

    //It checks if all the edittexts pass the requirements and shows errors if thy do not
    private boolean validateAll() {
        CustomErrorChecker.checkNameError(txtFields[0], lblFields[0], errorMsg);
        CustomErrorChecker.checkEmailError(txtFields[1], imgFields[0], lblFields[1], errorMsg);
        CustomErrorChecker.checkPhonenumberError(txtFields[2], imgFields[1], lblFields[2], errorMsg);
        CustomErrorChecker.checkAddressError(txtFields[3], imgFields[2], lblFields[3], errorMsg);
        CustomErrorChecker.checkWebError(txtFields[4], imgFields[3], lblFields[4], errorMsg);

        for (int i = 0; i < lblFields[0].length(); i++) {
            if (!lblFields[i].isEnabled()) {
                return false;
            }
        }
        return true;
    }


}
