package ics.kenya.com.FieldAgent.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.FieldAgent.Bus.ProcessLoanBus;
import ics.kenya.com.R;
import ics.kenya.com.FieldAgent.common.FieldAgentDashboardActivity;
import ics.kenya.com.db.MarikitiDataBaseAdapter;

public class ApplicaticantStep10OtherBusinessActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    EditText busname, kra, typeofbus, address;
    Spinner sscouty;
    MarikitiDataBaseAdapter db;

    String businessname, krapin, businesstype, countyname, homeaddres;
    private AppCompatButton appCompatButtonLogin, btnsave;

    ImageView imageadd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_bussness_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        db = new MarikitiDataBaseAdapter(getApplicationContext());

        imgdepo = findViewById(R.id.imgdepo);

        busname = findViewById(R.id.etBusinessname);
        kra = findViewById(R.id.etkrapin);
        typeofbus = findViewById(R.id.ettypeofbus);
        sscouty = findViewById(R.id.sscouty);
        address = findViewById(R.id.etbusaddress);
        imageadd = findViewById(R.id.imageadddata);


        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appCompatButtonLogin = findViewById(R.id.appCompatButtonEnter);
        btnsave = findViewById(R.id.appCompatButtonSubmit);


        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitdata();

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

                addotherbusinesses();
            }
        });

    }//end onCreate

    public class refreshing extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep10OtherBusinessActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Add Other Businesses!");
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
                    busname.setText(null);
                    kra.setText(null);
                    typeofbus.setText(null);
                    address.setText(null);

                }
            });
        }
    }

    public class addotherbusiness extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep10OtherBusinessActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Saving Other Businesses!");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // addotherbusinesses();
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

    private void addotherbusinesses() {
        businessname = Objects.requireNonNull(busname.getText()).toString().trim();
        krapin = Objects.requireNonNull(kra.getText()).toString().trim();
        businesstype = Objects.requireNonNull(typeofbus.getText()).toString().trim();
        countyname = Objects.requireNonNull(sscouty.getSelectedItem()).toString().trim();
        homeaddres = Objects.requireNonNull(address.getText()).toString().trim();
        ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();

        long appl_id = db.processloan(processLoanBus.getName(),
                processLoanBus.getShopname(),
                processLoanBus.getStaffno(),
                processLoanBus.getDate(),
                processLoanBus.getPurposeOfLoans(),
                processLoanBus.getDurationincurrLocation(),
                processLoanBus.getYearsinbussiness(),
                processLoanBus.getLevelofEducation());

        db.insertOtherBusiness(Integer.parseInt(String.valueOf(appl_id)),
                businessname,
                krapin,
                businesstype,
                countyname,
                homeaddres);

        new addotherbusiness().execute();
    }


    private void submitdata() {
        businessname = Objects.requireNonNull(busname.getText()).toString().trim();
        krapin = Objects.requireNonNull(kra.getText()).toString().trim();
        businesstype = Objects.requireNonNull(typeofbus.getText()).toString().trim();
        countyname = Objects.requireNonNull(sscouty.getSelectedItem()).toString().trim();
        homeaddres = Objects.requireNonNull(address.getText()).toString().trim();

        ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();
        processLoanBus.setBusinessname(businessname);
        processLoanBus.setKrapin(krapin);
        processLoanBus.setTypeofbusiness(businesstype);
        processLoanBus.setCountyname(countyname);
        processLoanBus.setBusinessaddress(homeaddres);
      /*  if (TextUtils.isEmpty(businessname)) {
            busname.setError("Business Name Required");
            return;
        }
        if (TextUtils.isEmpty(krapin)) {
            kra.setError("Business Name Required");
            return;
        }
        if (TextUtils.isEmpty(businesstype)) {
            typeofbus.setError("Business Name Required");
            return;
        }
        if ("-SELECT COUNTY-".equals(countyname)) {
            TextView errorText = (TextView) sscouty.getSelectedView();
            if (errorText != null) {
                errorText.setError("");
                errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                errorText.setText("Required");
            }
            return;
        }
        if (TextUtils.isEmpty(homeaddres)) {
            address.setError("Address Required");
            return;
        } */


        long appl_id = db.processloan(processLoanBus.getName(),
                processLoanBus.getShopname(),
                processLoanBus.getStaffno(),
                processLoanBus.getDate(),
                processLoanBus.getPurposeOfLoans(),
                processLoanBus.getDurationincurrLocation(),
                processLoanBus.getYearsinbussiness(),
                processLoanBus.getLevelofEducation());

        db.insertmaritalstatus(Integer.parseInt(String.valueOf(appl_id)),
                processLoanBus.getStatus(),
                processLoanBus.getNames(),
                processLoanBus.getPhonenumber(),
                processLoanBus.getEmployement(),
                processLoanBus.getEmployername());

        db.insertNextofkin(Integer.parseInt(String.valueOf(appl_id)),
                processLoanBus.getStatus(),
                processLoanBus.getNames(),
                processLoanBus.getPhonenumber(),
                processLoanBus.getEmployement(),
                processLoanBus.getEmployername());


        db.insertReferee(Integer.parseInt(String.valueOf(appl_id)),
                processLoanBus.getRefereestatus(),
                processLoanBus.getRefereenames(),
                processLoanBus.getRefereephonenumber(),
                processLoanBus.getRefereeemployement(),
                processLoanBus.getRefereeemployername());

        db.insertcurrentlocation(Integer.parseInt(String.valueOf(appl_id)),
                processLoanBus.getStreetname(),
                processLoanBus.getStreetno(),
                processLoanBus.getHsno(),
                processLoanBus.getAdrres());

        db.inserthomeaddress(Integer.parseInt(String.valueOf(appl_id)),
                processLoanBus.getCounty(),
                processLoanBus.getConstituency(),
                processLoanBus.getWard(),
                processLoanBus.getChiefsname(),
                processLoanBus.getContacts());


        db.insertlandlorddetails(Integer.parseInt(String.valueOf(appl_id)),
                processLoanBus.getLandlordname(),
                processLoanBus.getLandlordphoneno());

        db.insertbankdetails(Integer.parseInt(String.valueOf(appl_id)),
                processLoanBus.getAccountname(),
                processLoanBus.getBankname(),
                processLoanBus.getBankbranch(),
                processLoanBus.getAccountno(),
                processLoanBus.getYearswithbank());

        db.insertOtherLoans(Integer.parseInt(String.valueOf(appl_id)),
                processLoanBus.getCompany(),
                processLoanBus.getLoanamount(),
                processLoanBus.getBalance());

        db.insertOtherBusiness(Integer.parseInt(String.valueOf(appl_id)),
                businessname,
                krapin,
                businesstype,
                countyname,
                homeaddres);
        new proceedbtn().execute();

       /* if (appl_id.isEmpty()) {
            Toast.makeText(this, "Empty applicant id", Toast.LENGTH_SHORT).show();
        } else {
            new proceedbtn().execute();
        }*/


    }

    public class proceedbtn extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep10OtherBusinessActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Submitting Loan Request...Please wait!");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(4000);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Void result) {
            runOnUiThread(new Runnable() {
                @SuppressLint({"UseCompatLoadingForDrawables", "NewApi"})
                public void run() {
                    mprogress.dismiss();
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(ApplicaticantStep10OtherBusinessActivity.this);
                    alertdialog.setMessage("Loan Successfully Submitted");
                    alertdialog.setIcon(R.drawable.ic_baseline_done_24);
                    alertdialog.setCancelable(false);
                    alertdialog.setTitle("Marikiti Loan Processing");
                    alertdialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(getApplicationContext(), FieldAgentDashboardActivity.class);
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

