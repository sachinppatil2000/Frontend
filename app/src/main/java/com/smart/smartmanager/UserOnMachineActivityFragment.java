package com.smart.smartmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.ListFragment;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class UserOnMachineActivityFragment extends ListFragment implements View.OnClickListener {

    public UserOnMachineActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<useronmachineModel> arrayOfuseronMachine = new ArrayList<useronmachineModel>();
        useronmachineModel useronmachine1 = new useronmachineModel("Ramakant","kadam","9422374710",40,Boolean.TRUE,"KA 22 FA 9152", Boolean.FALSE);
        useronmachine1.setMachineStopTime("4Hr");
        arrayOfuseronMachine.add(useronmachine1);
        useronmachineModel useronmachine2 = new useronmachineModel("Ramesh","khalap","231232231",40,Boolean.TRUE,"KA 22 FA 9152", Boolean.TRUE);
        arrayOfuseronMachine.add(useronmachine2);
        useronmachineModel useronmachine3 = new useronmachineModel("Nana","patil","23132321",40,Boolean.TRUE,"KA 22 FA 9152", Boolean.TRUE);
       arrayOfuseronMachine.add(useronmachine3);
        useronmachineModel useronmachine4 = new useronmachineModel("Naresh","Sawant","24213231",40,Boolean.TRUE,"KA 22 FA 9152", Boolean.TRUE);
       arrayOfuseronMachine.add(useronmachine4);
        useronmachineModel useronmachine5 = new useronmachineModel("Hitesh","Bhosale","42132321321",40,Boolean.TRUE,"KA 22 FA 9152", Boolean.FALSE);
        useronmachine5.setMachineStopTime("8Hr");
       arrayOfuseronMachine.add(useronmachine5);
        useronmachineModel useronmachine6 = new useronmachineModel("Dinesh","Naik","2321323",40,Boolean.TRUE,"KA 22 FA 9152", Boolean.FALSE);
        useronmachine6.setMachineStopTime("48Hr");
        arrayOfuseronMachine.add(useronmachine6);

        // Create the adapter to convert the array to views
        useronMachineAdapter uadapter = new useronMachineAdapter(this.getActivity(), arrayOfuseronMachine);
        setListAdapter(uadapter);
        return super.onCreateView(inflater, container, savedInstanceState);
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

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.getActivity(),UserOnMachineActivity.class);
        startActivity(intent);

    }
}
