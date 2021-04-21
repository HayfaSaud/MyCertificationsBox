package com.example.cs410project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class customAdapter extends BaseAdapter {
    private Context context;
    private dbHelper helper;
    private ArrayList<String> names, ids, orgs, dates;

    public customAdapter(Context context, ArrayList<String> names, ArrayList<String> ids, ArrayList<String> orgs,ArrayList<String> dates) {
        this.context = context;
        this.names = names;
        this.ids = ids;
        this.dates = dates;
        this.orgs = orgs;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final viewHolder holder;
        helper = new dbHelper(context);
        Button delete = convertView.findViewById(R.id.deleteIcon);
        LayoutInflater inflater;
        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.certificate, null);
            holder = new viewHolder();
            holder.name = convertView.findViewById(R.id.name);
            holder.id = convertView.findViewById(R.id.id);
            holder.org = convertView.findViewById(R.id.org);
            holder.date = convertView.findViewById(R.id.Date);
            convertView.setTag(holder);
        }
        else{
            holder = (viewHolder) convertView.getTag();
        }
        holder.name.setText(names.get(position));
        holder.id.setText(ids.get(position));
        holder.org.setText(orgs.get(position));
        holder.date.setText(dates.get(position));
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ID = holder.id.toString();
                helper.Delete(ID);
                names.remove(position);
                ids.remove(position);
                orgs.remove(position);
                dates.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    public class viewHolder{
        TextView name, id, org, date;
    }
}
