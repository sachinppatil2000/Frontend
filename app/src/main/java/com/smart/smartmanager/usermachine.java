package com.smart.smartmanager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.smart.smartmanager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class usermachine extends AppCompatActivity {

    ListView listView ;
    ListView machinelistView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermachine);
        Intent intent = getIntent();
        String Area = intent.getStringExtra("Area");
        String msgRecieved = intent.getStringExtra("msgRecieved");
        updateData(msgRecieved);
  /*   JSONArray devicelist= null;

     try {
            JSONObject reader = new JSONObject(msgRecieved);
            devicelist = reader.getJSONArray("devices");
      }
        catch(org.json.JSONException e)
        {
            e.printStackTrace();
        }
        machinelistView = (ListView) findViewById(R.id.machinelist);
        // Construct the data source
     // Create the adapter to convert the array to views
        userAdapter adapter = new userAdapter(this,getUsers(Area));
        listView.setAdapter(adapter);

      // Create the adapter to convert the array to views
        machineAdapter madapter = new machineAdapter(this, getMachines(Area,devicelist));
        machinelistView.setAdapter(madapter);
        utils layoututil = new utils();
        layoututil.setListViewHeightBasedOnChildren(machinelistView);
        layoututil.setListViewHeightBasedOnChildren(listView);*/
    }


    private void updateData(String msgRecieved)
    {

        String Area="";
        JSONArray devicelist= null;

        try {
            JSONObject reader = new JSONObject(msgRecieved);
            devicelist = reader.getJSONArray("devices");
            Area = reader.getString("Location");
        }
        catch(org.json.JSONException e)
        {
            e.printStackTrace();
        }
        machinelistView = (ListView) findViewById(R.id.machinelist);
        // Construct the data source
        // Create the adapter to convert the array to views
        userAdapter adapter = new userAdapter(this,getUsers(Area));
        listView.setAdapter(adapter);

        // Create the adapter to convert the array to views
        machineAdapter madapter = new machineAdapter(this, getMachines(Area,devicelist));
        machinelistView.setAdapter(madapter);
        utils layoututil = new utils();
        layoututil.setListViewHeightBasedOnChildren(machinelistView);
        layoututil.setListViewHeightBasedOnChildren(listView);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usermachine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.string.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<userModel> getUsers(String Area)
    {
        ArrayList<userModel> arrayOfUsers = new ArrayList<userModel>();
        if(Area.equals("Buses")) {

        userModel user = new userModel("Ramakant","kadam","9422374710",40,Boolean.TRUE);
        arrayOfUsers.add(user);
        userModel user1 = new userModel("Mahesh","khalap","919919191",30,Boolean.FALSE);
        arrayOfUsers.add(user1);
        userModel user2 = new userModel("Dhondiba","Patil","95444343",35,Boolean.FALSE);
        arrayOfUsers.add(user2);
        }
        else
        {
            userModel user = new userModel("Sachin","Patil","9422374710",40,Boolean.TRUE);
            arrayOfUsers.add(user);
            userModel user1 = new userModel("Sanjay","More","919919191",30,Boolean.FALSE);
            arrayOfUsers.add(user1);
            userModel user2 = new userModel("Snehal","Patil","95444343",35,Boolean.FALSE);
            arrayOfUsers.add(user2);
        }
        return arrayOfUsers;
    }

    private ArrayList<machineModel> getMachines(String Area,JSONArray devices)  {
        // Normally call should be made directly to database to get the data
        // here createing dummy data based on content.
      //  if()
        JSONObject device= null;
        ArrayList<machineModel> arrayOfMachine = new ArrayList<machineModel>();
        if( devices!=null) {

            for (int i = 0, size = devices.length(); i < size; i++) {
                try {
                    device =   devices.getJSONObject(i);
                    String machinetype = device.getString("DeviceType");
                    // add code to send update details in machine model.
                    // get device type depending on device type add parameters.
                    switch(machinetype)
                    {
                        case "vibration": // do something
                                         machineModel machine = new machineModel(device.getString("DeviceName"),getStatus(device.getString("CurrentStatus")));
                                         machine.setStopTime("3HR");
                                         arrayOfMachine.add(machine);
                                                break;
                        case "biometric" :  machineModel biomachine = new machineModel(device.getString("DeviceName"),getStatus(device.getString("CurrentStatus")));
                                            biomachine.setStopTime("3HR");
                                            arrayOfMachine.add(biomachine);
                                            break;
                        case "temperature" : // do something
                                             machineModel tempmachine = new machineModel(device.getString("DeviceName"),getStatus(device.getString("CurrentStatus")));
                                             tempmachine.setStopTime("3HR");
                                             arrayOfMachine.add(tempmachine);
                                            break;
                        default : // do somethimg
                    }
                }
                catch(org.json.JSONException e)
                {
                    e.printStackTrace();
                }
                if (Area.equals("Buses")) {
                    machineModel machine = new machineModel("KA 22 FA 9152", Boolean.FALSE);
                    //machine.setStopTime("2hr");
                    arrayOfMachine.add(machine);
                    machineModel machine1 = new machineModel("KA 22 FA 1001", Boolean.TRUE);
                    arrayOfMachine.add(machine1);
                    machineModel machine2 = new machineModel("KA 22 FA 0009", Boolean.TRUE);
                    arrayOfMachine.add(machine2);
                    machineModel machine3 = new machineModel("KA 22 FA 9000", Boolean.TRUE);
                    arrayOfMachine.add(machine3);
                    machineModel machine4 = new machineModel("KA 22 FA 9999", Boolean.TRUE);
                    arrayOfMachine.add(machine4);
                    machineModel machine5 = new machineModel("KA 22 FA 1111", Boolean.TRUE);
                    arrayOfMachine.add(machine5);
                    machineModel machine6 = new machineModel("KA 22 FA 4244", Boolean.TRUE);
                    arrayOfMachine.add(machine6);
                    machineModel machine7 = new machineModel("KA 22 FA 5255", Boolean.TRUE);
                    arrayOfMachine.add(machine7);
                    machineModel machine8 = new machineModel("KA 22 FA 5255", Boolean.TRUE);
                    arrayOfMachine.add(machine8);
                }
            }
        }
        return arrayOfMachine;
    }

    private Boolean getStatus(String status)
    {
        Boolean boolStatus;
        if(status.equals("on"))
            boolStatus = Boolean.TRUE;
        else
            boolStatus = Boolean.FALSE;
        return boolStatus;
    }


    @Override
    public void onResume() {
        super.onResume();
        this.registerReceiver(mMessageReceiver, new IntentFilter("gcmMessageIntent"));
    }

    //Must unregister onPause()
    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(mMessageReceiver);
    }


    //This is the handler that will manager to process the broadcast intent
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            // Extract data included in the Intent
            String message = intent.getStringExtra("msgRecieved");
            updateData(message);
            //do other stuff here
        }
    };
}
