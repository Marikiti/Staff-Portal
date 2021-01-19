package ics.kenya.com.RegionalManager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import ics.kenya.com.R;
import ics.kenya.com.model.RegisterTrader;
import ics.kenya.com.model.ViewFieldAgents;

public class TestAdapter extends BaseAdapter {
    private ArrayList<ViewFieldAgents> listData;
    private LayoutInflater layoutInflater;

    public TestAdapter(Context aContext, ArrayList<ViewFieldAgents> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View v, ViewGroup vg) {
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.view_staff_list, null);
            holder = new ViewHolder();
            holder.tradername = v.findViewById(R.id.tradername);
            holder.textviewphoneno = v.findViewById(R.id.textviewphoneno);
            holder.textviewcode = v.findViewById(R.id.textviewcode);
            holder.textviewstatus = v.findViewById(R.id.textviewstatus);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.tradername.setText(listData.get(position).getTrader_name());
        holder.textviewphoneno.setText(listData.get(position).getPhone_number());
        holder.textviewcode.setText(listData.get(position).getTrader_code());
        holder.textviewstatus.setText(listData.get(position).getStatus());
        return v;
    }

    static class ViewHolder {
        TextView tradername;
        TextView textviewphoneno;
        TextView textviewcode;
        TextView textviewstatus;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        //registerTraderList.clear();
        if (charText.length() == 0) {
            listData.addAll(listData);
        } else {
            for (ViewFieldAgents wp : listData) {
                if (wp.getTrader_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listData.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
