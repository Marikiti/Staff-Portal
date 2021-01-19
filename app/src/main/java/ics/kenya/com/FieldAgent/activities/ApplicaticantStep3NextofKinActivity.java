package ics.kenya.com.FieldAgent.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.FieldAgent.Bus.ProcessLoanBus;
import ics.kenya.com.R;
import ics.kenya.com.db.MarikitiDataBaseAdapter;

public class ApplicaticantStep3NextofKinActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;
    EditText name, phoneno, idno;
    String names, phonenumber, idnumber, county, constitu, wardd;
    private AppCompatButton appCompatButtonLogin, btnsave;
    MarikitiDataBaseAdapter db;
    ImageView imageadd;
    String applicant_id;
    Spinner scounty, sconstituency;
    EditText ward, streetname, streetno, hseno, adres;
    String strstreetnmae, strstreetno, strhsno, stradres;

    int numberofNextOfkin = 2;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_of_kin_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        db = new MarikitiDataBaseAdapter(getApplicationContext());
        imgdepo = findViewById(R.id.imgdepo);
        imageadd = findViewById(R.id.imageadddata);


        name = findViewById(R.id.etnames);
        phoneno = findViewById(R.id.etphoneno);
        idno = findViewById(R.id.etIdno);
        scounty = findViewById(R.id.sscounty);
        sconstituency = findViewById(R.id.ssconstituency);
        ward = findViewById(R.id.etward);
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
        appCompatButtonLogin = findViewById(R.id.appCompatButtonEnter);
        appCompatButtonLogin.setBackgroundColor(getColor(R.color.primary_dark));
        appCompatButtonLogin.setText(numberofNextOfkin + "More Required");
        btnsave = findViewById(R.id.appCompatButtonSubmit);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            applicant_id = extras.getString("applicant_id");


        }
        appCompatButtonLogin.setFocusable(false);
        appCompatButtonLogin.setClickable(false);
        appCompatButtonLogin.setEnabled(false);

        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextofkin();


            }
        });

        imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new refreshing().execute();
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addkin();


            }
        });


    }//end onCreate

    @SuppressLint("NewApi")
    private void addkin() {
        names = Objects.requireNonNull(name.getText()).toString().trim();
        phonenumber = Objects.requireNonNull(phoneno.getText()).toString().trim();
        idnumber = Objects.requireNonNull(idno.getText()).toString().trim();
        county = Objects.requireNonNull(scounty.getSelectedItem()).toString().trim();
        constitu = Objects.requireNonNull(sconstituency.getSelectedItem()).toString().trim();
        wardd = Objects.requireNonNull(ward.getText()).toString().trim();
        strstreetnmae = Objects.requireNonNull(streetname.getText()).toString().trim();
        strstreetno = Objects.requireNonNull(streetno.getText()).toString().trim();
        strhsno = Objects.requireNonNull(hseno.getText()).toString().trim();
        stradres = Objects.requireNonNull(adres.getText()).toString().trim();

        ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();
        if (TextUtils.isEmpty(names)) {
            name.setError("Full Name Required");
            return;
        }
        if (TextUtils.isEmpty(phonenumber) || phonenumber.length() < 10) {
            phoneno.setError("Enter Valid Phone Number");
            return;
        }
        if (TextUtils.isEmpty(idnumber)) {
            idno.setError("Enter ID Number");
            return;
        }
        if (TextUtils.isEmpty(wardd)) {
            ward.setError("Enter Ward");
            return;
        }
        if ("-SELECT COUNTY-".equals(county)) {
            TextView errorText = (TextView) scounty.getSelectedView();
            if (errorText != null) {
                errorText.setError("");
                errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                errorText.setText("Required");
            }
            return;
        }
        if ("-SELECT CONSTITUENCY-".equals(constitu)) {
            TextView errorText = (TextView) sconstituency.getSelectedView();
            if (errorText != null) {
                errorText.setError("");
                errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                errorText.setText("Required");
            }
            return;
        }
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
        } else if (numberofNextOfkin != 1) {
            //unregistered user, display unregistered user msg and decrease login counter
            numberofNextOfkin = numberofNextOfkin - 1;

            appCompatButtonLogin.setEnabled(false);
            appCompatButtonLogin.setFocusable(false);
            Toast.makeText(getApplicationContext(), "Add " + numberofNextOfkin + " More Next Of Kin to Proceed!", Toast.LENGTH_LONG).show();

            long appl_id = db.processloan(processLoanBus.getName(),
                    processLoanBus.getShopname(),
                    processLoanBus.getStaffno(),
                    processLoanBus.getDate(),
                    processLoanBus.getPurposeOfLoans(),
                    processLoanBus.getDurationincurrLocation(),
                    processLoanBus.getYearsinbussiness(),
                    processLoanBus.getLevelofEducation());

            db.insertNextofkin(Integer.parseInt(String.valueOf(appl_id)),
                    processLoanBus.getStatus(),
                    processLoanBus.getNames(),
                    processLoanBus.getPhonenumber(),
                    processLoanBus.getEmployement(),
                    processLoanBus.getEmployername());
        } else {
            appCompatButtonLogin.setEnabled(true);
            appCompatButtonLogin.setFocusable(true);
            appCompatButtonLogin.setClickable(true);
            appCompatButtonLogin.setBackgroundColor(getColor(R.color.yello));
            appCompatButtonLogin.setText("NEXT");
            Toast.makeText(getApplicationContext(), "You Have Reached Maximum Number Required! You can Proceed Now!", Toast.LENGTH_SHORT).show();
        }

        clearfields();
        //new addnextofkin().execute();

        System.out.println("applicant_id is = " + applicant_id);


    }

    private void clearfields() {
        name.setText(null);
        phoneno.setText(null);
        idno.setText(null);
        ward.setText(null);
        streetname.setText(null);
        streetno.setText(null);
        hseno.setText(null);
        adres.setText(null);

    }

    private void nextofkin() {
        names = Objects.requireNonNull(name.getText()).toString().trim();
        phonenumber = Objects.requireNonNull(phoneno.getText()).toString().trim();
        idnumber = Objects.requireNonNull(idno.getText()).toString().trim();
        county = Objects.requireNonNull(scounty.getSelectedItem()).toString().trim();
        constitu = Objects.requireNonNull(sconstituency.getSelectedItem()).toString().trim();
        wardd = Objects.requireNonNull(ward.getText()).toString().trim();
        strstreetnmae = Objects.requireNonNull(streetname.getText()).toString().trim();
        strstreetno = Objects.requireNonNull(streetno.getText()).toString().trim();
        strhsno = Objects.requireNonNull(hseno.getText()).toString().trim();
        stradres = Objects.requireNonNull(adres.getText()).toString().trim();

        if (TextUtils.isEmpty(names)) {
            name.setError("Full Name Required");
            return;
        }
        if (TextUtils.isEmpty(phonenumber) || phonenumber.length() < 10) {
            phoneno.setError("Enter Valid Phone Number");
            return;
        }
        if (TextUtils.isEmpty(idnumber)) {
            idno.setError("Enter ID Number");
            return;
        }
        if (TextUtils.isEmpty(wardd)) {
            ward.setError("Enter Ward");
            return;
        }
        if ("-SELECT COUNTY-".equals(county)) {
            TextView errorText = (TextView) scounty.getSelectedView();
            if (errorText != null) {
                errorText.setError("");
                errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                errorText.setText("Required");
            }
            return;
        }
        if ("-SELECT CONSTITUENCY-".equals(constitu)) {
            TextView errorText = (TextView) sconstituency.getSelectedView();
            if (errorText != null) {
                errorText.setError("");
                errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                errorText.setText("Required");
            }
            return;
        }
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
            processLoanBus.setNxtkinnames(names);
            processLoanBus.setNxtkinphonenumber(phonenumber);
            processLoanBus.setNxtkinemployername(idnumber);
            new proceedbtn().execute();
        }

    }

    public void validate() {
        if (isValidMobile(phonenumber)) {
            new proceedbtn().execute();
        }
    }

    public boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[^(?:+254|\\+254|0)?(7(?:(?:[129][0-9])|(?:0[0-8])|(4[0-1]))[0-9]{6})$]+", phone)) {
            if (phone.length() < 13 || phone.length() > 13) {
                check = false;
                Toast.makeText(ApplicaticantStep3NextofKinActivity.this, "+2547... Phone Number Required", Toast.LENGTH_SHORT).show();
            } else {
                check = android.util.Patterns.PHONE.matcher(phone).matches();
            }
        } else {
            check = false;
        }
        return check;
    }

    public class proceedbtn extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep3NextofKinActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Proceed!!!");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            //nextofkin();
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
                    Intent intent = new Intent(getApplicationContext(), ApplicaticantStep4RefereeActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    public class refreshing extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep3NextofKinActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Add another Next Kin!");
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
                    name.setText(null);
                    phoneno.setText(null);
                    idno.setText(null);
                }
            });
        }
    }

    public class addnextofkin extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep3NextofKinActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Saving Next Of Kin!!! /t" + names);
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            //addkin();
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

