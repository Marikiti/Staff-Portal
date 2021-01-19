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
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.activities.FieldAgentUserProfilePortfolioActivity;
import ics.kenya.com.db.MarikitiDataBaseAdapter;
import ics.kenya.com.model.ViewFieldAgents;
import ics.kenya.com.RegionalManager.adapter.TestAdapter;

public class RegionalManagerViewFieldAgentsListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ImageView imgdepo;

    TextView srchstaff;
    private ArrayList<ViewFieldAgents> ViewFieldAgentsList = new ArrayList<>();
    private HashMap<Integer, String> localhash = new HashMap<>();
    ViewFieldAgents viewFieldAgentsList = new ViewFieldAgents();
    ArrayList<ViewFieldAgents> results = new ArrayList<>();

    SearchView editsearch;
    String localID, tradername, traderphoneno, staffno,county,constituency,ward, totalsales, salestarget,totalnooftraders,status;
    MarikitiDataBaseAdapter db;
    TextView searcchstaff;
    RatingBar ratingBar;
    TestAdapter arrayadapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.field_agents_activity);
        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationIcon(upArrow);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        ratingBar =findViewById(R.id.rating_bar);

        db = new MarikitiDataBaseAdapter(getApplicationContext());
        // Locate the EditText in listview_main.xml

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

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });


        ArrayList userList = getListData();

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
                status = results.get(position).getStatus();
               // Toast.makeText(ViewFieldAgentsActivity.this, "Username" + results.get(position).getTrader_name(), Toast.LENGTH_SHORT).show();

                if (tradername.isEmpty() || staffno.isEmpty()){
                    Toast.makeText(RegionalManagerViewFieldAgentsListActivity.this, "Username and Staff No Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new fetchuserdata().execute();
                }

                /*      localID = localhash.get(position);*/

                // String tradername =
            }
        });
    }

    private ArrayList getListData() {

        results.add(new ViewFieldAgents(1, "Mathew Kariuki", "0724917075", "MKT01111", "MKT123456", "", "Nairobi", "Starehe", "Pangani", "250000", "300000", "2","ACTIVE"));
        results.add(new ViewFieldAgents(2, "Faith Chepkorir", "0724557075", "MKT01222", "MKT123466", "", "Kericho", "belgut", "Ololmasani", "80000", "100000", "5","ACTIVE"));
        results.add(new ViewFieldAgents(3, "Shadrack Langat", "0724667075", "MKT01333", "MKT123476", "", "Nairobi", "Westlands", "Mogondo", "60000", "90000", "3","ACTIVE"));
        results.add(new ViewFieldAgents(4, "Bella Kimani", "0721117075", "MKT01444", "MKT12345686", "", "Bomet", "chepalungu", "Olkerin", "100000", "200000", "4","ACTIVE"));
        results.add(new ViewFieldAgents(5, "Faith Thangothe", "0724917075", "MKT01555", "MKT123496", "", "Eldoret", "Soimingin", "Chebunyo", "90000", "100000", "3","ACTIVE"));

       // editsearch = (SearchView) findViewById(R.id.searchview);
        //editsearch.setOnQueryTextListener(this);

        return results;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //String text = newText;
        //arrayadapter.filter(text);
        return false;
    }

    public class fetchuserdata extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mprogress = new ProgressDialog(RegionalManagerViewFieldAgentsListActivity.this);

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
                    Intent intent = new Intent(getApplicationContext(), FieldAgentUserProfilePortfolioActivity.class);
                    intent.putExtra("traderusername", tradername);
                    intent.putExtra("staffnumber", staffno);
                    intent.putExtra("county", county);
                    intent.putExtra("constituency", constituency);
                    intent.putExtra("ward", ward);
                    intent.putExtra("totalsales", totalsales);
                    intent.putExtra("salestarget", salestarget);
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

