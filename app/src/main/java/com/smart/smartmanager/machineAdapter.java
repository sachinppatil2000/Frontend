package com.smart.smartmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.smartmanager.R.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sachin_Patil06 on 9/15/2015.
 */
public class machineAdapter  extends ArrayAdapter<machineModel> {
    final private Activity usermachinActivity;

    private static final String TAG = "CallCamera";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQ = 0;
    private static final int RESULT_OK = 1;
    private static final int RESULT_CANCELED = 0;

    Uri fileUri = null;
    ImageView photoImage = null;
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
        final Button button = (Button) convertView.findViewById(id.camera);
       // button.setText("Take Photo");
       button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Button telbutton = (Button)v;
                System.out.println("here in the click event");
                try {
                    Intent callIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                  //  callIntent.setData(Uri.parse("tel:" + telbutton.getText()));
                    usermachinActivity.startActivity(callIntent);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

            }
        });

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

    private File getOutputPhotoFile() {
        File directory = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES),/* getPackageName()*/"test" );
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                Log.e(TAG, "Failed to create storage directory.");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.UK).format(new Date());
        return new File(directory.getPath() + File.separator + "IMG_"
                + timeStamp + ".jpg");
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQ) {
            if (resultCode == RESULT_OK) {
                Uri photoUri = null;
                if (data == null) {
                    // A known bug here! The image should have saved in fileUri
                   // Toast.makeText(this, "Image saved successfully",
                    //        Toast.LENGTH_LONG).show();
                    photoUri = fileUri;
                } else {
                    photoUri = data.getData();
                  //  Toast.makeText(this, "Image saved successfully in: " + data.getData(),
                    //        Toast.LENGTH_LONG).show();
                }
                // showPhoto(photoUri);
            } else if (resultCode == RESULT_CANCELED) {
               // Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
              //  Toast.makeText(this, "Callout for image capture failed!",
               //         Toast.LENGTH_LONG).show();
            }
        }
    }

}
