package com.smart.smartmanager;

/**
 * Created by Sachin_Patil06 on 10/15/2015.
 */
public class factoryModel {
    private String factoryName;
    private Boolean status;

    public factoryModel(String factoryName,Boolean status )
    {
        this.factoryName = factoryName;
        this.status = status;
    }

    public void setFactoryName(String name)
    {
        this.factoryName=name;
    }

    public String getFactoryName()
    {
        return factoryName;
    }


    public void setStatus(Boolean status)
    {
        this.status=status;
    }

    public Boolean getStatus()
    {
        return this.status;
    }
}
