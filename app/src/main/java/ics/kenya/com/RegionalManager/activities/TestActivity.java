package ics.kenya.com.RegionalManager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ics.kenya.com.R;
import ics.kenya.com.RegionalManager.activities.ViewList.RegionalManagerViewFieldAgentsListActivity;
import ics.kenya.com.model.ViewFieldAgents;
import ics.kenya.com.RegionalManager.adapter.TestAdapter;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

        ArrayList userList = getListData();

        final ListView lv = findViewById(R.id.listview);

        lv.setAdapter(new TestAdapter(this, userList));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ViewFieldAgents user = (ViewFieldAgents) lv.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), RegionalManagerViewFieldAgentsListActivity.class);
                startActivity(intent);            }
        });
    }
    private ArrayList getListData() {
        ArrayList<ViewFieldAgents> results = new ArrayList<>();

        ViewFieldAgents viewFieldAgentsList = new ViewFieldAgents();

      /*  results.add(new ViewFieldAgents(1, "Koskei", "0724917075", "011", "", "", "", "", "", "", ""));
        results.add(new ViewFieldAgents(1, "Faith", "0724557075", "011", "", "", "", "", "", "", ""));
        results.add(new ViewFieldAgents(1, "Shadrack", "0724667075", "011", "", "", "", "", "", "", ""));
        results.add(new ViewFieldAgents(1, "Bella", "0721117075", "011", "", "", "", "", "", "", ""));

*/
        return results;
    }
}