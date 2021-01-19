package ics.kenya.com.FieldAgent.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.FieldAgent.Bus.ProcessLoanBus;
import ics.kenya.com.R;
import ics.kenya.com.db.MarikitiDataBaseAdapter;

public class ApplicaticantStep1LoanProcessingActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    TextView username, shopname, staffnumber;
    String uname, ushopname, staffno, localid;

    private AppCompatButton appCompatButtonLogin;
    String purposeofloan, durationtme, yearsinbusness, educationlvl;
    Spinner educationlevel;
    EditText loanpurpose, duration, yearsinbus;
    MarikitiDataBaseAdapter db;

    SimpleDateFormat input = new SimpleDateFormat("yyyy MM dd");

    TextView txtdate;
    private WebView webView;
    String date;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_loan);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = new MarikitiDataBaseAdapter(getApplicationContext());



        imgdepo = findViewById(R.id.imgdepo);
        username = findViewById(R.id.username);
        shopname = findViewById(R.id.shopname);
        staffnumber = findViewById(R.id.staffnumber);

        loanpurpose = findViewById(R.id.etloanpurpose);
        duration = findViewById(R.id.etduration);
        yearsinbus = findViewById(R.id.etyearsinbusness);
        educationlevel = findViewById(R.id.sseducationlevel);

        txtdate = findViewById(R.id.txtviewdate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
        String currentDateandTime = sdf.format(new Date());
        txtdate.setText(currentDateandTime);

        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            uname = extras.getString("traderusername");
            staffno = extras.getString("staffnumber");
            ushopname = extras.getString("shopname");

        }

        username.setText(uname);
        staffnumber.setText(staffno);
        shopname.setText(ushopname);

        appCompatButtonLogin = findViewById(R.id.appCompatButtonEnter);

        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedatatobus();

            }
        });

    }//end onCreate

    private void savedatatobus() {
        date = Objects.requireNonNull(txtdate.getText()).toString().trim();
        purposeofloan = Objects.requireNonNull(loanpurpose.getText()).toString().trim();
        durationtme = Objects.requireNonNull(duration.getText()).toString().trim();
        yearsinbusness = Objects.requireNonNull(yearsinbus.getText()).toString().trim();
        educationlvl = Objects.requireNonNull(educationlevel.getSelectedItem()).toString().trim();

        if (TextUtils.isEmpty(purposeofloan)) {
            loanpurpose.setError("Purpose Required");
            return;
        }
        if (TextUtils.isEmpty(durationtme)) {
            duration.setError("Duration Required");
            return;
        }

        if (TextUtils.isEmpty(yearsinbusness)) {
            yearsinbus.setError("Years Required");
            return;
        }

        if ("-SELECT EDUCATION LEVEL-".equals(educationlvl)) {
            TextView errorText = (TextView) educationlevel.getSelectedView();
            if (errorText != null) {
                errorText.setError("");
                errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                errorText.setText("Education Level Required");
            }
            return;
        } else {
            ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();
            processLoanBus.setName(uname);
            processLoanBus.setStaffno(staffno);
            processLoanBus.setShopname(ushopname);
            processLoanBus.setDate(date);
            processLoanBus.setPurposeOfLoans(purposeofloan);
            processLoanBus.setDurationincurrLocation(durationtme);
            processLoanBus.setYearsinbussiness(yearsinbusness);
            processLoanBus.setLevelofEducation(educationlvl);

            new proceedbtn().execute();
        }
       /* applicant_id = String.valueOf(db.processloan(uname,
                staffno,
                ushopname,
                purposeofloan,
                durationtme,
                yearsinbusness,
                educationlvl));

        local_id = applicant_id;

        System.out.println("applicant_id is==" + local_id);*/
    }

    public class proceedbtn extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep1LoanProcessingActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Proceed !!!");
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
                    Intent intent = new Intent(getApplicationContext(), ApplicaticantStep2MaritalStatusActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
}