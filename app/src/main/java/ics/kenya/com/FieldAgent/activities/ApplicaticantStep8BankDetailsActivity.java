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

public class ApplicaticantStep8BankDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    EditText banknam, bankbrach, accountno, yearswthbank, accntname;
    String accountname, bankname, bankbrachname, accountnumber, yearswthbankno;
    private AppCompatButton appCompatButtonLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_details_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgdepo = findViewById(R.id.imgdepo);

        accntname = findViewById(R.id.etaccntname);
        banknam = findViewById(R.id.etbankname);
        bankbrach = findViewById(R.id.etbankbrach);
        accountno = findViewById(R.id.etaccountno);
        yearswthbank = findViewById(R.id.etyeraswthbank);


        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appCompatButtonLogin = findViewById(R.id.appCompatButtonEnter);


        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedatatobus();


            }
        });

    }//end onCreate

    private void savedatatobus() {
        accountname = Objects.requireNonNull(accntname.getText()).toString().trim();
        bankname = Objects.requireNonNull(banknam.getText()).toString().trim();
        bankbrachname = Objects.requireNonNull(bankbrach.getText()).toString().trim();
        accountnumber = Objects.requireNonNull(accountno.getText()).toString().trim();
        yearswthbankno = Objects.requireNonNull(yearswthbank.getText()).toString().trim();
        if (TextUtils.isEmpty(accountname)) {
            accntname.setError("Account Name Required");
            return;
        }
        if (TextUtils.isEmpty(bankname)) {
            banknam.setError("Bank Name Required");
            return;
        }
        if (TextUtils.isEmpty(bankbrachname)) {
            bankbrach.setError("Branch Name Required");
            return;
        }
        if (TextUtils.isEmpty(accountnumber)) {
            accountno.setError("Account Number Required");
            return;
        }
        if (TextUtils.isEmpty(yearswthbankno)) {
            yearswthbank.setError("Years Required");
            return;
        } else {
            ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();
            processLoanBus.setAccountname(accountname);
            processLoanBus.setBankname(bankname);
            processLoanBus.setBankbranch(bankbrachname);
            processLoanBus.setAccountno(accountnumber);
            processLoanBus.setYearswithbank(yearswthbankno);

            new proceedbtn().execute();
        }


    }

    public class proceedbtn extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep8BankDetailsActivity.this);

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
                    Intent intent = new Intent(getApplicationContext(), ApplicaticantStep9OtherLoansActivity.class);
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

