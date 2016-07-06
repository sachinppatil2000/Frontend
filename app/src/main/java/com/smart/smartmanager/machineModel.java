package com.smart.smartmanager;

/**
 * Created by Sachin_Patil06 on 9/15/2015.
 */
public class machineModel {

    private String machineName;
    private Boolean status;
    private String stopTime;

    public machineModel(String machineName,Boolean status )
    {
        this.machineName = machineName;
        this.status = status;
    }

    public void setmachineName(String name)
    {
        this.machineName=name;
    }

    public String getName()
    {
        return machineName;
    }


    public void setStatus(Boolean status)
    {
        this.status=status;
    }

    public Boolean getStatus()
    {
        return this.status;
    }

    public void setStopTime(String stopTime)
    {
        this.stopTime=stopTime;
    }

    public String getStopTime()
    {
        return this.stopTime;
    }

}
