package ics.kenya.com.RegionalManager.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.util.MonthYearPickerDialog;
import ics.kenya.com.db.MarikitiDataBaseAdapter;
import ics.kenya.com.model.FileReport;

public class FileFieldAgentReportActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    private AppCompatButton appCompatButtonSubmit;
    TextView username, Staffno, txttotalscore, txtcounty, txtconstituency, txtward, txtvwdate;
    String a, b, c, d, e, f, g, h, i, j;
    String totascore;

    SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
    MarikitiDataBaseAdapter db;
    Spinner staffsalestgt, ttlsales, tradersupport, newaccount, closedaccounts, traderscomplaints, daysoffwork, training, personalinitiative, treportedfraud;
    String uname = "Koskei", ushopname = "Shoe shop", staffno = "MKT1235", localid;

    EditText newtarget;
    String newtgt;
    String monthYearStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_report_activity);
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

        txtvwdate = findViewById(R.id.etdate);

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
        //spiner items
        staffsalestgt = findViewById(R.id.ssstaffsalestgt);
        ttlsales = findViewById(R.id.ssttlsales);
        tradersupport = findViewById(R.id.sstradersupport);
        newaccount = findViewById(R.id.ssnewaccount);
        closedaccounts = findViewById(R.id.ssclosed);
        traderscomplaints = findViewById(R.id.sstraderscomplaints);
        daysoffwork = findViewById(R.id.ssdaysoffwork);
        training = findViewById(R.id.sstraininng);
        personalinitiative = findViewById(R.id.sspersonalinitiative);
        treportedfraud = findViewById(R.id.sstreportedfraud);
        newtarget = findViewById(R.id.txtnewsalestarget);
        txttotalscore = findViewById(R.id.txttotalscore);


    /*    Spinner spinner = (Spinner) findViewById(R.id.ssstaffsalestgt);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.txtScale, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        staffsalestgt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                a = staffsalestgt.getItemAtPosition(i).toString();
                totascore = a;
                txttotalscore.setText("Total Score is: " + totascore);

            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        ttlsales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                b = ttlsales.getItemAtPosition(i).toString();
                totascore = a + b;
                txttotalscore.setText("Total Score is: " + totascore);
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        tradersupport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                c = tradersupport.getItemAtPosition(i).toString();
                totascore = a + b + c;
                txttotalscore.setText("Total Score is: " + totascore);
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });
        newaccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                d = newaccount.getItemAtPosition(i).toString();
                totascore = a + b + c + d;
                txttotalscore.setText("Total Score is: " + totascore);
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        closedaccounts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                e = closedaccounts.getItemAtPosition(i).toString();
                totascore = a + b + c + d + e;
                txttotalscore.setText("Total Score is: " + totascore);
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });
        traderscomplaints.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                f = traderscomplaints.getItemAtPosition(i).toString();
                totascore = a + b + c + d + e + f;
                txttotalscore.setText("Total Score is: " + totascore);
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        daysoffwork.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                g = daysoffwork.getItemAtPosition(i).toString();
                totascore = a + b + c + d + e + f + g;
                txttotalscore.setText("Total Score is: " + totascore);
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });
        training.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                h = training.getItemAtPosition(i).toString();
                totascore = a + b + c + d + e + f + g + h;
                txttotalscore.setText("Total Score is: " + totascore);
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });
        personalinitiative.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int t, long l) {
                i = personalinitiative.getItemAtPosition(t).toString();
                totascore = a + b + c + d + e + f + g + h + i;
                txttotalscore.setText("Total Score is: " + totascore);
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });
        treportedfraud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int t, long l) {
                j = treportedfraud.getItemAtPosition(t).toString();
                totascore = a + b + c + d + e + f + g + h + i + j;
                txttotalscore.setText("Total Score is: " + totascore);

            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        appCompatButtonSubmit = findViewById(R.id.appCompatButtonSubmit);

        appCompatButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newtgt = Objects.requireNonNull(newtarget.getText()).toString().toUpperCase().trim();

                if (TextUtils.isEmpty(newtgt)) {
                    newtarget.setError("New Target Required");
                    return;
                }
                new filereport().execute();
            }
        });
    }//end onCreate

    private void SubmitTraderDetails() {
        FileReport fileReport = new FileReport(
                0,
                uname,
                "",
                ushopname,
                staffno,
                "",
                a,
                b,
                c,
                d,
                e,
                f,
                g,
                h,
                i,
                j,
                totascore,
                newtgt
        );
        db.insertfilereport(fileReport);

    }

    public class filereport extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(FileFieldAgentReportActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Filing Report....Please Wait!");
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
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(FileFieldAgentReportActivity.this);
                    alertdialog.setMessage("Successfully Filed!");
                    alertdialog.setCancelable(false);
                    alertdialog.setTitle("Report Filing");
                    alertdialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    finish();
                                }
                            });
                    AlertDialog alertDialog = alertdialog.create();
                    alertDialog.show();

                }
            });
        }
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
}