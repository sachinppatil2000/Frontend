package com.smart.smartmanager;

/**
 * Created by Sachin_Patil06 on 9/15/2015.
 */
public class userModel {


    private String employeename;
    private String Lastname;
    private int Age;
    private String contactno;
    private Boolean status;

    public userModel(String stremployeename,String phoneno,int age,Boolean status )
    {
      //   Name = firstName;
        employeename = employeename ;
         Age =  age ;
        contactno = phoneno;
        this.employeename=stremployeename;
         this.status = status;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }


    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
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


    public void setStatus(Boolean status)
    {
        this.status=status;
    }

    public Boolean getStatus()
    {
        return this.status;
    }
}
