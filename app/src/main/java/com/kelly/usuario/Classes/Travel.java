package com.kelly.usuario.Classes;

/**
 * Created by KELLY on 16-Jul-17.
 */

public class Travel {
    private Schedule schedule;
    private String beginDestination;
    private String endDestination;
    public Travel(){};

    public Travel(String beginDestination, String endDestination){
        this.beginDestination=beginDestination;
        this.endDestination=endDestination;
    }
    public Travel(String beginDestination, String endDestination,Schedule schedule){
        this.beginDestination=beginDestination;
        this.endDestination=endDestination;
        this.setSchedule(schedule);
    }
    public String getBeginDestination() {
        return beginDestination;
    }

    public void setBeginDestination(String beginDestination) {
        this.beginDestination = beginDestination;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public void setEndDestination(String endDestination) {
        this.endDestination = endDestination;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

}
