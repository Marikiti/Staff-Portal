package ics.kenya.com.FieldAgent.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class ApplicaticantStep2MaritalStatusActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    Spinner sstatus;
    EditText name, phoneno, idno;
    String status, names, phonenumber, idnum;
    private AppCompatButton appCompatButtonLogin;
    MarikitiDataBaseAdapter db;
    String applicant_id;

    String[] stattusselection = {"-SELECT STATUS-", "Single", "Married", "Other"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marial_status_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        db = new MarikitiDataBaseAdapter(getApplicationContext());
        imgdepo = findViewById(R.id.imgdepo);

        sstatus = findViewById(R.id.ssstatus);
        name = findViewById(R.id.etnames);
        phoneno = findViewById(R.id.etphoneno);
        idno = findViewById(R.id.etidnum);

        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appCompatButtonLogin = findViewById(R.id.appCompatButtonEnter);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            applicant_id = extras.getString("applicant_id");


        }

        sstatus.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stattusselection);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        sstatus.setAdapter(arrayAdapter);


        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sstatus.equals("Married")) {
                    status = Objects.requireNonNull(sstatus.getSelectedItem()).toString().trim();
                    names = Objects.requireNonNull(name.getText()).toString().trim();
                    phonenumber = Objects.requireNonNull(phoneno.getText()).toString().trim();
                    idnum = Objects.requireNonNull(idno.getText()).toString().trim();


                    if ("-SELECT STATUS-".equals(status)) {
                        TextView errorText = (TextView) sstatus.getSelectedView();
                        if (errorText != null) {
                            errorText.setError("");
                            errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            errorText.setText("Status Required");
                        }
                        return;
                    }
                    if (TextUtils.isEmpty(names)) {
                        name.setError("Full Name Required");
                        return;
                    }
                    if (TextUtils.isEmpty(phonenumber) || phonenumber.length() < 10) {
                        phoneno.setError("Enter valid Phone Number");
                        return;
                    }
                    if (TextUtils.isEmpty(idnum)) {
                        idno.setError("Id Number Required");
                        return;
                    }
                } else {
                    ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();
                    processLoanBus.setStatus(status);
                    processLoanBus.setNames(names);
                    processLoanBus.setPhonenumber(phonenumber);
                    processLoanBus.setEmployement(idnum);

                    new proceedbtn().execute();
          /*  db.insertmaritalstatus(
                    processLoanBus.getStatus(),
                    processLoanBus.getNames(),
                    processLoanBus.getPhonenumber(),
                    processLoanBus.getEmployement(),
                    processLoanBus.getEmployername());*/
                    // }

                }
            }
        });

    }//end onCreate

    private void savedatatobus() {


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
                Toast.makeText(ApplicaticantStep2MaritalStatusActivity.this, "+2547... Phone Number Required", Toast.LENGTH_SHORT).show();
            } else {
                check = android.util.Patterns.PHONE.matcher(phone).matches();
            }
        } else {
            check = false;
        }
        return check;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selection = (String) parent.getItemAtPosition(position);
        if (selection.equals("Other")) {
            name.setVisibility(View.GONE);
            phoneno.setVisibility(View.GONE);
            idno.setVisibility(View.GONE);

        }
        if (selection.equals("Single")) {
            name.setVisibility(View.GONE);
            phoneno.setVisibility(View.GONE);
            idno.setVisibility(View.GONE);


        }
        if (selection.equals("Married")) {
            name.setVisibility(View.VISIBLE);
            phoneno.setVisibility(View.VISIBLE);
            idno.setVisibility(View.VISIBLE);

            if (TextUtils.isEmpty(names)) {
                name.setError("Full Name Required");
                return;
            }
            if (TextUtils.isEmpty(phonenumber) || phonenumber.length() < 10) {
                phoneno.setError("Enter valid Phone Number");
                return;
            }
            if (TextUtils.isEmpty(idnum)) {
                idno.setError("Id Number Required");
                return;
            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class proceedbtn extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep2MaritalStatusActivity.this);

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
                    Intent intent = new Intent(getApplicationContext(), ApplicaticantStep3NextofKinActivity.class);
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

