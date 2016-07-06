package com.smart.smartmanager;

/**
 * Created by Sachin_Patil06 on 9/15/2015.
 */
public class userModel {
    private String Name;
    private String Lastname;
    private int Age;
    private String phoneNo;
    private Boolean status;

    public userModel(String firstName,String lastname,String phoneno,int age,Boolean status )
    {
         Name = firstName;
         Lastname = lastname ;
         Age =  age ;
         phoneNo = phoneno;
         this.status = status;
    }

    public void setName(String name)
    {
        Name=name;
    }

    public String getName()
    {
        return Name;
    }

    public void setLastName(String name)
    {
        Lastname=name;
    }

    public String getLastName()
    {
        return Lastname;
    }

    public void setAge(int age)
    {
        Age=age;
    }

    public int getAge()
    {
        return Age;
    }
    public void setPhoneno(String phoneno)
    {
        phoneNo=phoneno;
    }

    public String getPhoneno()
    {
        return phoneNo;
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
