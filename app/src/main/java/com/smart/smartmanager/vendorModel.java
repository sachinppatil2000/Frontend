package com.smart.smartmanager;

/**
 * Created by sachinpatil on 20/02/17.
 */

public class vendorModel {

    private String Name;
    private String Type;
    private String contactno;
    private int rating;

    public vendorModel(String name,String phoneno,int rating,String type )
    {
        //   Name = firstName;
        this.Name = name ;
        this.Type =  type ;
        this.contactno = phoneno;
        this.rating=rating;
    }


    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
