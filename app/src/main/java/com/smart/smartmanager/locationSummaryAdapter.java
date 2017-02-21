package com.smart.smartmanager;

/**
 * Created by sachinpatil on 21/02/17.
 */

public class locationSummaryAdapter {

   static public void loadData(locationSummaryViewHolder h,locationSummaryModel data )
    {
        h.workDoneTextView.setText(data.getWorkDone());
        h.electricConsumption.setText(data.getCurrentConsumption());
        h.noofEmployeesPresent.setText(data.getNoOfEmployeesPresent());
    }

}
