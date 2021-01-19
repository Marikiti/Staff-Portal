package ics.kenya.com.RegionalManager.activities;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.common.RegionalManagerDashboardActivity;
import ics.kenya.com.db.MarikitiDataBaseAdapter;

public class AssignFieldAgentOfficerActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;
    private AppCompatButton appCompatButtonLogin;
    @BindView(R.id.etcounty)
    EditText etcounty;
    @BindView(R.id.etConstituency)
    EditText etConstituency;
    @BindView(R.id.etWard)
    EditText etWard;
    @BindView(R.id.etSalesTarget)
    EditText etSalesTarget;

    MarikitiDataBaseAdapter db;
    String county, constituency, ward, salestarget, status = "ACTIVE";

    String fullnames, idno, dob, marikituserno, uname, emailaddress, phoneno, addres;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_officer_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        db = new MarikitiDataBaseAdapter(getApplicationContext());

        imgdepo = findViewById(R.id.imgdepo);
        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            fullnames = extras.getString("fullname");
            idno = extras.getString("idno");
            dob = extras.getString("dob");
            marikituserno = extras.getString("marikituser");
            uname = extras.getString("username");
            emailaddress = extras.getString("emailadd");
            phoneno = extras.getString("phonenum");
            addres = extras.getString("adress");

        }

        appCompatButtonLogin = findViewById(R.id.appCompatButtonEnter);


        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                submitdata();


            }
        });

    }//end onCreate

    public void submitdata() {
        new register().execute();
       /* county = etcounty.getText().toString().trim();
        constituency = etConstituency.getText().toString().trim();
        ward = etWard.getText().toString().trim();
        salestarget = etSalesTarget.getText().toString().trim();

        if (TextUtils.isEmpty(county)) {
            etcounty.setError("Enter County");
            return;
        }
        if (TextUtils.isEmpty(constituency)) {
            etConstituency.setError("Enter Constituency");
            return;
        }
        if (TextUtils.isEmpty(ward)) {
            etWard.setError("Enter Ward");
            return;
        }
        if (TextUtils.isEmpty(salestarget)) {
            etSalesTarget.setError("Enter Sales Target");
            return;
        } else {
            new register().execute();
        }*/
    }

    public void SubmitTraderDetails() {
      /*  TraderRegBus traderRegBus = TraderRegBus.getSingle_instance();
        RegisterTrader registerTrader = new RegisterTrader(
                0,
                "MKT12552020",
                fullnames,
                idno,
                dob,
                marikituserno,
                uname,
                emailaddress,
                phoneno,
                addres,
                county,
                constituency,
                ward,
                salestarget,
                status

        );

        db.registterTrader(registerTrader);
*/
    }

    public class register extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(AssignFieldAgentOfficerActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Assigning....Please Wait!");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
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
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(AssignFieldAgentOfficerActivity.this);
                    alertdialog.setMessage("Successfully Assigned!");
                    alertdialog.setCancelable(false);
                    alertdialog.setTitle("Marikiti Agent");
                    alertdialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent depositsintent = new Intent(getApplicationContext(), RegionalManagerDashboardActivity.class);
                                    startActivity(depositsintent);
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

