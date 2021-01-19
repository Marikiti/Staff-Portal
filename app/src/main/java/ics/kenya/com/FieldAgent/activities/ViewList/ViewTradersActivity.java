package ics.kenya.com.FieldAgent.activities.ViewList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.FieldAgent.activities.TraderPortfolioDetailsActivity;
import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.adapter.ViewStaffAdapter;
import ics.kenya.com.db.MarikitiDataBaseAdapter;
import ics.kenya.com.model.RegisterTrader;
import ics.kenya.com.model.ViewFieldAgents;

public class ViewTradersActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    TextView tradername;

    private List registerTraderLists = new ArrayList<>();
    private ArrayList<RegisterTrader> registerTraderrrayList = new ArrayList<>();
    RegisterTrader registerTrader;
    ViewStaffAdapter arrayAdapter;
    private HashMap<Integer, String> localhash = new HashMap<>();
    TextView searcchstaff;
    private ArrayList<ViewFieldAgents> ViewFieldAgentsList = new ArrayList<>();

    ViewFieldAgents viewFieldAgentsList = new ViewFieldAgents();
    ArrayList<ViewFieldAgents> results = new ArrayList<>();
    MarikitiDataBaseAdapter db;
    SearchView editsearch;
    String traderphoneno, staffno, county, constituency, ward, totalsales, salestarget, totalnooftraders;
    String name, status = "INACTIVE";
    String localID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_traders_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = new MarikitiDataBaseAdapter(getApplicationContext());
        // Locate the EditText in listview_main.xml

        showlistdata();
        editsearch = (SearchView) findViewById(R.id.searchview);
        editsearch.setVisibility(View.GONE);
        imgdepo = findViewById(R.id.imgdepo);
        searcchstaff = findViewById(R.id.srchstaff);
        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searcchstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editsearch.setVisibility(View.VISIBLE);
            }
        });
    }

    private ArrayList getListData() {


      /*  results.add(new ViewFieldAgents(1, "Joseph Kimani", "0724917075", "01111", "MKT123456", "", "Narok", "Narok south", "Abossi", "250000", "300000", "500"));
        results.add(new ViewFieldAgents(1, "Phylis Otieno", "0724557075", "01222", "MKT123466", "", "Kericho", "belgut", "Ololmasani", "80000", "100000", "800"));
        results.add(new ViewFieldAgents(1, "Mark Kariuki", "0724667075", "01333", "MKT123476", "", "Nairobi", "Westlands", "Mogondo", "60000", "90000", "300"));
*/
        return results;
    }

    private void showlistdata() {
        ListView listView = (ListView) findViewById(R.id.listview);
//        Toast.makeText(getApplicationContext(), String.valueOf(studentLists.size()),Toast.LENGTH_SHORT).show();
        List<RegisterTrader> registerTraderLists = db.getRegisterTraderList();
        if (registerTraderLists.size() != 0) {
            for (int i = 0; i < registerTraderLists.size(); i++) {
                registerTraderrrayList.add(new RegisterTrader(
                        0,
                        registerTraderLists.get(i).getTradercode(),
                        registerTraderLists.get(i).getFullnames(),
                        registerTraderLists.get(i).getIdno(),
                        registerTraderLists.get(i).getDob(),
                        registerTraderLists.get(i).getMarikitiUserNo(),
                        registerTraderLists.get(i).getUsername(),
                        registerTraderLists.get(i).getEmail(),
                        registerTraderLists.get(i).getPhoneNo(),
                        registerTraderLists.get(i).getAddress(),
                        registerTraderLists.get(i).getCounty(),
                        registerTraderLists.get(i).getConstituency(),
                        registerTraderLists.get(i).getWard(),
                        registerTraderLists.get(i).getSalesTarget(),
                        registerTraderLists.get(i).getStatus()
                ));
                localhash.put(i, registerTraderLists.get(i).getLocalID());
                arrayAdapter = new ViewStaffAdapter(getApplicationContext(), registerTraderrrayList);
                listView.setAdapter(arrayAdapter);

                editsearch = (SearchView) findViewById(R.id.searchview);

                editsearch.setOnQueryTextListener(this);
            }


        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View viewClicked, int position, long id) {
                name = registerTraderrrayList.get(position).getFullnames();
                staffno = registerTraderrrayList.get(position).getTradercode();
                status = registerTraderrrayList.get(position).getStatus();

                localID = localhash.get(position);
                if (localID != null && !localID.isEmpty()) {
                    new fetchuserdata().execute();
                } else {
                    Toast.makeText(ViewTradersActivity.this, "USER SUSPENDED", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        arrayAdapter.filter(text);
        return false;
    }

    /*private void getallregisteredtraders() {
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        ListView lv = (ListView) findViewById(R.id.listview);
        ListAdapter adapter = new SimpleAdapter(this, userList, R.layout.view_field_agents_list,
                new String[]{"full_names", "phone_number", "trader_code", "status"},
                new int[]{
                        R.id.tradername,
                        R.id.textviewphoneno,
                        R.id.textviewcode,
                        R.id.textviewstatus});
        lv.setAdapter(adapter);
        //tradername = findViewById(R.id.textviewphoneno);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View viewClicked, int position, long id) {

                new fetchuserdata().execute();

            }
        });
    }*/

    public class fetchuserdata extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ViewTradersActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mprogress.show();
            mprogress.setMessage("Fetching User Information...");
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
                    Intent intent = new Intent(getApplicationContext(), TraderPortfolioDetailsActivity.class);
                    intent.putExtra("localID", localID);
                    intent.putExtra("full_names", name);
                    intent.putExtra("staffnumber", staffno);
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