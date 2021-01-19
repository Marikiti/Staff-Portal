package ics.kenya.com.RegionalManager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ics.kenya.com.R;
import ics.kenya.com.model.FileReport;


public class ReadReportAdapter extends BaseAdapter {
    public DecimalFormat currency = new DecimalFormat("Ksh: ###,##0.00");
    private List<FileReport> fileReportList = null;
    private ArrayList<FileReport> arraylist;
    private LayoutInflater layoutInflater;
    Context mContext;

    public ReadReportAdapter(Context aContext, List<FileReport> fileReportList) {
        mContext = aContext;
        this.fileReportList = fileReportList;
        layoutInflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<FileReport>();
        this.arraylist.addAll(fileReportList);
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }


    @Override
    public FileReport getItem(int position) {
        return fileReportList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View v, ViewGroup vg) {
        final ReadReportAdapter.ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.rread_report_activity_content, null);
            holder = new ReadReportAdapter.ViewHolder();
            holder.username = v.findViewById(R.id.username);
            holder.shopname = v.findViewById(R.id.shopname);
            holder.tradernumber = v.findViewById(R.id.tradernumber);
            holder.date = v.findViewById(R.id.date);
            holder.txtva = v.findViewById(R.id.txtva);
            holder.txtvb = v.findViewById(R.id.txtvb);
            holder.txtvc = v.findViewById(R.id.txtvc);
            holder.txtvd = v.findViewById(R.id.txtvd);
            holder.txtve = v.findViewById(R.id.txtve);
            holder.txtvf = v.findViewById(R.id.txtvf);
            holder.txtvg = v.findViewById(R.id.txtvg);
            holder.txtvh = v.findViewById(R.id.txtvh);
            holder.txtvi = v.findViewById(R.id.txtvi);
            holder.txtvj = v.findViewById(R.id.txtvj);
            holder.txttotalscore = v.findViewById(R.id.txttotalscore);
            holder.txtnewsalestarget = v.findViewById(R.id.txtnewsalestarget);

            v.setTag(holder);
        } else {
            holder = (ReadReportAdapter.ViewHolder) v.getTag();
        }
        holder.username.setText(fileReportList.get(position).getUsername());
        holder.shopname.setText(fileReportList.get(position).getShopname());
        holder.tradernumber.setText(fileReportList.get(position).getTradercode());
        holder.date.setText(fileReportList.get(position).getDate());
        holder.txtva.setText(fileReportList.get(position).getA());
        holder.txtvb.setText(fileReportList.get(position).getB());
        holder.txtvc.setText(fileReportList.get(position).getC());
        holder.txtvd.setText(fileReportList.get(position).getD());
        holder.txtve.setText(fileReportList.get(position).getE());
        holder.txtvf.setText(fileReportList.get(position).getF());
        holder.txtvg.setText(fileReportList.get(position).getG());
        holder.txtvh.setText(fileReportList.get(position).getH());
        holder.txtvi.setText(fileReportList.get(position).getI());
        holder.txtvj.setText(fileReportList.get(position).getJ());
        holder.txttotalscore.setText("Total Score:  " + fileReportList.get(position).getTotalscore());
        holder.txtnewsalestarget.setText("New Sales Target: " + fileReportList.get(position).getNewtarget());
        return v;
    }

    static class ViewHolder {
        TextView username;
        TextView shopname;
        TextView tradernumber;
        TextView date;
        TextView txtva;
        TextView txtvb;
        TextView txtvc;
        TextView txtvd;
        TextView txtve;
        TextView txtvf;
        TextView txtvg;
        TextView txtvh;
        TextView txtvi;
        TextView txtvj;
        TextView txttotalscore;
        TextView txtnewsalestarget;
    }

}

