package ics.kenya.com.FieldAgent.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class ApplicaticantStep9OtherLoansActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    EditText company, loanamount, balance;
    String companyname, amountofloan, balanceamount;
    private AppCompatButton appCompatButtonLogin, btnsave;
    MarikitiDataBaseAdapter db;

    ImageView imageadd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_loans_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        db = new MarikitiDataBaseAdapter(getApplicationContext());
        imgdepo = findViewById(R.id.imgdepo);

        company = findViewById(R.id.etcompany);
        loanamount = findViewById(R.id.etloanamount);
        balance = findViewById(R.id.etbalance);
        imageadd = findViewById(R.id.imageadddata);


        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appCompatButtonLogin = findViewById(R.id.appCompatButtonEnter);
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
                addkin();


            }
        });

    }//end onCreate


    private void savedatatobus() {
        companyname = Objects.requireNonNull(company.getText()).toString().trim();
        amountofloan = Objects.requireNonNull(loanamount.getText()).toString().trim();
        balanceamount = Objects.requireNonNull(balance.getText()).toString().trim();

     /*   if (TextUtils.isEmpty(companyname)) {
            company.setError("Company Name Required");
            return;
        }
        if (TextUtils.isEmpty(amountofloan)) {
            loanamount.setError("Amount Required");
            return;
        }
        if (TextUtils.isEmpty(balanceamount)) {
            balance.setError("Amount Required");
            return;
        } else {

        }*/
            ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();
            processLoanBus.setCompany(companyname);
            processLoanBus.setLoanamount(amountofloan);
            processLoanBus.setBalance(balanceamount);
            new proceedbtn().execute();


    }

    private void addkin() {
        companyname = Objects.requireNonNull(company.getText()).toString().trim();
        amountofloan = Objects.requireNonNull(loanamount.getText()).toString().trim();
        balanceamount = Objects.requireNonNull(balance.getText()).toString().trim();

        ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();

        long appl_id = db.processloan(processLoanBus.getName(),
                processLoanBus.getShopname(),
                processLoanBus.getStaffno(),
                processLoanBus.getDate(),
                processLoanBus.getPurposeOfLoans(),
                processLoanBus.getDurationincurrLocation(),
                processLoanBus.getYearsinbussiness(),
                processLoanBus.getLevelofEducation());

        db.insertOtherLoans(Integer.parseInt(String.valueOf(appl_id)),
                processLoanBus.getCompany(),
                processLoanBus.getLoanamount(),
                processLoanBus.getBalance());

        new addotherloans().execute();
    }

    public class refreshing extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep9OtherLoansActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Add Other Loans!");
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
                    company.setText(null);
                    loanamount.setText(null);
                    balance.setText(null);

                }
            });
        }
    }

    public class addotherloans extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep9OtherLoansActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Saving Other Loans!");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // addkin();
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


    public class proceedbtn extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep9OtherLoansActivity.this);

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
                    Intent intent = new Intent(getApplicationContext(), ApplicaticantStep10OtherBusinessActivity.class);
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

