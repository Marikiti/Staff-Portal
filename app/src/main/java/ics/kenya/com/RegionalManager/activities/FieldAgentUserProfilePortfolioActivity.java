package ics.kenya.com.RegionalManager.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.activities.ViewList.ViewRegisteredTradersUnderFieldAgentPortfolioActivity;
import ics.kenya.com.RegionalManager.common.RegionalManagerDashboardActivity;
import ics.kenya.com.db.MarikitiDataBaseAdapter;

public class FieldAgentUserProfilePortfolioActivity extends AppCompatActivity {

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

    MarikitiDataBaseAdapter db;

    String statusoption;
    int localID;

    AppCompatButton buttonEXIT;
    EditText nooftraders;

    TextView username, Staffno, txtcounty, txtconstituency, txtward, ettotalregionalsales, etregionalsalestarget, etnooftraders;
    private AppCompatButton appCompatButtonLogin;
    public String uname, uphoneno, ustaffno, county, constituency, ward, totalsales, salestarget, totalnooftraders;

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

        db = new MarikitiDataBaseAdapter(getApplicationContext());


        imgdepo = findViewById(R.id.imgdepo);
        username = findViewById(R.id.username);
        Staffno = findViewById(R.id.StaffNo);
        txtcounty = findViewById(R.id.txtcounty);
        txtconstituency = findViewById(R.id.txtconstituency);
        txtward = findViewById(R.id.txtward);
        ettotalregionalsales = findViewById(R.id.ettotalregionalsales);
        etregionalsalestarget = findViewById(R.id.etregionalsalestarget);
        etnooftraders = findViewById(R.id.etnooftraders);

        nooftraders = findViewById(R.id.etnooftraders);
        getTraderCount();


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
        }
        username.setText(uname);
        Staffno.setText(ustaffno);
        txtcounty.setText(county);
        txtconstituency.setText(constituency);
        txtward.setText(ward);
        ettotalregionalsales.setText("Total Number Of Sales:ksh " + totalsales);
        etregionalsalestarget.setText("Sales Target: ksh " + salestarget);


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
            showpinDialog();
        });

        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }//end onCreate

    private void getTraderCount() {
        int totalnumberoftraders = db.getTraderCount();
        nooftraders.setText("Total Number of Registered Traders : " + totalnumberoftraders);
    }

    public class navigateToTradersDetails extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(FieldAgentUserProfilePortfolioActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Fetching User Information...");
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
                    Intent intent = new Intent(getApplicationContext(), ViewRegisteredTradersUnderFieldAgentPortfolioActivity.class);
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

    public void showpinDialog() {
        // TODO Auto-generated method stub
        final Dialog dialog = new Dialog(FieldAgentUserProfilePortfolioActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.row_spinner);
        dialog.setCancelable(true);

        // set the custom dialog components - text, image and button
        final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner1);
        final EditText edittext = (EditText) dialog.findViewById(R.id.editText1);
        Button button = (Button) dialog.findViewById(R.id.button1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItem().equals("ACTIVATE")) {
                    statusoption = "ACTIVE";
                }
                if (spinner.getSelectedItem().equals("DEACTIVATE")) {
                    statusoption = "INACTIVE";
                }
                if (spinner.getSelectedItem().equals("SUSPEND")) {
                    statusoption = "SUSPENDED";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                new submitdata().execute();
            }
        });
        dialog.show();

    }

    public class submitdata extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(FieldAgentUserProfilePortfolioActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Processing...please wait!");
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
                   // UpdateTraderDetails();
                    Intent intent = new Intent(getApplicationContext(), RegionalManagerDashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
    public void UpdateTraderDetails() {

        db.updateRegisterTrader(localID, statusoption);

    }


    public class navigateToFilreport extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(FieldAgentUserProfilePortfolioActivity.this);

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

