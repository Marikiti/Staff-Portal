package ics.kenya.com.FieldAgent.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.activities.FileFieldAgentReportActivity;
import ics.kenya.com.RegionalManager.activities.TransferFieldAgentActivity;

public class FieldAgentProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    @BindView(R.id.cardViewviewtraders)
    CardView cardViewviewtraders;
    @BindView(R.id.cardViewViewFileagentreport)
    CardView cardViewViewFileagentreport;
    @BindView(R.id.cardViewTransferagent)
    CardView cardViewTransferagent;
    @BindView(R.id.cardviewcsuspendaccount)
    CardView cardviewcsuspendaccount;


    AppCompatButton buttonEXIT;
    TextView username,Staffno,txtcounty,txtconstituency,txtward,ettotalregionalsales,etregionalsalestarget,etnooftraders;
    private AppCompatButton appCompatButtonLogin;
    public String uname, uphoneno,ustaffno,county,constituency,ward, totalsales, salestarget,totalnooftraders;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgdepo = findViewById(R.id.imgdepo);
        username = findViewById(R.id.username);
        Staffno = findViewById(R.id.StaffNo);
        txtcounty = findViewById(R.id.txtcounty);
        txtconstituency = findViewById(R.id.txtconstituency);
        txtward = findViewById(R.id.txtward);
        ettotalregionalsales = findViewById(R.id.ettotalregionalsales);
        etregionalsalestarget = findViewById(R.id.etregionalsalestarget);
        etnooftraders = findViewById(R.id.etnooftraders);

        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonEXIT = findViewById(R.id.appCompatButtonEXIT);
        buttonEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            uname = extras.getString("traderusername");
            ustaffno = extras.getString("staffnumber");
            county = extras.getString("county");
            constituency = extras.getString("constituency");
            ward = extras.getString("ward");
            totalsales = extras.getString("totalsales");
            salestarget = extras.getString("salestarget");
            totalnooftraders = extras.getString("totalnooftraders");
        }
        username.setText(uname);
        Staffno.setText(ustaffno);
        txtcounty.setText(county);
        txtconstituency.setText(constituency);
        txtward.setText(ward);
        ettotalregionalsales.setText("Total Number Of Sales:ksh "+totalsales);
        etregionalsalestarget.setText("Sales Target: ksh "+salestarget);
        etnooftraders.setText("Total Number Of traders:"+totalnooftraders);


        appCompatButtonLogin = findViewById(R.id.appCompatButtonEXIT);

        cardViewviewtraders.setOnClickListener(v -> {
            new navigateToTradersDetails().execute();

        });

        cardViewViewFileagentreport.setOnClickListener(v -> {
            new navigateToFilreport().execute();

        });
        cardViewTransferagent.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TransferFieldAgentActivity.class);
            startActivity(intent);

        });
        cardviewcsuspendaccount.setOnClickListener(v -> {
            Toast.makeText(this, "Unavailable Now", Toast.LENGTH_SHORT).show();
        });

        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }//end onCreate

    public class navigateToTradersDetails extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(FieldAgentProfileActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Fetching...");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Void result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    // Start your app main activity
                    mprogress.dismiss();
                    Intent intent = new Intent(getApplicationContext(), TraderPortfolioDetailsActivity.class);
                    intent.putExtra("traderusername", uname);
                    intent.putExtra("staffnumber", ustaffno);
                    startActivity(intent);


                }
            });
        }
    }
    public class navigateToFilreport extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(FieldAgentProfileActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Fetching...");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Void result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    // Start your app main activity
                    mprogress.dismiss();
                    Intent intent = new Intent(getApplicationContext(), FileFieldAgentReportActivity.class);
                    intent.putExtra("traderusername", uname);
                    intent.putExtra("staffnumber", ustaffno);
                    intent.putExtra("county", county);
                    intent.putExtra("constituency", constituency);
                    intent.putExtra("ward", ward);
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