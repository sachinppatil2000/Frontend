package com.smart.smartmanager;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sachinpatil on 21/02/17.
 */

public class locationSummaryViewHolder {
    public TextView workDoneTextView;
    public TextView noofEmployeesPresent;
    public TextView electricConsumption;

   locationSummaryViewHolder(LinearLayout v)
    {
       workDoneTextView =(TextView) v.findViewById(R.id.hoursofwork);
        noofEmployeesPresent=(TextView) v.findViewById(R.id.noofEmployeesPresent);
        electricConsumption=(TextView) v.findViewById(R.id.electicityconsumed);

    }


}
