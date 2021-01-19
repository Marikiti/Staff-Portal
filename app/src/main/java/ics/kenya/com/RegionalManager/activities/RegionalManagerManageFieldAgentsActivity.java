package ics.kenya.com.RegionalManager.activities;

import android.app.ProgressDialog;
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

import java.text.SimpleDateFormat;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.R;
import ics.kenya.com.db.MarikitiDataBaseAdapter;

public class RegionalManagerManageFieldAgentsActivity extends AppCompatActivity  {
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
        setContentView(R.layout.manage_agents_activity);
        ButterKnife.bind(this);

        db = new MarikitiDataBaseAdapter(getApplicationContext());
      //  simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgdepo = findViewById(R.id.imgdepo);


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


    public void submitdata() {


            new navigate().execute();

    }

    public void SubmitTraderDetails() {


    }


    public class navigate extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(RegionalManagerManageFieldAgentsActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Proceed To Assign!!!");
            mprogress.setCancelable(false);
            mprogress.setIndeterminate(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            SubmitTraderDetails();
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
                    Intent intent = new Intent(RegionalManagerManageFieldAgentsActivity.this, AssignFieldAgentOfficerActivity.class);
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

