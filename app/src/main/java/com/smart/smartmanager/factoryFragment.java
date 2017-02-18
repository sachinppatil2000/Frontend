package com.smart.smartmanager;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import org.codehaus.jackson.node.ArrayNode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 9/17/2016.
 */
public class factoryFragment extends Fragment {

    ListView listView ;
    ListView machinelistView ;
    public factoryFragment(){ }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_factoryfloor, container, false);
        machinelistView = (ListView)(rootView.findViewById(R.id.machinelist));
        Bundle bundle = this.getArguments();
        String msg = bundle.getString("rcvdMsg");
        listView = (ListView)(rootView.findViewById(R.id.userlist));
        updateData(msg);
        return rootView;
    }

    private void updateData(String msgRecieved)
    {
        String Area="";
        JSONArray devicelist= null;
        ArrayNode datasetArray = null;
        List<userModel> userlist = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
       // mapper.configure(JsonParser.Feature.A, true);
        try {
            JsonNode rootNode = mapper.readTree(msgRecieved);
            Area =rootNode.get("Location").getValueAsText();// reader.getString("Location");
            datasetArray= (ArrayNode)rootNode.get("devices");
            if (datasetArray.isArray()) {
                for (final JsonNode objNode : datasetArray) {
                    String devicetype = objNode.get("DeviceType").getValueAsText();
                    if ("biometric".equals(devicetype)) {
                        Iterator<JsonNode> iterator = objNode.get("AttendenceInfo").getElements();
                        userlist = getUsers(Area,iterator);
                    }
                    System.out.println(objNode);
                }
            }


            String testString = "";

        }
        catch(java.io.IOException e)
        {
            e.printStackTrace();
        }

        // Construct the data source
        // Create the adapter to convert the array to views
        userAdapter adapter = new userAdapter(getActivity(),userlist);
        listView.setAdapter(adapter);

        // Create the adapter to convert the array to views
        machineAdapter madapter = new machineAdapter(getActivity(), getMachines(Area,datasetArray));
        machinelistView.setAdapter(madapter);
        utils layoututil = new utils();
        layoututil.setListViewHeightBasedOnChildren(machinelistView);
        layoututil.setListViewHeightBasedOnChildren(listView);
    }

    private ArrayList<userModel> getUsers(String Area,Iterator<JsonNode> attendenceInformation )
    {
        ArrayList<userModel> arrayOfUsers = new ArrayList<userModel>();

        for (;attendenceInformation.hasNext();attendenceInformation.next()){
           JsonNode userInfo = attendenceInformation.next();
            userModel user = new userModel(userInfo.get("employeename").getValueAsText(),userInfo.get("contactno").getValueAsText(),40,Boolean.TRUE);
            arrayOfUsers.add(user);

        }
        return arrayOfUsers;
    }

    private ArrayList<machineModel> getMachines(String Area,ArrayNode devices)  {
        // Normally call should be made directly to database to get the data
        // here createing dummy data based on content.
        //  if()
        //JsonNode device= null;
        ArrayList<machineModel> arrayOfMachine = new ArrayList<machineModel>();
        if( devices!=null) {

            for (final JsonNode device : devices){
               // try {
                    //device =   devices.getJSONObject(i);
                    String machinetype = device.get("DeviceType").getTextValue();//device.getString("DeviceType");
                    // add code to send update details in machine model.
                    // get device type depending on device type add parameters.
                    switch(machinetype)
                    {
                        case "vibration": // do something
                            machineModel machine = new machineModel(device.get("DeviceName").getTextValue(),getStatus(device.get("CurrentStatus").getTextValue()));
                            machine.setStopTime("3HR");
                            arrayOfMachine.add(machine);
                            break;
                      //case "biometric" :  machineModel biomachine = new machineModel(device.get("DeviceName").getTextValue(),getStatus(device.get("CurrentStatus").getTextValue()));
                      //      biomachine.setStopTime("3HR");
                      //      arrayOfMachine.add(biomachine);
                      //      break;
                        case "temperature" : // do something
                            machineModel tempmachine = new machineModel(device.get("DeviceName").getTextValue(),getStatus(device.get("CurrentStatus").getTextValue()));
                            tempmachine.setStopTime("3HR");
                            arrayOfMachine.add(tempmachine);
                            break;
                        default : // do somethimg
                    }
               // }
                /*catch(java.io.IOException e)
                {
                    e.printStackTrace();
                }*/
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


}
