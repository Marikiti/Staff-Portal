package ics.kenya.com.RegionalManager.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.RegionalManager.common.RegionalManagerDashboardActivity;
import ics.kenya.com.R;


public class RegionalManagerLoginActivity extends AppCompatActivity {

    int loginCntr = 3;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;
    @BindView(R.id.etStaffNumber)
    EditText etStaffNumber;
    @BindView(R.id.etIDNumber)
    EditText etIDNumber;
    @BindView(R.id.etPinNumber)
    EditText etPinNumber;
    private AppCompatButton appCompatButtonLogin;

    int PERMISSION_ALL = 1;
    public ProgressDialog mprogress;
    private static int SPLASH_TIME_OUT = 3000;

    String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    String staffno, idno, pin;

    private static final int DATA_SETUP_SERVICE_ID = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regional_login);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        appCompatButtonLogin = findViewById(R.id.appCompatButtonEnter);

        appCompatButtonLogin.setOnClickListener(v -> {
            staffno = Objects.requireNonNull(etStaffNumber.getText()).toString().trim();
            idno = Objects.requireNonNull(etIDNumber.getText()).toString().trim();
            pin = Objects.requireNonNull(etPinNumber.getText()).toString().trim();

            new FetchLogin().execute();
        });
    }

    public class FetchLogin extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(RegionalManagerLoginActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Authenticating...Please wait!!!");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Void result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    mprogress.dismiss();
                    Intent intent = new Intent(RegionalManagerLoginActivity.this, RegionalManagerDashboardActivity.class);
                    startActivity(intent);

                }
            });
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
}
