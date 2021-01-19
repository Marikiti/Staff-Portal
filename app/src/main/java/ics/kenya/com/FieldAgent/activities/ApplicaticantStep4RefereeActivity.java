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

public class ApplicaticantStep4RefereeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    Spinner ssetemployement;
    EditText name, phoneno, employeemnt, idno;
    String names, phonenumber, empl, idnumber;
    private AppCompatButton appCompatButtonLogin, btnsave;
    MarikitiDataBaseAdapter db;
    ImageView imageadd;
    int numberofNextOfkin = 2;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.referee_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = new MarikitiDataBaseAdapter(getApplicationContext());

        imgdepo = findViewById(R.id.imgdepo);
        name = findViewById(R.id.etnames);
        phoneno = findViewById(R.id.etphoneno);
        ssetemployement = findViewById(R.id.ssetemployement);
        idno = findViewById(R.id.etIdno);
        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appCompatButtonLogin = findViewById(R.id.appCompatButtonEnter);

        appCompatButtonLogin = findViewById(R.id.appCompatButtonEnter);
        appCompatButtonLogin.setBackgroundColor(getColor(R.color.primary_dark));
        appCompatButtonLogin.setText(numberofNextOfkin + "More Required");
        btnsave = findViewById(R.id.appCompatButtonSubmit);
        imageadd = findViewById(R.id.imageadddata);


        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedatatobus();

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
                addreferee();
            }
        });

    }//end onCreate


    private void savedatatobus() {
        names = Objects.requireNonNull(name.getText()).toString().trim();
        phonenumber = Objects.requireNonNull(phoneno.getText()).toString().trim();
        empl = Objects.requireNonNull(ssetemployement.getSelectedItem()).toString().trim();
        idnumber = Objects.requireNonNull(idno.getText()).toString().trim();


        if (TextUtils.isEmpty(names)) {
            name.setError("Full Name Required");
            return;
        }
        if (TextUtils.isEmpty(phonenumber) || phonenumber.length() < 10) {
            phoneno.setError("Enter Valid Phone Number");
            return;
        }
        if ("-SELECT STATUS-".equals(empl)) {
            TextView errorText = (TextView) ssetemployement.getSelectedView();
            if (errorText != null) {
                errorText.setError("");
                errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                errorText.setText("Required");
            }
            return;
        } else {
            ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();
            processLoanBus.setRefereenames(names);
            processLoanBus.setRefereephonenumber(phonenumber);
            processLoanBus.setRefereeemployement(empl);
            processLoanBus.setRefereeemployername(idnumber);

            new proceedbtn().execute();
        }

    }

    @SuppressLint("NewApi")
    private void addreferee() {
        names = Objects.requireNonNull(name.getText()).toString().trim();
        phonenumber = Objects.requireNonNull(phoneno.getText()).toString().trim();
        empl = Objects.requireNonNull(employeemnt.getText()).toString().trim();
        idnumber = Objects.requireNonNull(idno.getText()).toString().trim();
        if (TextUtils.isEmpty(names)) {
            name.setError("Full Name Required");
            return;
        }
        if (TextUtils.isEmpty(phonenumber) || phonenumber.length() < 10) {
            phoneno.setError("Enter Valid Phone Number");
            return;
        }
        if ("-SELECT STATUS-".equals(empl)) {
            TextView errorText = (TextView) ssetemployement.getSelectedView();
            if (errorText != null) {
                errorText.setError("");
                errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                errorText.setText("Required");
            }
            return;
        }
        else if (numberofNextOfkin != 1) {
            //unregistered user, display unregistered user msg and decrease login counter
            numberofNextOfkin = numberofNextOfkin - 1;

            appCompatButtonLogin.setEnabled(false);
            appCompatButtonLogin.setFocusable(false);
            Toast.makeText(getApplicationContext(), "Add " + numberofNextOfkin + " More Next Of Kin to Proceed!", Toast.LENGTH_LONG).show();
          /*ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();
            long appl_id = db.processloan(processLoanBus.getName(),
                    processLoanBus.getShopname(),
                    processLoanBus.getStaffno(),
                    processLoanBus.getDate(),
                    processLoanBus.getPurposeOfLoans(),
                    processLoanBus.getDurationincurrLocation(),
                    processLoanBus.getYearsinbussiness(),
                    processLoanBus.getLevelofEducation());

            db.insertReferee(Integer.parseInt(String.valueOf(appl_id)),
                    processLoanBus.getRefereestatus(),
                    processLoanBus.getRefereenames(),
                    processLoanBus.getRefereephonenumber(),
                    processLoanBus.getRefereeemployement(),
                    processLoanBus.getRefereeemployername());*/
        } else {
            appCompatButtonLogin.setEnabled(true);
            appCompatButtonLogin.setFocusable(true);
            appCompatButtonLogin.setClickable(true);
            appCompatButtonLogin.setBackgroundColor(getColor(R.color.yello));
            appCompatButtonLogin.setText("NEXT");
            Toast.makeText(getApplicationContext(), "You Have Reached Maximum Number Required! You can Proceed Now!", Toast.LENGTH_SHORT).show();
        }


       // new addReferee().execute();
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
                Toast.makeText(ApplicaticantStep4RefereeActivity.this, "+2547... Phone Number Required", Toast.LENGTH_SHORT).show();
            } else {
                check = android.util.Patterns.PHONE.matcher(phone).matches();
            }
        } else {
            check = false;
        }
        return check;
    }


    public class proceedbtn extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep4RefereeActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setTitle("Loading...");
            mprogress.setMessage("Proceed!!!");
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
                    Intent intent = new Intent(getApplicationContext(), ApplicaticantStep5CurrentLocationaActivity.class);
                    startActivity(intent);
                }
            });
        }
    }


    public class refreshing extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep4RefereeActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Add another Referee!");
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
                    employeemnt.setText(null);
                    idno.setText(null);
                }
            });
        }
    }

    public class addReferee extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep4RefereeActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Saving Referee!");
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

