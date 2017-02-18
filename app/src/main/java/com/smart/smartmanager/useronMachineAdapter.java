package com.smart.smartmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
//import com.machineuser.smartmanager;
import com.smart.smartmanager.R;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created by Sachin_Patil06 on 10/16/2015.
 */
public class useronMachineAdapter extends ArrayAdapter <useronmachineModel>
{
    final private Activity usermachineActivity;

    public useronMachineAdapter(Context context, List<useronmachineModel> objects) {
       super(context, 0, objects);

        usermachineActivity = (Activity)context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        useronmachineModel user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemuseronmachine, parent, false);
        }

        TextView tvMachine = (TextView) convertView.findViewById(R.id.machineName);
        tvMachine.setText(user.getMachineName());
        tvMachine.setTextColor(Color.MAGENTA);
       // tvMachine.setElegantTextHeight(Boolean.TRUE);
        tvMachine.setTextSize(20);
        TextView tvStoptime = (TextView) convertView.findViewById(R.id.stoptime);
        if(user.getMachineStatus())
        {
           // convertView.setBackgroundColor(Color.GREEN);

            tvStoptime.setTextColor(Color.GREEN);
            tvStoptime.setTextSize(25);
            tvStoptime.setText("RUNNING");
        }
        else
        {
            //convertView.setBackgroundColor(Color.RED);
            tvStoptime.setTextColor(Color.RED);
            tvStoptime.setTextSize(25);
            tvStoptime.setText(user.getMachineStopTime());
        }
        // Lookup view for data population

        int resid = usermachineActivity.getResources().getIdentifier("volvo2","drawable",usermachineActivity.getPackageName());
        convertView.setBackgroundResource(resid);
        TextView tvName = (TextView) convertView.findViewById(R.id.userName);
         tvName.setText(user.getDriverName() + " " + user.getLastName());
        tvName.setTextSize(20);
        //   tvName.setElegantTextHeight(Boolean.TRUE);
        //tvName.setElegantTextHeight(Boolean.TRUE);
        tvName.setTextColor(Color.BLUE);

        final Button button = (Button) convertView.findViewById(R.id.phone);
        button.setText(user.getPhoneno());
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Button telbutton = (Button) v;
                System.out.println("here in the click event");
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + telbutton.getText()));
                    usermachineActivity.startActivity(callIntent);
                } catch (Exception e) {

                    e.printStackTrace();
                }

            }
        });

                // Populate the data into the template view using the data object

        // tvName.setTextSize(32);
        // tvHome.setText(user.getPhoneno());
        //   tvHome.setTextSize(32);
        // Return the completed view to render on screen

        return convertView;
    }

    void startActivity(Intent callintent)
    {
        //  adapterview.startActivity(callintent)
    }
}
