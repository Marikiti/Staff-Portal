package ics.kenya.com.FieldAgent.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.FieldAgent.common.FieldAgentDashboardActivity;
import ics.kenya.com.R;
import ics.kenya.com.db.MarikitiDataBaseAdapter;
import ics.kenya.com.model.RegisterTrader;

public class RegisterTraderActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, DatePickerDialog.OnCancelListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;
    private AppCompatButton appCompatButtonnext;

    @BindView(R.id.etFullNames)
    EditText etFullNames;
    @BindView(R.id.etidno)
    EditText etidno;
    @BindView(R.id.etdob)
    AppCompatButton btndon;
    @BindView(R.id.etmarikitiuserno)
    EditText etmarikitiuserno;
    @BindView(R.id.etusername)
    EditText etusername;
    @BindView(R.id.etemailaddress)
    EditText etemailaddress;
    @BindView(R.id.etphoneno)
    EditText etphoneno;
    @BindView(R.id.etaddress)
    EditText etaddress;

    MarikitiDataBaseAdapter db;

    String fullnames, idno, dob, marikituserno, uname, emailaddress, phoneno, addres, status = "ACTIVE";

    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_details_activity);
        ButterKnife.bind(this);

        db = new MarikitiDataBaseAdapter(getApplicationContext());
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgdepo = findViewById(R.id.imgdepo);

        btndon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate(1980, 0, 1, R.style.DatePickerSpinner);
            }
        });


        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        appCompatButtonnext = findViewById(R.id.appCompatButtonNext);


        appCompatButtonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitdata();


            }
        });

    }//end onCreate

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        btndon.setText(simpleDateFormat.format(calendar.getTime()));
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        btndon.setText(R.string.cancelled);
    }


    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(RegisterTraderActivity.this)
                .callback(RegisterTraderActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }

    public void submitdata() {
        fullnames = Objects.requireNonNull(etFullNames.getText()).toString().toUpperCase().trim();
        idno = Objects.requireNonNull(etidno.getText()).toString().toUpperCase().trim();
        dob = Objects.requireNonNull(btndon.getText()).toString().trim();
        marikituserno = Objects.requireNonNull(etmarikitiuserno.getText()).toString().trim();
        uname = Objects.requireNonNull(etusername.getText()).toString().toUpperCase().trim();
        emailaddress = Objects.requireNonNull(etemailaddress.getText()).toString().trim();
        phoneno = Objects.requireNonNull(etphoneno.getText()).toString().trim();
        addres = Objects.requireNonNull(etaddress.getText()).toString().trim();

        if (TextUtils.isEmpty(fullnames)) {
            etFullNames.setError("Enter Username");
            return;
        }
        if (TextUtils.isEmpty(idno)) {
            etidno.setError("Enter Id Number");
            return;
        }
        if (TextUtils.isEmpty(dob)) {
            btndon.setError("Enter DOB");
            return;
        }
        if (TextUtils.isEmpty(marikituserno)) {
            etmarikitiuserno.setError("Enter Marikiti User");
            return;
        }
        if (TextUtils.isEmpty(uname)) {
            etusername.setError("Enter username");
            return;
        }
        if (TextUtils.isEmpty(emailaddress)) {
            etemailaddress.setError("Enter Email Address");
            return;
        }
        if (TextUtils.isEmpty(phoneno)) {
            etphoneno.setError("Enter Phone number");
            return;
        }
        if (TextUtils.isEmpty(addres)) {
            etaddress.setError("Enter Address");
            return;
        } else {


            new navigate().execute();
        }
    }

    public void SubmitTraderDetails() {
        RegisterTrader registerTrader = new RegisterTrader(
                0,
                "MKT52020",
                fullnames,
                idno,
                dob,
                marikituserno,
                uname,
                emailaddress,
                phoneno,
                addres,
                "NAIROBI",
                "STAREHE",
                "KAMUKUNJI",
                "200,000",
                ""

        );

        db.registterTrader(registerTrader);

    }


    public class navigate extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(RegisterTraderActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setTitle("MARIKITI TRADER REGISTRATION");
            mprogress.setMessage("Registering Trader....Please Wait!");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            SubmitTraderDetails();
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Void result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    mprogress.dismiss();
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(RegisterTraderActivity.this);
                    alertdialog.setMessage("Successfully Registered!");
                    alertdialog.setCancelable(false);
                    alertdialog.setTitle("Marikiti Trader Registration");
                    alertdialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(RegisterTraderActivity.this, FieldAgentDashboardActivity.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog alertDialog = alertdialog.create();
                    alertDialog.show();

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
