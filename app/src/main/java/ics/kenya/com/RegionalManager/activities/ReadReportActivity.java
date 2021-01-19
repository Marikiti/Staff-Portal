package ics.kenya.com.RegionalManager.activities;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.adapter.ReadReportAdapter;
import ics.kenya.com.db.MarikitiDataBaseAdapter;
import ics.kenya.com.model.FileReport;

public class ReadReportActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    TextView username, shopname, tradernumber;
    String uname, ushopname, staffno, localid;

    private AppCompatButton appCompatButtonSubmit;

    TextView txttotalscore;
    String a, b, c, d, e, f, g, h, i, j;
    int totascore;

    TextView txtsalestarget, txtstaffsalegrowthscore, txtviewrange, txtcustomerservicescore, txtpersonalhygienescore, txtTrainingnnedsscore, txtperiodonlocationscore, txttcomlpaintsscore, txtlostreturnsgoodsscore, txtwreportedfraudscore;

    private List fileReportLists = new ArrayList<>();
    private ArrayList<FileReport> fileReportarrrayList = new ArrayList<>();
    FileReport fileReport;
    ReadReportAdapter arrayAdapter;
    private HashMap<Integer, String> localhash = new HashMap<>();
    TextView srchstaff;

    MarikitiDataBaseAdapter db;
    String name, status = "INACTIVE";

    AppCompatButton ButtonEXIT;
    String localID, tradername, traderphoneno, county, constituency, ward, totalsales, salestarget, totalnooftraders;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        db = new MarikitiDataBaseAdapter(getApplicationContext());
        // Locate the EditText in listview_main.xml

        imgdepo = findViewById(R.id.imgdepo);


        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            uname = extras.getString("traderusername");
            staffno = extras.getString("staffnumber");
            ushopname = extras.getString("shopname");

        }

        showReport();


    }//end onCreate


    private void showReport() {
        ListView listView = (ListView) findViewById(R.id.listview);
        //  if (fileReport.getStaffno().equals(staffno)) {
        List<FileReport> fileReportLists = db.getfiledreportfortrader();

        if (fileReportLists.size() != 0) {
            for (int i = 0; i < fileReportLists.size(); i++) {
                fileReportarrrayList.add(new FileReport(
                        0,
                        fileReportLists.get(i).getUsername(),
                        fileReportLists.get(i).getTradercode(),
                        fileReportLists.get(i).getShopname(),
                        fileReportLists.get(i).getStaffno(),
                        fileReportLists.get(i).getDate(),
                        fileReportLists.get(i).getA(),
                        fileReportLists.get(i).getB(),
                        fileReportLists.get(i).getC(),
                        fileReportLists.get(i).getD(),
                        fileReportLists.get(i).getE(),
                        fileReportLists.get(i).getF(),
                        fileReportLists.get(i).getG(),
                        fileReportLists.get(i).getH(),
                        fileReportLists.get(i).getI(),
                        fileReportLists.get(i).getJ(),
                        fileReportLists.get(i).getTotalscore(),
                        fileReportLists.get(i).getNewtarget()
                ));
                localhash.put(i, fileReportLists.get(i).getLocalId());
                arrayAdapter = new ReadReportAdapter(getApplicationContext(), fileReportarrrayList);
                listView.setAdapter(arrayAdapter);
            }

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View viewClicked, int position, long id) {
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
}




