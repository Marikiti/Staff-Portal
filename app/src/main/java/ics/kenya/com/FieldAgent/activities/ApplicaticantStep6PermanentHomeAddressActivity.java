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
import android.widget.Spinner;
import android.widget.TextView;
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

public class ApplicaticantStep6PermanentHomeAddressActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    Spinner scounty, sconstituency;
    EditText ward, chiefsname, contacts;
    String countyname, constituencyname, wardname, chiefname, contactsname;
    private AppCompatButton appCompatButtonLogin;

    String[] ssconstituencyspinner = {"-SELECT CONSTITUENCY-", "Bomet East", "Bomet Central", "Chepalungu", "Sotik"};
    String[] ssnarokconstspinner = {"-SELECT CONSTITUENCY-", "Transmara East", "Emurua Dikir", "Narok South"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_address_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgdepo = findViewById(R.id.imgdepo);

        scounty = findViewById(R.id.sscounty);
        sconstituency = findViewById(R.id.ssconstituency);
        ward = findViewById(R.id.etward);
        chiefsname = findViewById(R.id.etchiefsname);
        contacts = findViewById(R.id.etcontacts);
        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       /* //Creating the ArrayAdapter instance having the country list
        ArrayAdapter bmtcon =  new ArrayAdapter(this, android.R.layout.simple_spinner_item, ssconstituencyspinner);
        bmtcon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter narokcons = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ssnarokconstspinner);
        if (scounty.equals("NAROK")) {
            narokcons.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            sconstituency.setAdapter(narokcons);
        }*/

        appCompatButtonLogin = findViewById(R.id.appCompatButtonEnter);

        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (scounty.equals("BOMET")) {
                    //Setting the ArrayAdapter data on the Spinner
                    sconstituency.setAdapter(bmtcon);
                }*/
                savedatatobus();
            }
        });

    }//end onCreate


    private void savedatatobus() {
        countyname = Objects.requireNonNull(scounty.getSelectedItem()).toString().trim();
        constituencyname = Objects.requireNonNull(sconstituency.getSelectedItem()).toString().trim();
        wardname = Objects.requireNonNull(ward.getText()).toString().trim();
        chiefname = Objects.requireNonNull(chiefsname.getText()).toString().trim();
        contactsname = Objects.requireNonNull(contacts.getText()).toString().trim();


        if ("-SELECT COUNTY-".equals(countyname)) {
            TextView errorText = (TextView) scounty.getSelectedView();
            if (errorText != null) {
                errorText.setError("");
                errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                errorText.setText("Required");
            }
            return;
        }


        if ("-SELECT CONSTITUENCY-".equals(constituencyname)) {
            TextView errorText = (TextView) sconstituency.getSelectedView();
            if (errorText != null) {
                errorText.setError("");
                errorText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                errorText.setText("Required");
            }
            return;
        }
        if (TextUtils.isEmpty(wardname)) {
            ward.setError("Ward Required");
            return;
        }

        if (TextUtils.isEmpty(chiefname)) {
            chiefsname.setError("Chief Name Required");
            return;
        }
        if (TextUtils.isEmpty(contactsname) || contactsname.length() < 10) {
            contacts.setError("Enter valid Required");
            return;
        } else {

            ProcessLoanBus processLoanBus = ProcessLoanBus.getInstance();
            processLoanBus.setCounty(countyname);
            processLoanBus.setConstituency(constituencyname);
            processLoanBus.setWard(wardname);
            processLoanBus.setChiefsname(chiefname);
            processLoanBus.setContacts(contactsname);

            new proceedbtn().execute();
        }
    }

    public void validate() {
        if (isValidMobile(contactsname)) {
            new proceedbtn().execute();
        }
    }

    public boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[^(?:+254|\\+254|0)?(7(?:(?:[129][0-9])|(?:0[0-8])|(4[0-1]))[0-9]{6})$]+", phone)) {
            if (phone.length() < 13 || phone.length() > 13) {
                check = false;
                Toast.makeText(ApplicaticantStep6PermanentHomeAddressActivity.this, "+2547... Phone Number Required", Toast.LENGTH_SHORT).show();
            } else {
                check = android.util.Patterns.PHONE.matcher(phone).matches();
            }
        } else {
            check = false;
        }
        return check;
    }


    public class proceedbtn extends AsyncTask<Void, Void, Void> {
        private ProgressDialog mprogress = new ProgressDialog(ApplicaticantStep6PermanentHomeAddressActivity.this);

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
                    Intent intent = new Intent(getApplicationContext(), ApplicaticantStep7DetailsOfLandLordActivity.class);
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


