package ics.kenya.com.RegionalManager.common;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.activities.FileFieldAgentReportActivity;
import ics.kenya.com.RegionalManager.activities.RegionalMangerTraderRegistrationActivity;
import ics.kenya.com.RegionalManager.activities.SearchFieldAgentStaffActivity;
import ics.kenya.com.RegionalManager.activities.ViewList.RegionalManagerViewFieldAgentsListActivity;

public class RegionalManagerDashboardActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.cardViewmanageagents)
    CardView cardViewmanageagents;
    @BindView(R.id.cardViewViewFieldAgents)
    CardView cardViewViewFieldAgents;
    @BindView(R.id.cardViewFileregionReport)
    CardView cardViewFileregionReport;
    @BindView(R.id.cardViewRegisterTrader)
    CardView cardViewRegisterTrader;
    @BindView(R.id.cardviewcustomercare)
    CardView cardviewcustomercare;
    @BindView(R.id.cardViewmessages)
    CardView cardViewmessages;

    ImageView imgdepo;

    AppCompatButton buttonEXIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
        buttonEXIT = findViewById(R.id.appCompatButtonEXIT);
        buttonEXIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cardViewmanageagents.setOnClickListener(v -> {
            Intent depositsintent = new Intent(getApplicationContext(), SearchFieldAgentStaffActivity.class);
            startActivity(depositsintent);

        });

        cardViewViewFieldAgents.setOnClickListener(v -> {
            Intent depositsintent = new Intent(getApplicationContext(), RegionalManagerViewFieldAgentsListActivity.class);
            startActivity(depositsintent);

        });

        cardViewFileregionReport.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FileFieldAgentReportActivity.class);
            startActivity(intent);

        });

        cardViewRegisterTrader.setOnClickListener(v -> {
            Intent depositsintent = new Intent(getApplicationContext(), RegionalMangerTraderRegistrationActivity.class);
            startActivity(depositsintent);

        });
        cardviewcustomercare.setOnClickListener(v -> {
           /* Intent depositsintent = new Intent(getApplicationContext(), FileReportActivity.class);
            startActivity(depositsintent);*/

        });
        cardViewmessages.setOnClickListener(v -> {
          /*  Intent depositsintent = new Intent(getApplicationContext(), FileReportActivity.class);
            startActivity(depositsintent);*/

        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
}