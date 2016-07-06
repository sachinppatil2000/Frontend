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
import android.widget.ListAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.smart.smartmanager.R.*;

import java.util.List;

//import static com.machineuser.smartmanager_ver2.R.*;

/**
 * Created by Sachin_Patil06 on 10/15/2015.
 */
public class factoryAdapter  extends ArrayAdapter<factoryModel> {
    final private FactoryActivity factoryActivity;
    public factoryAdapter(Context context, List<factoryModel> objects) {
        super(context, 0, objects);
        factoryActivity = (FactoryActivity)context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        factoryModel factory = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemfactory,parent, false);
        }
        if(factory.getStatus())
        {
            convertView.setBackgroundColor(Color.GREEN);
        }
        else
        {
            convertView.setBackgroundColor(Color.RED);
        }

        final Button button = (Button) convertView.findViewById(id.Inside);
        button.setText(factory.getFactoryName());
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Button facbutton = (Button)v;
                System.out.println("here in the click event");
                try {
                    Intent intent = new Intent(factoryActivity,usermachine.class);
                    Intent intent2 = new Intent(factoryActivity,UserOnMachineActivity.class);
                    intent.putExtra("Area", button.getText());
                    intent.putExtra("msgRecieved",factoryActivity.getGCMMessage());
                    intent2.putExtra("msgRecieved",factoryActivity.getGCMMessage());
                    if(button.getText().equals("Buses")) {
                        factoryActivity.startActivity(intent2);
                    }
                    else
                    {
                        factoryActivity.startActivity(intent);                    }
                }
                catch(Exception e) {

                    e.printStackTrace();
                }

            }
        });
        // Lookup view for data population
      //  TextView tvName = (TextView) convertView.findViewById(R.id.factory);
        // Populate the data into the template view using the data object
      //  tvName.setText(factory.getFactoryName());
        //   tvName.setTextSize(32);
        // Return the completed view to render on screen
        return convertView;
    }
}