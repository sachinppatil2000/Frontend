package com.smart.smartmanager;

/**
 * Created by sachinpatil on 21/02/17.
 */

public class locationSummaryModel {

    public String getWorkDone() {
        return workDone;
    }

    public void setWorkDone(String workDone) {
        this.workDone = workDone;
    }

    public String getCurrentConsumption() {

        return CurrentConsumption;
    }

    public void setCurrentConsumption(String currentConsumption) {
        CurrentConsumption = currentConsumption;
    }

    public String getNoOfEmployeesPresent() {

        return noOfEmployeesPresent;
    }

    public void setNoOfEmployeesPresent(String noOfEmployeesPresent) {
        this.noOfEmployeesPresent = noOfEmployeesPresent;
    }

    private String noOfEmployeesPresent;
    private String CurrentConsumption;
    private String workDone;
}
