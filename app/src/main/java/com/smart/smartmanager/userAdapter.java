package com.smart.smartmanager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.smart.smartmanager.R;

import java.util.List;

/**
 * Created by Sachin_Patil06 on 9/15/2015.
 */
public class userAdapter extends ArrayAdapter<userModel> {

    final private  Activity usermachinActivity;
    final private Context ctx;
    // Request code for READ_CONTACTS. It can be any number > 0.
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    public userAdapter(Context context, List<userModel> objects) {
        super(context, 0, objects);
        ctx = context;
        usermachinActivity = (Activity)context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        userModel user = getItem(position);
                // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemuser, parent, false);
        }

        if(user.getStatus())
        {
            convertView.setBackgroundColor(Color.GREEN);
        }
        else
        {
            convertView.setBackgroundColor(Color.RED);
        }
        // Lookup view for data population
        int resid = usermachinActivity.getResources().getIdentifier("employee","drawable",usermachinActivity.getPackageName());
      //   convertView.setBackgroundResource(resid);
        TextView tvName = (TextView) convertView.findViewById(R.id.Name);

        final Button button = (Button) convertView.findViewById(R.id.phone);
        button.setText(user.getPhoneno());
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Boolean readrights=false;
                // Perform action on click
                Button telbutton = (Button) v;
                System.out.println("here in the click event");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && ctx.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
                    //      int permissionCheck = ctx.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
                {
                    usermachinActivity.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
                    readrights=true;
                }
                else
                {
                    readrights=true;
                }

                if(readrights) {
                    try {
                         Intent callIntent = new Intent(Intent.ACTION_CALL);
                         callIntent.setData(Uri.parse("tel:" + telbutton.getText()));
                         usermachinActivity.startActivity(callIntent);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            }
        });
        TextView tvHome = (TextView) convertView.findViewById(R.id.phone);
        // Populate the data into the template view using the data object
        tvName.setText(user.getName() + " " + user.getLastName());
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
