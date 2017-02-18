package com.smart.smartmanager;

/**
 * Created by Sachin_Patil06 on 10/16/2015.
 */
public class useronmachineModel {

    private userModel driver;
    private machineModel machine;
    private int Age;
    private String phoneNo;
    private Boolean status;

    public useronmachineModel(String firstName,String lastname,String phoneno,int age,Boolean userstatus,String machineName, Boolean machineStatus )
    {

      driver = new userModel(lastname,phoneno,age,userstatus);
        machine = new machineModel(machineName,machineStatus);

    }

    public void setDriverName(String name)
    {
        driver.setEmployeename(name);
    }

    public String getDriverName()
    {
        return driver.getEmployeename();
    }

    public void setLastName(String name)
    {
        driver.setLastName(name);
    }

    public String getLastName()
    {
        return driver.getLastName();
    }

    public void setAge(int age)
    {
        driver.setAge(age);
    }

    public int getAge()
    {
        return driver.getAge();
    }
    public void setPhoneno(String phoneno)
    {
        driver.setContactno(phoneno);
    }

    public String getPhoneno()
    {
        return driver.getContactno();
    }

    public void setStatus(Boolean status)
    {
        driver.setStatus(status);
    }

    public Boolean getStatus()
    {
        return driver.getStatus();
    }

    public void setMachineStatus(Boolean status)
    {
        machine.setStatus(status);
    }

    public Boolean getMachineStatus()
    {
        return machine.getStatus();
    }

    public void setMachineName(String name)
    {
        machine.setmachineName(name);
    }

    public String getMachineName()
    {
        return machine.getName();
    }

    public String getMachineStopTime()
    {
        return machine.getStopTime();
    }

    public void setMachineStopTime(String stopTime)
    {
         machine.setStopTime(stopTime);
    }
}
