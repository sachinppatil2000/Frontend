package com.smart.smartmanager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
//import android.support.v4.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.smart.smartmanager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class usermachine extends AppCompatActivity {

    ListView listView ;
    ListView machinelistView ;
    private String message;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent latestIntent = getIntent();
        Bundle Extras = latestIntent.getExtras();
        if (Extras!=null) {
            if (Extras.containsKey("gcmMessage")) {
                String msg = Extras.getString("gcmMessage");
                message = msg;
            }
        }
        else
        {
             String msg_dummy =  getString(R.string.sample_data);
            //getString(R.string.dummy_data);
            message = msg_dummy;
        }
      //  setContentView(R.layout.activity_usermachine);
        //updateData(message);
        setContentView(R.layout.activity_usermachine);
        mTitle = mDrawerTitle = getTitle();
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        navDrawerItems = new ArrayList<NavDrawerItem>();
        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
     //   navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
   //     navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        // Pages
    //    navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
     //   navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
        // Recycle the typed array
        navMenuIcons.recycle();

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.employee, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0,message);
        }
    }

    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position,message);
        }
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position,String strMessage) {
        // update the main content by replacing fragments
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        SharedPreferences mPrefs = getSharedPreferences("gcmdata", MODE_PRIVATE);
        Gson gson = new Gson();
        String msgjson = mPrefs.getString(navMenuTitles[position], "");
        Fragment fragment = null;
        Bundle msgBundle = new Bundle();
        msgBundle.putString("rcvdMsg",strMessage);
        switch (position) {
            case 0:
                fragment = new factoryFragment();
                fragment.setArguments(msgBundle);
                break;
            case 1:
                fragment = new factoryFragment();
                fragment.setArguments(msgBundle);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
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
      //  machinelistView = (ListView) findViewById(R.id.machinelist);
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
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    private ArrayList<userModel> getUsers(String Area)
    {
        ArrayList<userModel> arrayOfUsers = new ArrayList<userModel>();
        if(Area.equals("Buses")) {

        userModel user = new userModel("Ramakant kadam","9422374710",40,Boolean.TRUE);
        arrayOfUsers.add(user);
        userModel user1 = new userModel("Mahesh khalap","919919191",30,Boolean.FALSE);
        arrayOfUsers.add(user1);
        userModel user2 = new userModel("Dhondiba Patil","95444343",35,Boolean.FALSE);
        arrayOfUsers.add(user2);
        }
        else
        {
            userModel user = new userModel("Sachin Patil","9422374710",40,Boolean.TRUE);
            arrayOfUsers.add(user);
            userModel user1 = new userModel("Sanjay More","919919191",30,Boolean.FALSE);
            arrayOfUsers.add(user1);
            userModel user2 = new userModel("SnehalPatil","95444343",35,Boolean.FALSE);
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
