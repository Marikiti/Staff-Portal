package ics.kenya.com.FieldAgent.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.FieldAgent.common.FieldAgentDashboardActivity;
import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.util.MonthYearPickerDialog;
import ics.kenya.com.db.MarikitiDataBaseAdapter;
import ics.kenya.com.model.FileReport;

public class FileTraderReportActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    TextView username, shopname, tradernumber;
    String uname, ushopname, staffno, localid;

    private AppCompatButton appCompatButtonSubmit;

    EditText newtarget;
    TextView txttotalscore, txtvwdate;
    String a, b, c, d, e, f, g, h, i, j, newtgt;
    String totascore, sum;
    String monthYearStr;
    Spinner salestgt, squalityofproducts, srange, scustomerservice, spersonalhygiene, straininngneeds, speriodonlocation, scomplaints, slostreturnsgoods, streportedfraud;
    SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
    public DecimalFormat currency = new DecimalFormat("Ksh: ###,##0.00");
    MarikitiDataBaseAdapter db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_field_agent_report_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        db = new MarikitiDataBaseAdapter(getApplicationContext());

        imgdepo = findViewById(R.id.imgdepo);

        imgdepo = findViewById(R.id.imgdepo);
        username = findViewById(R.id.username);
        shopname = findViewById(R.id.shopname);
        tradernumber = findViewById(R.id.tradernumber);


        imgdepo.setOnClickListener(new View.OnClickListener() {
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

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String currentDateandTime = sdf.format(new Date());
        txtvwdate.setText(currentDateandTime);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            uname = extras.getString("traderusername");
            staffno = extras.getString("staffnumber");
            ushopname = extras.getString("shopname");

        }

        username.setText(uname);
        tradernumber.setText(staffno);
        shopname.setText(ushopname);

        appCompatButtonSubmit = findViewById(R.id.appCompatButtonSubmit);

        salestgt = findViewById(R.id.ssalestgt);
        squalityofproducts = findViewById(R.id.ssqualityofproducts);
        srange = findViewById(R.id.ssrange);
        scustomerservice = findViewById(R.id.sscustomerservice);
        spersonalhygiene = findViewById(R.id.sspersonalhygiene);
        straininngneeds = findViewById(R.id.sstraininngneeds);
        speriodonlocation = findViewById(R.id.ssperiodonlocation);
        scomplaints = findViewById(R.id.sscomplaints);
        slostreturnsgoods = findViewById(R.id.sslostreturnsgoods);
        streportedfraud = findViewById(R.id.sstreportedfraud);
        newtarget = findViewById(R.id.txtnewsalestarget);
        txttotalscore = findViewById(R.id.txttotalscore);

        salestgt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                a = salestgt.getItemAtPosition(i).toString();

                totascore = a;
                txttotalscore.setText("Total Score is: " + totascore);

            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        squalityofproducts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                b = squalityofproducts.getItemAtPosition(i).toString();

                totascore = a + b;
                txttotalscore.setText("Total Score is: " + totascore);

            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });


        srange.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                c = srange.getItemAtPosition(i).toString();

                totascore = a + b + c;
                txttotalscore.setText("Total Score is: " + totascore);
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        scustomerservice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                d = scustomerservice.getItemAtPosition(i).toString();

                totascore = a + b + c + d;
                txttotalscore.setText("Total Score is: " + totascore);

            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });


        spersonalhygiene.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                e = spersonalhygiene.getItemAtPosition(i).toString();

                totascore = a + b + c + d + e;
                txttotalscore.setText("Total Score is: " + totascore);

            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        straininngneeds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                f = straininngneeds.getItemAtPosition(i).toString();

                totascore = a + b + c + d + e + f;
                txttotalscore.setText("Total Score is: " + totascore);

            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        speriodonlocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                g = speriodonlocation.getItemAtPosition(i).toString();
                totascore = a + b + c + d + e + f + g;
                txttotalscore.setText("Total Score is: " + totascore);

            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });
        scomplaints.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                h = scomplaints.getItemAtPosition(i).toString();

                totascore = a + b + c + d + e + f + g + h;
                txttotalscore.setText("Total Score is: " + totascore);

            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });
        slostreturnsgoods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int t, long l) {
                i = slostreturnsgoods.getItemAtPosition(t).toString();

                totascore = a + b + c + d + e + f + g + h + i;
                txttotalscore.setText("Total Score is: " + totascore);

            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {
            }
        });
        streportedfraud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int t, long l) {
                j = streportedfraud.getItemAtPosition(t).toString();

                sum = a + b + c + d + e + f + g + h + i + j;

               // txttotalscore.setText("Total Score is: " + sum);


            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        appCompatButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newtgt = Objects.requireNonNull(newtarget.getText()).toString().toUpperCase().trim();


                if ("0".equals(a)) {
                    TextView errorText = (TextView) salestgt.getSelectedView();
                    if (errorText != null) {
                        errorText.setError("");
                        errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        errorText.setText("Field Required");
                    }
                    return;
                } else if ("0".equals(b)) {
                    TextView errorText = (TextView) squalityofproducts.getSelectedView();
                    if (errorText != null) {
                        errorText.setError("");
                        errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        errorText.setText("Field Required");
                    }
                    return;
                } else if ("0".equals(c)) {
                    TextView errorText = (TextView) srange.getSelectedView();
                    if (errorText != null) {
                        errorText.setError("");
                        errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        errorText.setText("Field Required");
                    }
                    return;
                } else if ("0".equals(d)) {
                    TextView errorText = (TextView) scustomerservice.getSelectedView();
                    if (errorText != null) {
                        errorText.setError("");
                        errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        errorText.setText("Field Required");
                    }
                    return;
                } else if ("0".equals(e)) {
                    TextView errorText = (TextView) spersonalhygiene.getSelectedView();
                    if (errorText != null) {
                        errorText.setError("");
                        errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        errorText.setText("Field Required");
                    }
                    return;
                } else if ("0".equals(f)) {
                    TextView errorText = (TextView) straininngneeds.getSelectedView();
                    if (errorText != null) {
                        errorText.setError("");
                        errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        errorText.setText("Field Required");
                    }
                    return;
                } else if ("0".equals(g)) {
                    TextView errorText = (TextView) speriodonlocation.getSelectedView();
                    if (errorText != null) {
                        errorText.setError("");
                        errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        errorText.setText("Field Required");
                    }
                    return;
                } else if ("0".equals(h)) {
                    TextView errorText = (TextView) scomplaints.getSelectedView();
                    if (errorText != null) {
                        errorText.setError("");
                        errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        errorText.setText("Field Required");
                    }
                    return;
                } else if ("0".equals(i)) {
                    TextView errorText = (TextView) slostreturnsgoods.getSelectedView();
                    if (errorText != null) {
                        errorText.setError("");
                        errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        errorText.setText("Field Required");
                    }
                    return;
                } else if ("0".equals(j)) {
                    TextView errorText = (TextView) streportedfraud.getSelectedView();
                    if (errorText != null) {
                        errorText.setError("");
                        errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        errorText.setText("Field Required");
                    }
                    return;
                } else if (TextUtils.isEmpty(newtgt)) {
                    newtarget.setError("New Target Required");
                    return;
                } else {
                    new filereport().execute();

                }

            }
        });

    }//end onCreate

    private void SubmitTraderDetails() {
        FileReport fileReport = new FileReport(
                0,
                uname,
                "TRADER NO: MKTR22565",
                ushopname,
                staffno,
                "07/NOV/2020",
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

        private ProgressDialog mprogress = new ProgressDialog(FileTraderReportActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setTitle("Filing Report");
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
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(FileTraderReportActivity.this);
                    alertdialog.setMessage("Successfully Filed!");
                    alertdialog.setCancelable(false);
                    alertdialog.setTitle("Trader Report");
                    alertdialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(FileTraderReportActivity.this, FieldAgentDashboardActivity.class);
                                    startActivity(intent);
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