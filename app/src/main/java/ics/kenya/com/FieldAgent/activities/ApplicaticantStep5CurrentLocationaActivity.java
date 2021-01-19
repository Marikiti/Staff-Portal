package ics.kenya.com.FieldAgent.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.FieldAgent.Bus.ProcessLoanBus;
import ics.kenya.com.R;
import ics.kenya.com.db.MarikitiDataBaseAdapter;

public class ApplicaticantStep5CurrentLocationaActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;
    private AppCompatButton appCompatButtonNxt;


    EditText streetname, streetno, hseno, adres;
    String strstreetnmae, strstreetno, strhsno, stradres;
    MarikitiDataBaseAdapter db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_location_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        db = new MarikitiDataBaseAdapter(getApplicationContext());

        imgdepo = findViewById(R.id.imgdepo);
        streetname = findViewById(R.id.etstreetname);
        streetno = findViewById(R.id.etstreetno);
        hseno = findViewById(R.id.ethouseno);
        adres = findViewById(R.id.etadrress);
        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        appCompatButtonNxt = findViewById(R.id.appCompatButtonNext);


        appCompatButtonNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strstreetnmae = Objects.requireNonNull(streetname.getText()).toString().trim();
                strstreetno = Objects.requireNonNull(streetno.getText()).toString().trim();
                strhsno = Objects.requireNonNull(hseno.getText()).toString().trim();
                stradres = Objects.requireNonNull(adres.getText()).toString().trim();

                if (TextUtils.isEmpty(strstreetnmae)) {
                    streetname.setError("Street Name Required");
                    return;
                }
                if (TextUtils.isEmpty(strstreetno)) {
                    streetno.setError("Street No Required");
                    return;
                }
                if (TextUtils.isEmpty(strhsno)) {
                    hseno.setError("House No Required");
                    return;
                }
                if (TextUtils.isEmpty(stradres)) {
                    adres.setError("Address Required");
                    return;
                } else {
                    ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();
                    processLoanBus.setStreetname(strstreetnmae);
                    processLoanBus.setStreetno(strstreetno);
                    processLoanBus.setHsno(strhsno);
                    processLoanBus.setAdrres(stradres);
                    new proceed().execute();
                }
            }
        });

    }//end onCreate


    public class proceed extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep5CurrentLocationaActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Proceed...!");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    mprogress.dismiss();
                    Intent intent = new Intent(getApplicationContext(), ApplicaticantStep6PermanentHomeAddressActivity.class);
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


