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

public class ApplicaticantStep7DetailsOfLandLordActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    EditText landloardNames, phonenu;
    String landldnme, phno;
    private AppCompatButton appCompatButtonLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_landlord_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgdepo = findViewById(R.id.imgdepo);
        landloardNames = findViewById(R.id.etlandloardNames);
        phonenu = findViewById(R.id.etphonenumber);


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
        landldnme = Objects.requireNonNull(landloardNames.getText()).toString().trim();
        phno = Objects.requireNonNull(phonenu.getText()).toString().trim();

        if (TextUtils.isEmpty(landldnme)) {
            landloardNames.setError("LandLord Name Required");
            return;
        }
        if (TextUtils.isEmpty(phno) || phno.length() < 10) {
            phonenu.setError("Enter Valid Phone Number");
            return;
        } else {
            ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();
            processLoanBus.setLandlordname(landldnme);
            processLoanBus.setLandlordphoneno(phno);
            new proceedbtn().execute();
        }
    }

    public void validate() {
        if (isValidMobile(phno)) {
            new proceedbtn().execute();
        }
    }

    public boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[^(?:+254|\\+254|0)?(7(?:(?:[129][0-9])|(?:0[0-8])|(4[0-1]))[0-9]{6})$]+", phone)) {
            if (phone.length() < 13 || phone.length() > 13) {
                check = false;
                Toast.makeText(ApplicaticantStep7DetailsOfLandLordActivity.this, "+2547... Phone Number Required", Toast.LENGTH_SHORT).show();
            } else {
                check = android.util.Patterns.PHONE.matcher(phone).matches();
            }
        } else {
            check = false;
        }
        return check;
    }

    public class proceedbtn extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep7DetailsOfLandLordActivity.this);

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
                    Intent intent = new Intent(getApplicationContext(), ApplicaticantStep8BankDetailsActivity.class);
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


