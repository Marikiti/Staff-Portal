package ics.kenya.com.FieldAgent.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.FieldAgent.common.FieldAgentDashboardActivity;
import ics.kenya.com.R;
import ics.kenya.com.db.MarikitiDataBaseAdapter;


public class TraderPortfolioDetailsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    @BindView(R.id.cardViewviewProcessLoans)
    CardView cardViewviewProcessLoans;
    @BindView(R.id.cardViewviewPSuspendAccount)
    CardView cardViewviewPSuspendAccount;
    @BindView(R.id.cardViewViewFileTraderReport)
    CardView cardViewViewFileTraderReport;
    TextView username, Staffno;
    public String uname, uphoneno, ustaffno;
    MarikitiDataBaseAdapter db;
    String statusoption, shopname;
    int localID;

    AppCompatButton buttonEXIT;
    Spinner ssshopname;

    int localid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trader_details);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        db = new MarikitiDataBaseAdapter(getApplicationContext());

        imgdepo = findViewById(R.id.imgdepo);
        username = findViewById(R.id.username);
        Staffno = findViewById(R.id.StaffNo);
        ssshopname = findViewById(R.id.ssshopname);
        buttonEXIT = findViewById(R.id.appCompatButtonEXIT);
        buttonEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FieldAgentDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            localID = Integer.parseInt(extras.getString("localID"));
            uname = extras.getString("full_names");
            ustaffno = extras.getString("staffnumber");
            shopname = extras.getString("shopname");

        }
        username.setText(uname);
        Staffno.setText(ustaffno);

        localid = Integer.parseInt(String.valueOf(localID));

        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cardViewviewProcessLoans.setOnClickListener(v -> {
            shopname = Objects.requireNonNull(ssshopname.getSelectedItem()).toString().trim();
            if ("-Select Shop-".equals(shopname)) {
                TextView errorText = (TextView) ssshopname.getSelectedView();
                if (errorText != null) {
                    errorText.setError("");
                    errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    errorText.setText("Shop Required");
                }
                return;
            } else {
                Intent depositsintent = new Intent(getApplicationContext(), ApplicaticantStep1LoanProcessingActivity.class);
                depositsintent.putExtra("traderusername", uname);
                depositsintent.putExtra("staffnumber", ustaffno);
                depositsintent.putExtra("shopname", shopname);
                startActivity(depositsintent);
            }

        });
        cardViewviewPSuspendAccount.setOnClickListener(v -> {
            showpinDialog();

        });

        cardViewViewFileTraderReport.setOnClickListener(v -> {
            shopname = Objects.requireNonNull(ssshopname.getSelectedItem()).toString().trim();

            if ("-Select Shop-".equals(shopname)) {
                TextView errorText = (TextView) ssshopname.getSelectedView();
                if (errorText != null) {
                    errorText.setError("");
                    errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    errorText.setText("Shop Required");
                }
                return;
            } else {
                Intent depositsintent = new Intent(getApplicationContext(), FileTraderReportActivity.class);
                depositsintent.putExtra("traderusername", uname);
                depositsintent.putExtra("staffnumber", ustaffno);
                depositsintent.putExtra("shopname", shopname);
                startActivity(depositsintent);
            }
        });


    }//end onCreate

    public void UpdateTraderDetails() {
        db.updateRegisterTrader(localID, statusoption);
    }


    public void showpinDialog() {
        // TODO Auto-generated method stub
        final Dialog dialog = new Dialog(TraderPortfolioDetailsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.row_spinner);
        dialog.setCancelable(true);

        // set the custom dialog components - text, image and button
        final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner1);
        final EditText edittext = (EditText) dialog.findViewById(R.id.editText1);
        Button button = (Button) dialog.findViewById(R.id.button1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItem().equals("ACTIVATE")) {
                    statusoption = "ACTIVE";
                }
                if (spinner.getSelectedItem().equals("DEACTIVATE")) {
                    statusoption = "INACTIVE";
                }
                if (spinner.getSelectedItem().equals("SUSPEND")) {
                    statusoption = "SUSPENDED";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                new submitdata().execute();
            }
        });
        dialog.show();

    }


    public void showpin2Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.account));
        final EditText acc = new EditText(this);
        final EditText etpin = new EditText(this);
        etpin.setInputType(InputType.TYPE_CLASS_PHONE);
        acc.setInputType(InputType.TYPE_CLASS_PHONE);
        etpin.setHint(getString(R.string.pin_marikiti));
        etpin.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        builder.setView(acc);
        builder.setView(etpin);

        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {

            new submitdata().execute();

        });
        builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
            dialog.cancel();
        });

        builder.show();
    }

    public class submitdata extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(TraderPortfolioDetailsActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setTitle("Loading...");
            mprogress.setMessage("Proceed to fill other steps!!");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            UpdateTraderDetails();
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
                    Intent intent = new Intent(getApplicationContext(), FieldAgentDashboardActivity.class);
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

