package ics.kenya.com.RegionalManager.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ics.kenya.com.R;
import ics.kenya.com.model.RegisterTrader;
import ics.kenya.com.model.ViewFieldAgents;


public class ViewStaffAdapter extends BaseAdapter {

    private List<RegisterTrader> registerTraderList = null;
    private ArrayList<RegisterTrader> arraylist;
    private LayoutInflater layoutInflater;
    Context mContext;

    public ViewStaffAdapter(Context aContext, List<RegisterTrader> registerTraderList) {
        mContext = aContext;
        this.registerTraderList = registerTraderList;
        layoutInflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<RegisterTrader>();
        this.arraylist.addAll(registerTraderList);
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }



    @Override
    public RegisterTrader getItem(int position) {
        return registerTraderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View v, ViewGroup vg) {
        final ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.view_field_agents_list, null);
            holder = new ViewHolder();
            holder.tradername = v.findViewById(R.id.tradername);
            holder.textviewphoneno = v.findViewById(R.id.textviewphoneno);
            holder.textviewcode = v.findViewById(R.id.textviewcode);
            holder.textviewstatus = v.findViewById(R.id.textviewstatus);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.tradername.setText(registerTraderList.get(position).getFullnames());
        holder.textviewphoneno.setText(registerTraderList.get(position).getPhoneNo());
        holder.textviewcode.setText(registerTraderList.get(position).getTradercode());
        holder.textviewstatus.setText(registerTraderList.get(position).getStatus());
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
            registerTraderList.addAll(arraylist);
        } else {
            for (RegisterTrader wp : arraylist) {
                if (wp.getFullnames().toLowerCase(Locale.getDefault()).contains(charText)) {
                    registerTraderList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
