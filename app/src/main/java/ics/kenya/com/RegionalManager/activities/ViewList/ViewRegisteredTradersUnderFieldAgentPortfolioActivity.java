package ics.kenya.com.RegionalManager.activities.ViewList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
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
import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.activities.TraderProfilePortfolioActivity;
import ics.kenya.com.RegionalManager.adapter.ViewStaffAdapter;
import ics.kenya.com.db.MarikitiDataBaseAdapter;
import ics.kenya.com.model.RegisterTrader;
import ics.kenya.com.model.ViewFieldAgents;

public class ViewRegisteredTradersUnderFieldAgentPortfolioActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;
    private List registerTraderLists = new ArrayList<>();
    private ArrayList<RegisterTrader> registerTraderrrayList = new ArrayList<>();
    RegisterTrader registerTrader;
    ViewStaffAdapter arrayAdapter;
    private HashMap<Integer, String> localhash = new HashMap<>();
    TextView srchstaff;
    private ArrayList<ViewFieldAgents> ViewFieldAgentsList = new ArrayList<>();
    ViewFieldAgents viewFieldAgentsList = new ViewFieldAgents();
    ArrayList<ViewFieldAgents> results = new ArrayList<>();
    MarikitiDataBaseAdapter db;
    String name, status = "INACTIVE";

    String localID, tradername, traderphoneno, staffno,county,constituency,ward, totalsales, salestarget,totalnooftraders;

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

        imgdepo = findViewById(R.id.imgdepo);
        imgdepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        showlistdata();

        /*ArrayList userList = getListData();

        final ListView lv = findViewById(R.id.listview);

        lv.setAdapter(new TestAdapter(this, userList));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View viewClicked, int position, long id) {
                ViewFieldAgents user = (ViewFieldAgents) lv.getItemAtPosition(position);

                TextView textviewtradername = viewClicked.findViewById(R.id.tradername);
                TextView textviewtextviewphoneno = viewClicked.findViewById(R.id.textviewphoneno);
                TextView textviewtextviewcode = viewClicked.findViewById(R.id.textviewcode);


                tradername = results.get(position).getTrader_name();
                staffno = results.get(position).getStaff_no();
                county = results.get(position).getCounty();
                constituency = results.get(position).getConstituency();
                ward = results.get(position).getWard();
                totalsales = results.get(position).getTotal_sales();
                salestarget = results.get(position).getSales_target();
                totalnooftraders = results.get(position).getNumber_of_traders();

                if (tradername.isEmpty() || staffno.isEmpty()){
                   // Toast.makeText(ViewFieldAgentsActivity.this, "Username and Staff No Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new fetchuserdata().execute();
                }


            }
        });*/
    }

    private ArrayList getListData() {

        results.add(new ViewFieldAgents(1, "Joseph Kimani", "0724917075", "01111", "MKT123456", "", "Nairobi", "Starehe", "Pangani", "250000", "300000", "500","ACTIVE"));
        results.add(new ViewFieldAgents(2, "Phylis Otieno", "0724557075", "01222", "MKT123466", "", "Nairobi", "Starehe", "Pangani", "80000", "100000", "800","ACTIVE"));
        results.add(new ViewFieldAgents(3, "Mark Kariuki", "0724667075", "01333", "MKT123476", "", "Nairobi", "Starehe", "Pangani","60000", "90000", "300","ACTIVE"));
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

            }


        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View viewClicked, int position, long id) {
                tradername = registerTraderrrayList.get(position).getFullnames();
                staffno = registerTraderrrayList.get(position).getTradercode();
                county = registerTraderrrayList.get(position).getCounty();
                constituency = registerTraderrrayList.get(position).getConstituency();
                ward = registerTraderrrayList.get(position).getWard();

                localID = localhash.get(position);
                if (localID != null && !localID.isEmpty()) {
                    new fetchuserdata().execute();
                } else {
                    Toast.makeText(ViewRegisteredTradersUnderFieldAgentPortfolioActivity.this, "USER SUSPENDED", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

    }

    public class fetchuserdata extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(ViewRegisteredTradersUnderFieldAgentPortfolioActivity.this);

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
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Void result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    mprogress.dismiss();
                    Intent intent = new Intent(getApplicationContext(), TraderProfilePortfolioActivity.class);
                    intent.putExtra("traderusername", tradername);
                    intent.putExtra("staffnumber", staffno);
                    intent.putExtra("county", county);
                    intent.putExtra("constituency", constituency);
                    intent.putExtra("ward", ward);
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