package com.smart.smartmanager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smart.smartmanager.R.*;

import java.util.List;

/**
 * Created by Sachin_Patil06 on 9/15/2015.
 */
public class machineAdapter  extends ArrayAdapter<machineModel> {
    final private Activity usermachinActivity;
    public machineAdapter(Context context, List<machineModel> objects) {
        super(context, 0, objects);
        usermachinActivity = (Activity)context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        machineModel machine = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemmachine, parent, false);
        }
        TextView tvStatus = (TextView) convertView.findViewById(R.id.status);
        TextView tvStoptime = (TextView) convertView.findViewById(R.id.stoptime);
        // Populate the data into the template view using the data object
       // tvName.setText(machine.getName());
        if(machine.getStatus())
        {
            convertView.setBackgroundColor(Color.GREEN);
            tvStatus.setText("ON");
            tvStatus.setTextSize(25);
            tvStoptime.setText("RUNNING");
            tvStatus.setTextColor(Color.GREEN);
        }
        else
        {
            tvStoptime.setText(machine.getStopTime());
            tvStatus.setText("OFF");
            tvStatus.setTextSize(25);
            tvStatus.setTextColor(Color.RED);
        }

        int resid = usermachinActivity.getResources().getIdentifier("machine1","drawable",usermachinActivity.getPackageName());
          convertView.setBackgroundResource(resid);
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.machinName);
       // Populate the data into the template view using the data object
        tvName.setTextSize(25);
        tvName.setText(machine.getName());
     //   tvName.setTextSize(32);
        // Return the completed view to render on screen
        return convertView;
    }
}
