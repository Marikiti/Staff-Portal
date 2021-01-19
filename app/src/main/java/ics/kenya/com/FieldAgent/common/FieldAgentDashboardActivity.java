package ics.kenya.com.FieldAgent.common;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.FieldAgent.activities.RegisterTraderActivity;
import ics.kenya.com.FieldAgent.activities.ViewList.ViewTradersActivity;
import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.activities.ReadReportActivity;
import ics.kenya.com.RegionalManager.util.MonthYearPickerDialog;
import ics.kenya.com.db.MarikitiDataBaseAdapter;

public class FieldAgentDashboardActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.cardViewviewRegistertraders)
    CardView cardViewviewRegistertraders;
    @BindView(R.id.cardViewViewViewTraders)
    CardView cardViewViewViewTraders;
    @BindView(R.id.cardViewmessages)
    CardView cardViewmessages;
    @BindView(R.id.cardViewReadReport)
    CardView cardViewReadReport;
    MarikitiDataBaseAdapter db;
    ImageView imgdepo;
    EditText nooftraders;

    AppCompatButton buttonEXIT;

    SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");

    String monthYearStr;
    TextView txtvwdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.field_agent_activity_dashboard);
        ButterKnife.bind(this);
        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        db = new MarikitiDataBaseAdapter(getApplicationContext());

        nooftraders = findViewById(R.id.etnooftraders);
        getTraderCount();

        imgdepo = findViewById(R.id.imgdepo);
        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonEXIT = findViewById(R.id.appCompatButtonEXIT);
        buttonEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtvwdate = findViewById(R.id.txtviewdate);

        txtvwdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonthYearPickerDialog pickerDialog = new MonthYearPickerDialog();
                pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
                        monthYearStr = year + "-" + (month + 1) + "-" + i2;
                        txtvwdate.setText(formatMonthYear(monthYearStr));
                    }
                });
                pickerDialog.show(getSupportFragmentManager(), "MonthYearPickerDialog");
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
        String currentDateandTime = sdf.format(new Date());
        txtvwdate.setText(currentDateandTime);

        cardViewviewRegistertraders.setOnClickListener(v -> {
            Intent depositsintent = new Intent(getApplicationContext(), RegisterTraderActivity.class);
            startActivity(depositsintent);


        });

        cardViewViewViewTraders.setOnClickListener(v -> {
            new viewtraders().execute();


        });

        cardViewReadReport.setOnClickListener(v -> {
            new readreport().execute();
        });

        cardViewmessages.setOnClickListener(v -> {
          /*  Intent depositsintent = new Intent(getApplicationContext(), TraderDetailsActivity.class);
            startActivity(depositsintent);*/
        });
    }

    String formatMonthYear(String str) {
        Date date = null;
        try {
            date = input.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }

    public class readreport extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(FieldAgentDashboardActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Fetching Report....Please Wait!");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Void result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    mprogress.dismiss();
                    Intent depositsintent = new Intent(getApplicationContext(), ReadReportActivity.class);
                    startActivity(depositsintent);

                }
            });
        }
    }

    public class viewtraders extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(FieldAgentDashboardActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Fetching Registered Traders....Please Wait!");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Void result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    mprogress.dismiss();
                    Intent depositsintent = new Intent(getApplicationContext(), ViewTradersActivity.class);
                    startActivity(depositsintent);

                }
            });
        }
    }

    private void getTraderCount() {
        int totalnumberoftraders = db.getTraderCount();
        nooftraders.setText("Number of Registered Traders : " + totalnumberoftraders);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
}
