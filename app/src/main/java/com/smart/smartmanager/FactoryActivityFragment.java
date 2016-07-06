package com.smart.smartmanager;


import android.app.ListFragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.GridView;
import android.widget.ListView;
import com.smart.smartmanager.usermachine;

import org.json.*;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class FactoryActivityFragment extends ListFragment implements View.OnClickListener {

    String msgRecieved;
    public FactoryActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
       // if(savedInstanceState==null) {
            super.onCreate(savedInstanceState);
            // retain this fragment
            setRetainInstance(false);
       // }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<factoryModel> arrayOfFactory = new ArrayList<factoryModel>();
        FactoryActivity facActivity = (FactoryActivity)this.getActivity();
        String msg = facActivity.getGCMMessage();
  //      {"Location":"Pune","Vendorid":"test","timestamp":1453269075875,"devices":{"D1":{"DeviceName":"ocbv1","username":"OSBB2B.TMME@TMME","password":"tars123","DeviceType":"vibration","CurrentStatus":"off"},"D2":{"DeviceName":"ocbv1","username":"OSBB2B.TMME@TMME","password":"tars123","DeviceType":"temperature","CurrentStatus":"on"},"D3":"DeviceName":"ocbv1","username":"OSBB2B.TMME@TMME","password":"tars123","DeviceType":"biometric","CurrentStatus":"on"}}}

        msgRecieved = msg;
        String strlocation="";
        JSONObject location=null;
       // JSONParser parser = new JSONParser();
        try {
            JSONObject reader = new JSONObject(msg);
        //    location  = reader.getJSONObject("Location");
            strlocation = reader.getString("Location");
        }
        catch(org.json.JSONException e)
        {
            e.printStackTrace();
            // handle exception.
        }
       // JSONObject json = (JSONObject) parser.parse(stringToParse);
        factoryModel factory = new factoryModel(strlocation,Boolean.FALSE);
        arrayOfFactory.add(factory);
        factoryModel factory1 = new factoryModel("Buses",Boolean.TRUE);
        arrayOfFactory.add(factory1);
        // Create the adapter to convert the array to views
        factoryAdapter fadapter = new factoryAdapter(this.getActivity(), arrayOfFactory);
        setListAdapter(fadapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.getActivity(),usermachine.class);
        intent.putExtra("msgRecived", msgRecieved);
        startActivity(intent);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


       // ListView listView = (ListView) view.findViewById(R.id.Factories);
        //listView.setAdapter(fadapter);
        super.onViewCreated(view, savedInstanceState);

       // view.findViewById(R.id.Inside).setOnClickListener(this);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent(this.getActivity(),usermachine.class);
        intent.putExtra("msgRecived", msgRecieved);
        startActivity(intent);

    }


}
