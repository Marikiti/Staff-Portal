package ics.kenya.com.RegionalManager.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

public class TraderProfilePortfolioActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;


    @BindView(R.id.cardViewViewreadreport)
    CardView cardViewViewreadreport;

    AppCompatButton buttonext, ButtonEXIT;
    TextView username, staffno, county, constituency, ward;

    public String uname, uphoneno, ustaffno, countyname, constituencyname, wardname, totalsales, salestarget, totalnooftraders;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_portfolio);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgdepo = findViewById(R.id.imgdepo);
        buttonext = findViewById(R.id.appCompatButtonEXIT);
        username = findViewById(R.id.username);
        staffno = findViewById(R.id.StaffNo);
        county = findViewById(R.id.txtcounty);
        constituency = findViewById(R.id.txtconstituency);
        ward = findViewById(R.id.txtward);

        ButtonEXIT = findViewById(R.id.appCompatButtonEXIT);


        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ButtonEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TraderProfilePortfolioActivity.this, FieldAgentUserProfilePortfolioActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            uname = extras.getString("traderusername");
            ustaffno = extras.getString("staffnumber");
            countyname = extras.getString("county");
            constituencyname = extras.getString("constituency");
            wardname = extras.getString("ward");

        }
        username.setText(uname);
        staffno.setText(ustaffno);
        county.setText(countyname);
        constituency.setText(constituencyname);
        ward.setText(wardname);


        cardViewViewreadreport.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ReadReportActivity.class);
            startActivity(intent);

        });

    }//end onCreate

    public class navigateToTradersDetails extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(TraderProfilePortfolioActivity.this);

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
                    Intent intent = new Intent(getApplicationContext(), ViewRegisteredTradersUnderFieldAgentPortfolioActivity.class);
                    intent.putExtra("traderusername", uname);
                    intent.putExtra("staffnumber", ustaffno);
                    startActivity(intent);


                }
            });
        }
    }

    public class navigateToFilreport extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(TraderProfilePortfolioActivity.this);

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
                    intent.putExtra("county", countyname);
                    intent.putExtra("constituency", constituencyname);
                    intent.putExtra("ward", wardname);
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

